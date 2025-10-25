package com.mutualfund.fileupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfund.fileupload.repository.StockBuyRepository;
import com.mutualfund.fileupload.repository.StockSellRepository;
import com.mutualfund.fileupload.repository.projection.StockCountProjection;

@Service
public class MfCountService {

    @Autowired
    private StockBuyRepository buyRepository;
    
    @Autowired
    private StockSellRepository sellRepository;
    

    public List<StockCountProjection> getBuyStocksWithMinCount(int minCount) {
        return buyRepository.findStocksWithMinCount(minCount);
    }
    
    
    public List<StockCountProjection> getSellStocksWithMinCount(int minCount) {
        return sellRepository.findStocksWithMinCount(minCount);
    }
}
