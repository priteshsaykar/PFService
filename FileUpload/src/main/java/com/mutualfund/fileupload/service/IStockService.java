package com.mutualfund.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfund.fileupload.entity.IStock;
import com.mutualfund.fileupload.repository.IstockRepository;

import java.util.List;

@Service
public class IStockService {

    @Autowired
    private IstockRepository iStockRepository;

    public List<IStock> getAllStocks() {
        return iStockRepository.findAll();
    }
}