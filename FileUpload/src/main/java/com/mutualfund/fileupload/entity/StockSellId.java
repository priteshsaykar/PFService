package com.mutualfund.fileupload.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class StockSellId implements Serializable {
    private String sourceFile;
    private String stockName;

    public StockSellId() {}
    public StockSellId(String sourceFile, String stockName) {
        this.sourceFile = sourceFile;
        this.stockName = stockName;
    }

    // Getters, setters, equals, hashCode
}
