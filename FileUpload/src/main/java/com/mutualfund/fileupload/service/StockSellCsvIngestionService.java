package com.mutualfund.fileupload.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mutualfund.fileupload.entity.StockSellId;
import com.mutualfund.fileupload.entity.StockSellRecord;
import com.mutualfund.fileupload.repository.StockSellRepository;

@Service
public class StockSellCsvIngestionService {

    @Autowired
    private StockSellRepository repository;

    public void ingestCsv(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            reader.readLine(); // skip header

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens.length < 6) continue;

                StockSellRecord record = new StockSellRecord();
                StockSellId id = new StockSellId(filename, tokens[0].trim());
                record.setId(id);

                record.setSector(tokens[1].trim());
                record.setClassification(tokens[2].trim());
                record.setMonth(tokens[3].trim());
                record.setNetQtySold(Long.parseLong(tokens[4].trim()));
                record.setApproxSellValue(Double.parseDouble(tokens[5].trim()));

                repository.save(record);
            }
        }
    }
}
