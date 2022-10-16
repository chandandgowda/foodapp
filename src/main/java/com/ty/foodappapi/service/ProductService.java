package com.ty.foodappapi.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ty.foodappapi.dao.ProductDao;
import com.ty.foodappapi.dto.Product;
import com.ty.foodappapi.exception.IdnotFoundException;
import com.ty.foodappapi.repository.ProductRepository;
import com.ty.foodappapi.util.ResponseStructure;

@Service
public class ProductService {
	@Autowired
	ProductDao dao;
	@Autowired
	ProductRepository repository;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(dao.saveProduct(product));
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(int id, Product product) {
		Optional<Product> opt = repository.findById(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		if (opt.isPresent()) {

			Product res = opt.get();
			res.setName(product.getName());
			res.setPrice(product.getPrice());
			res.setOffer(product.getOffer());
			res.setType(product.getType());

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");

			responseStructure.setData(dao.saveProduct(res));
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdnotFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int id) {
		Optional<Product> opt = repository.findById(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		if (opt.isPresent()) {
			Product product = new Product();
			repository.delete(product);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("DELETED");

			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("NOT DELETED");
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct() {
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(repository.findAll());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> getProduct(@PathVariable int id) {
		Optional<Product> opt = repository.findById(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		if (opt.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(opt.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("ID NOT FOUND");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
}


