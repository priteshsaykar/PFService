package com.mutualfund.fileupload.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mutualfund.fileupload.entity.Stock;
import com.mutualfund.fileupload.repository.StockRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@Service
public class StocksCsvIngestionService {

    @Autowired
    private StockRepository stockRepository;

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
}