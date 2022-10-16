package com.ty.foodappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.foodappapi.dto.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {

}
