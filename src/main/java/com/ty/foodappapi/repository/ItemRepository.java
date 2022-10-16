package com.ty.foodappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.foodappapi.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
