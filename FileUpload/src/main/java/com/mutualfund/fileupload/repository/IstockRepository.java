package com.mutualfund.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutualfund.fileupload.entity.IStock;
import com.mutualfund.fileupload.entity.IciciStockId;


public interface IstockRepository extends JpaRepository<IStock, IciciStockId>{

}
