package com.ty.foodappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.foodappapi.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
