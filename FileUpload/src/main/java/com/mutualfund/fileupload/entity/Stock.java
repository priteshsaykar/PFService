package com.mutualfund.fileupload.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "nse_stocks")
@Data
public class Stock {
    @Id
    private String symbol;

    @Column(name = "company_name")
    private String companyName;

    private String series;

    @Column(name = "listing_date")
    private String dateOfListing;

    @Column(name = "paid_up_value")
    private int paidUpValue;

    @Column(name = "market_lot")
    private int marketLot;

    @Column(name = "isin")
    private String isinNumber;

    @Column(name = "face_value")
    private int faceValue;

    // Getters and setters
}