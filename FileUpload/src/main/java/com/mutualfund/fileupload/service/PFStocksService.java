package com.mutualfund.fileupload.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfund.fileupload.entity.Stock;
import com.mutualfund.fileupload.entity.StockBuyRecord;
import com.mutualfund.fileupload.entity.StockSellRecord;
import com.mutualfund.fileupload.entity.Zstock;
import com.mutualfund.fileupload.model.PFStocksDto;
import com.mutualfund.fileupload.repository.StockBuyRepository;
import com.mutualfund.fileupload.repository.StockRepository;
import com.mutualfund.fileupload.repository.StockSellRepository;

@Service
public class PFStocksService {

	@Autowired
	private ZstockService zstockService;

	@Autowired
	private IStockService iStockService;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private StockSellRepository stockSellRepository;

	@Autowired
	private StockBuyRepository stockBuyRepository;

	public Map<String, List<PFStocksDto>> getAllPFStockswithMfCount() {

		List<Zstock> zStockList = zstockService.getAllZstocks();
		Map<String, List<PFStocksDto>> pfStockMfDetails = new HashMap<>();

		for (Zstock zStock : zStockList) {
			List<PFStocksDto> pfStockList = new ArrayList<>();
			Optional<Stock> stock = stockRepository.findById(zStock.getInstrument());

			if (stock.isPresent()) {
				String companyName = normalizeCompanyName(stock.get().getCompanyName());

				List<StockSellRecord> stockSellRecordList = stockSellRepository
						.findCleanedNamesNative(companyName);

				List<StockBuyRecord> stockBuyRecordList = stockBuyRepository
						.findCleanedNamesNative(companyName);

				for (StockSellRecord stockSellRecord : stockSellRecordList) {
					PFStocksDto pfStocksDto = new PFStocksDto();
					pfStocksDto.setPfcolor("Red");
					pfStocksDto.setPfDate(stockSellRecord.getMonthStartDate());
					pfStocksDto.setPfStockName(stockSellRecord.getId().getStockName());
					pfStockList.add(pfStocksDto);

				}

				for (StockBuyRecord stockBuyRecord : stockBuyRecordList) {
					PFStocksDto pfStocksDto = new PFStocksDto();
					pfStocksDto.setPfcolor("Green");
					pfStocksDto.setPfDate(stockBuyRecord.getMonthStartDate());
					pfStocksDto.setPfStockName(stockBuyRecord.getId().getStockName());
					pfStockList.add(pfStocksDto);

				}
			}

			pfStockList.sort(Comparator.comparing(PFStocksDto::getPfDate));

			if (pfStockList == null || pfStockList.isEmpty())
				// List is either null or empty
				System.out.println("No data found for key: yourKey");

			else
				pfStockMfDetails.put(pfStockList.get(0).getPfStockName(), pfStockList);

		}

		return pfStockMfDetails;

	}

	public static String normalizeCompanyName(String name) {
		return name.toLowerCase().replaceAll("\\blimited\\b", "").replaceAll("\\bltd\\.?\\b", "")
				.replaceAll("\\s+", " ") // collapse extra spaces
				.trim();
	}
}
