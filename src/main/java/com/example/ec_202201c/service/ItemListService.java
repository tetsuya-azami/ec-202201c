package com.example.ec_202201c.service;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.repository.ItemListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemListService {
	@Autowired
	ItemListRepository itemListRepository;

	public Page<Item> findAll(Pageable pageable) {
		return itemListRepository.findAll(pageable);
	}

	public Page<Item> findByLikeName(String name, Pageable pageable) {
		return itemListRepository.findByLikeName(name, pageable);
	}
}
