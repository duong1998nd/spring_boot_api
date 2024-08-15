package com.bShop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bShop.model.ImageData;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String filename);
}
