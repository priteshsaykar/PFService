package com.mutualfund.fileupload.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "istocks")
@Data
public class IStock {

    @EmbeddedId
    private IciciStockId id;

    private String companyName;
    private String isinCode;
    private int qty;
    private double averageCostPrice;
    private double currentMarketPrice;
    private double valueAtCost;
    private double valueAtMarketPrice;
    private double realizedProfitLoss;
    private double unrealizedProfitLoss;
    private double unrealizedProfitLossPercent;

    // Getters and setters
}