package com.mutualfund.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mutualfund.fileupload.entity.StockBuyId;
import com.mutualfund.fileupload.entity.StockBuyRecord;
import com.mutualfund.fileupload.entity.StockSellRecord;
import com.mutualfund.fileupload.repository.projection.StockCountProjection;

public interface StockBuyRepository extends JpaRepository<StockBuyRecord, StockBuyId> {
	
	
	 @Query(value = """
		        SELECT stock_name AS stockName, COUNT(stock_name) AS monthCount
		        FROM stock_buy_records
		        GROUP BY stock_name
		        HAVING COUNT(stock_name) = :minCount
		    """, nativeQuery = true)
		    List<StockCountProjection> findStocksWithMinCount(@Param("minCount") int minCount);
	 
	 
	 
	 @Query(value = "SELECT *  FROM stock_buy_records WHERE TRIM(LOWER(REPLACE(stock_name, 'Ltd.', ''))) LIKE LOWER(:stockNamePart)", nativeQuery = true)
	 List<StockBuyRecord> findCleanedNamesNative(@Param("stockNamePart") String stockNamePart);



}
