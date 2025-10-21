package com.mutualfund.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutualfund.fileupload.entity.Zstock;

public interface ZstockRepository extends JpaRepository<Zstock, String> {
}
