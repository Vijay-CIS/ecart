package com.demo.ecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecart.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByProductName(String productName);

}
