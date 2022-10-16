package com.ty.foodappapi.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	ProductRepository repository;
	
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	
	public Product upateProduct(int id, Product product) {
		Optional<Product> opt = repository.findById(id);
		if(opt.isPresent()) {
			Product p = opt.get();
			return p;
		}else {
			return null;
		}
	}
	
	
	public String deleteProduct(int id) {
		Optional<Product> opt = repository.findById(id);
		if(opt.isPresent()) {
			Product product = opt.get();
			repository.delete(product);
			return "Deleted Successfully";
		}else {
			return "ID is not Exist";
		}
	}
	
	public Product getProductById(int id) {
		Optional<Product> opt = repository.findById(id);
		if(opt.isPresent()) {
			Product p = opt.get();
			return p;
		}else {
			return null;
		}
	}
	
	public List<Product> getAllProduct(){
		return repository.findAll();
	}
}


