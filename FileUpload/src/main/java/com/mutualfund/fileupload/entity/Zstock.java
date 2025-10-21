package com.mutualfund.fileupload.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Zstocks")
@Data
public class Zstock {

	@Id
	private String instrument;
	@Column(name = "qty")
	private int qty;
	@Column(name = "avgCost")
	private double avgCost;
	@Column(name = "ltp")
	private double ltp;
	@Column(name = "invested")
	private double invested;
	@Column(name = "currentVal")
	private double currentVal;
	@Column(name = "pnl")
	private double pnl;
	@Column(name = "netChg")
	private double netChg;
	@Column(name = "dayChg")
	private double dayChg;
	@Column(name = "sourceFile")
	 private String sourceFile; // NEW FIELD


	// Getters and setters
}