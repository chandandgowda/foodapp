package com.ty.foodappapi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodappapi.dto.FoodOrder;
import com.ty.foodappapi.repository.FoodOrderRepository;

@Repository
public class FoodOrdeDao {
	@Autowired
	FoodOrderRepository repository;
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		return repository.save(foodOrder);
	}
	
	public FoodOrder getFoodOrderById(int id) {
		Optional<FoodOrder> opt = repository.findById(id);
		if(opt.isPresent()) {
			FoodOrder fd = opt.get();
			return fd;
		}
		return null;
	}
}
