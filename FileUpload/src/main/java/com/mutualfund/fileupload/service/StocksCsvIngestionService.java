package com.mutualfund.fileupload.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mutualfund.fileupload.entity.IStock;
import com.mutualfund.fileupload.entity.IciciStockId;
import com.mutualfund.fileupload.entity.Stock;
import com.mutualfund.fileupload.entity.Zstock;
import com.mutualfund.fileupload.repository.IstockRepository;
import com.mutualfund.fileupload.repository.StockRepository;
import com.mutualfund.fileupload.repository.ZstockRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@Service
public class StocksCsvIngestionService {

    @Autowired
    private StockRepository stockRepository;
    
    
    @Autowired
    private ZstockRepository zstockRepository;
    
    @Autowired
    private IstockRepository istockRepository;

    public void ingestCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> records = csvReader.readAll();  // This can throw CsvException

            for (String[] record : records) {
                Stock stock = new Stock();
                stock.setSymbol(record[0]);
                stock.setCompanyName(record[1]);
                stock.setSeries(record[2]);
                stock.setDateOfListing(record[3]);
                stock.setPaidUpValue(Integer.parseInt(record[4]));
                stock.setMarketLot(Integer.parseInt(record[5]));
                stock.setIsinNumber(record[6]);
                stock.setFaceValue(Integer.parseInt(record[7]));

                stockRepository.save(stock);
            }
        } catch (CsvException e) {
            throw new RuntimeException("CSV parsing error: " + e.getMessage(), e);
        }
    }

    public void ingestZstcokCsv(MultipartFile file) throws IOException {
    	 String filename = file.getOriginalFilename();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens.length < 10) continue;

                Zstock stock = new Zstock();
                stock.setInstrument(tokens[0].replace("\"", ""));
                stock.setQty(Integer.parseInt(tokens[1]));
                stock.setAvgCost(Double.parseDouble(tokens[2]));
                stock.setLtp(Double.parseDouble(tokens[3]));
                stock.setInvested(Double.parseDouble(tokens[4]));
                stock.setCurrentVal(Double.parseDouble(tokens[5]));
                stock.setPnl(Double.parseDouble(tokens[6]));
                stock.setNetChg(Double.parseDouble(tokens[7]));
                stock.setDayChg(Double.parseDouble(tokens[8]));
                stock.setSourceFile(filename); // SET FILENAME

                zstockRepository.save(stock);
            }
        }
    }
    
    
    public void ingestIstcokCsv(MultipartFile file) throws IOException {
   	 String filename = file.getOriginalFilename();

       try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
           String line;
           reader.readLine(); // skip header

           while ((line = reader.readLine()) != null) {
               String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
               if (tokens.length < 12) continue;

               IStock stock = new IStock();
               IciciStockId stockId = new IciciStockId(filename, tokens[0]); // sourceFile + stockSymbol
               stock.setId(stockId);

               stock.setCompanyName(tokens[1]);
               stock.setIsinCode(tokens[2]);
               stock.setQty(Integer.parseInt(tokens[3]));
               stock.setAverageCostPrice(Double.parseDouble(tokens[4]));
               stock.setCurrentMarketPrice(Double.parseDouble(tokens[5]));
               stock.setValueAtCost(Double.parseDouble(tokens[7]));
               stock.setValueAtMarketPrice(Double.parseDouble(tokens[8]));
               stock.setRealizedProfitLoss(Double.parseDouble(tokens[9]));
               stock.setUnrealizedProfitLoss(Double.parseDouble(tokens[10]));
               stock.setUnrealizedProfitLossPercent(Double.parseDouble(tokens[11].replace("(", "").replace(")", "")));

               istockRepository.save(stock);
           }
       }
   }
    
}