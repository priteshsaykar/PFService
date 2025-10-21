package com.mutualfund.fileupload.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mutualfund.fileupload.entity.StockBuyId;
import com.mutualfund.fileupload.entity.StockBuyRecord;
import com.mutualfund.fileupload.repository.StockBuyRepository;

@Service
public class StockBuyCsvIngestionService {

    @Autowired
    private StockBuyRepository repository;

    public void ingestCsv(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            reader.readLine(); // skip header

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens.length < 6) continue;

                StockBuyRecord record = new StockBuyRecord();
                StockBuyId id = new StockBuyId(filename, tokens[0].trim());
                record.setId(id);

                record.setSector(tokens[1].trim());
                record.setClassification(tokens[2].trim());
                record.setMonth(tokens[3].trim());
                record.setNetQtyBought(Long.parseLong(tokens[4].trim()));
                record.setApproxBuyValue(Double.parseDouble(tokens[5].trim()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);

                String monthStr = tokens[3].trim(); // e.g., "August-2025"
                LocalDate firstDayOfMonth = YearMonth.parse(monthStr, formatter).atDay(1);

                record.setMonthStartDate(firstDayOfMonth);

                repository.save(record);
            }
        }
    }
}
