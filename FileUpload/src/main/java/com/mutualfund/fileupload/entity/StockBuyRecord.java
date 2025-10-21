package com.mutualfund.fileupload.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stock_buy_records")
@Data
public class StockBuyRecord {

    @EmbeddedId
    private StockBuyId id;

    private String sector;
    private String classification;
    private String month;
    private long netQtyBought;
    private double approxBuyValue;

    // Getters and setters
}
