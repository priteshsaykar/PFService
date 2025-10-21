package com.mutualfund.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutualfund.fileupload.entity.StockBuyId;
import com.mutualfund.fileupload.entity.StockBuyRecord;

public interface StockBuyRepository extends JpaRepository<StockBuyRecord, StockBuyId> {
}
