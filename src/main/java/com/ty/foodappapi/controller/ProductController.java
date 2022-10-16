package com.ty.foodappapi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.service.ProductService;
import com.ty.foodappapi.util.ResponseStructure;

@RestController
public class ProductController {
	@Autowired
	ProductService service;
	
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> SaveProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}
	
	@PutMapping("/products/update")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestParam int id,@RequestBody Product product){
		return service.updateProduct(id, product);
	}
	
	@DeleteMapping("/products/delete")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@RequestParam int id) {
		return service.deleteProduct(id);
	}
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
		return service.getAllProduct();
	}
	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> getProduct(@PathVariable int id) {
		return service.getProduct(id);
	}	
}

