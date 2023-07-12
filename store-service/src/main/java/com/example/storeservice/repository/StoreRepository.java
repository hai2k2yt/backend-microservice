package com.example.storeservice.repository;

import com.example.storeservice.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

  Optional<Store> findByUsername(String username);
}
