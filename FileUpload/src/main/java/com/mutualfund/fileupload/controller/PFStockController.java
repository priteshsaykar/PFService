package com.mutualfund.fileupload.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutualfund.fileupload.model.PFStocksDto;
import com.mutualfund.fileupload.service.PFStocksService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pfstocks")
public class PFStockController {
	
	
	@Autowired
	PFStocksService pfStocksService;
	
	
	@GetMapping
	@RequestMapping("/zstocks")
    public Map<String,List<PFStocksDto>> getAllZStocks() {
		return pfStocksService.getAllZerodhaPFStockswithMfCount();
    }
	
	@GetMapping
	@RequestMapping("/istocks")
    public Map<String,List<PFStocksDto>> getAllIStocks() {
		return pfStocksService.getAllIciciDirectPFStockswithMfCount();
    }


}
