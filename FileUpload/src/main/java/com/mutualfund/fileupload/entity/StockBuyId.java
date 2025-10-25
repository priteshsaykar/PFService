package com.mutualfund.fileupload.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class StockBuyId implements Serializable {
    private String sourceFile;
    private String stockName;

    public StockBuyId() {}
    public StockBuyId(String sourceFile, String stockName) {
        this.sourceFile = sourceFile;
        this.stockName = stockName;
    }

    // Getters, setters, equals, hashCode
}
