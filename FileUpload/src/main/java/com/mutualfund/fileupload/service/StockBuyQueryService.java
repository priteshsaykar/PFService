package com.mutualfund.fileupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfund.fileupload.repository.StockBuyRepository;
import com.mutualfund.fileupload.repository.projection.StockCountProjection;

@Service
public class StockBuyQueryService {

    @Autowired
    private StockBuyRepository repository;

    public List<StockCountProjection> getStocksWithMinCount(int minCount) {
        return repository.findStocksWithMinCount(minCount);
    }
}
