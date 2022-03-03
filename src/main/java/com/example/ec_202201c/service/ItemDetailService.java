package com.example.ec_202201c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Topping;
import com.example.ec_202201c.repository.ItemDetailRepository;

@Service
@Transactional
public class ItemDetailService {

	@Autowired
	private ItemDetailRepository itemDetailRepository;
	
	public Item showDetail(Integer id) {
		Item item = itemDetailRepository.load(id);
		return item;
	}
	
	public List<Topping> findAll(){
		List<Topping> toppingList = itemDetailRepository.findAll();
		return toppingList;
	}
}
