package com.mutualfund.fileupload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutualfund.fileupload.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
	
	Optional<Stock> findById(String id);

}