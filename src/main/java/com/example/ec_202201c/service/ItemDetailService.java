package com.example.ec_202201c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.OrderItem;
import com.example.ec_202201c.domain.OrderTopping;
import com.example.ec_202201c.domain.Topping;
import com.example.ec_202201c.repository.ItemDetailRepository;

@Service
public class ItemDetailService {

	@Autowired
	private ItemDetailRepository itemDetailRepository;

	public Item showDetail(Integer id) {
		Item item = itemDetailRepository.load(id);
		return item;
	}

	public List<Topping> findAll() {
		return itemDetailRepository.findAll();
	}

	public Order findShoppingCartByUserId(Integer userId) {
		return itemDetailRepository.findShoppingCartByUserId(userId);
	}
	
	@Transactional
	public synchronized void ordersUpdate(Order order, OrderItem orderItem, OrderTopping orderTopping) {
		itemDetailRepository.ordersUpdate(order);
		itemDetailRepository.OrderItemInsert(orderItem);
		int id = itemDetailRepository.findByItemId();
		itemDetailRepository.OrderToppingInsert(orderTopping);
	}

	@Transactional
	public synchronized void OrderInsert(Order order, OrderItem orderItem, OrderTopping orderTopping) {
		itemDetailRepository.ordersInsert(order);
		itemDetailRepository.OrderItemInsert(orderItem);
		int id = itemDetailRepository.findByItemId();
		orderTopping.setOrderItemId(id);
		itemDetailRepository.OrderToppingInsert(orderTopping);
	}
}
