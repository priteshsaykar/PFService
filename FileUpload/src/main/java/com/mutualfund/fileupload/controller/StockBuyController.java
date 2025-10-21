package com.mutualfund.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mutualfund.fileupload.service.StockBuyCsvIngestionService;

@RestController
@RequestMapping("/api/stock-buy")
public class StockBuyController {

    @Autowired
    private StockBuyCsvIngestionService ingestionService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().contains("buy")) {
            return ResponseEntity.badRequest().body("Invalid file: filename must contain the word 'buy'.");
        }

        try {
            ingestionService.ingestCsv(file);
            return ResponseEntity.ok("Stock buy CSV uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error: " + e.getMessage());
        }
    }
}
