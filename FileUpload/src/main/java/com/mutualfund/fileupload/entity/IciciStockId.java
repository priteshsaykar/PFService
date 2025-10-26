package com.mutualfund.fileupload.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class IciciStockId implements Serializable {

    private String sourceFile;
    private String stockSymbol;

    // Constructors
    public IciciStockId() {}
    public IciciStockId(String sourceFile, String stockSymbol) {
        this.sourceFile = sourceFile;
        this.stockSymbol = stockSymbol;
    }

    // Getters, setters, equals, and hashCode
    // Required for JPA to identify uniqueness
}
