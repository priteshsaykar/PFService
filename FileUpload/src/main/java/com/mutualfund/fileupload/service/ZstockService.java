package com.mutualfund.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfund.fileupload.entity.Zstock;
import com.mutualfund.fileupload.repository.ZstockRepository;

import java.util.List;

@Service
public class ZstockService {

    @Autowired
    private ZstockRepository zstockRepository;

    public List<Zstock> getAllZstocks() {
        return zstockRepository.findAll();
    }
}