package com.ty.foodappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.foodappapi.dto.FoodOrder;
import com.ty.foodappapi.service.FoodOrderService;
import com.ty.foodappapi.util.ResponseStructure;

@RestController
public class FoodOrderController {
	@Autowired
	FoodOrderService foodOrderService;
	
	@PostMapping("/foodorders")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodorder(@RequestBody FoodOrder foodOrder){
		return foodOrderService.saveFoodOrder(foodOrder);
	}
	@GetMapping("/foodorders")
	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodorderById(@RequestParam int id){
		return foodOrderService.getFoodOrderById(id);
	}
}
