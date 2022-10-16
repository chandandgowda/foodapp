package com.ty.foodappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodappapi.dao.FoodOrdeDao;
import com.ty.foodappapi.dto.FoodOrder;
import com.ty.foodappapi.dto.Item;
import com.ty.foodappapi.exception.IdnotFoundException;
import com.ty.foodappapi.repository.FoodOrderRepository;
import com.ty.foodappapi.util.ResponseStructure;
@Service
public class FoodOrderService {
	@Autowired
	FoodOrdeDao dao;
	
	@Autowired
	FoodOrderRepository repository;
	
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder){
		double price =0.0;
		List<Item> items = foodOrder.getItem();
		for(Item item : items) {
			price = price + item.getCost()*item.getQuantity();
			item.setFoodOrder(foodOrder);
		}
		foodOrder.setTotal_cost(price);
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<FoodOrder>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESSFULLY INSERTED");
		responseStructure.setData(dao.saveFoodOrder(foodOrder));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(int id){
		Optional<FoodOrder> opt = repository.findById(id);
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<FoodOrder>();
		if(opt.isPresent()) {
			FoodOrder fd = opt.get();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("The FoodOrder detail:");
			responseStructure.setData(dao.getFoodOrderById(id));
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.OK);
		}else {
			throw new IdnotFoundException("Given ID: "+id+", does not Exist");
		}
		
	}
}
