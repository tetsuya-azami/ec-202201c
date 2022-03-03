package com.example.ec_202201c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.repository.ItemListRepository;

@Service
public class ItemListService {
	@Autowired
	ItemListRepository itemListRepository;
	public List<Item> findAll(){
		return itemListRepository.findAll();
	}
}
