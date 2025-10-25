package com.mutualfund.fileupload.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PFStocksDto {

	private String pfStockName;
	private Integer pfMfCount;
	private String pfcolor;
	private LocalDate pfDate;

}
