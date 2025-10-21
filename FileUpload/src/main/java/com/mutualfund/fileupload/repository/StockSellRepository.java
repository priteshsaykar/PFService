package com.mutualfund.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutualfund.fileupload.entity.StockSellId;
import com.mutualfund.fileupload.entity.StockSellRecord;

public interface StockSellRepository extends JpaRepository<StockSellRecord, StockSellId> {
}
