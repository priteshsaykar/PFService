package com.mutualfund.fileupload.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stock_sell_records")
@Data
public class StockSellRecord {

    @EmbeddedId
    private StockSellId id;

    private String sector;
    private String classification;
    private String month;
    private long netQtySold;
    private double approxSellValue;

    // Getters and setters
}
