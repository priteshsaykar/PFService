package com.mutualfund.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mutualfund.fileupload.entity.StockSellId;
import com.mutualfund.fileupload.entity.StockSellRecord;
import com.mutualfund.fileupload.repository.projection.StockCountProjection;

public interface StockSellRepository extends JpaRepository<StockSellRecord, StockSellId> {
	
	 @Query(value = """
		        SELECT stock_name AS stockName, COUNT(stock_name) AS monthCount
		        FROM stock_sell_records
		        GROUP BY stock_name
		        HAVING COUNT(stock_name) = :minCount
		    """, nativeQuery = true)
		    List<StockCountProjection> findStocksWithMinCount(@Param("minCount") int minCount);
	 
	 
	 @Query(value = "SELECT * FROM stock_sell_records WHERE TRIM(LOWER(REPLACE(stock_name, 'Ltd.', ''))) LIKE LOWER(:stockNamePart)", nativeQuery = true)
	 List<StockSellRecord> findCleanedNamesNative(@Param("stockNamePart") String stockNamePart);

}
