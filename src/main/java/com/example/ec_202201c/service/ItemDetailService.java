package com.example.ec_202201c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.OrderItem;
import com.example.ec_202201c.domain.Topping;
import com.example.ec_202201c.repository.CartListRepository;
import com.example.ec_202201c.repository.ItemDetailRepository;

@Service
public class ItemDetailService {

	@Autowired
	private ItemDetailRepository itemDetailRepository;
	
	@Autowired 
	private CartListRepository cartListRepository;

	public Item showDetail(Integer id) {
		Item item = itemDetailRepository.load(id);
		return item;
	}

	public List<Topping> findAll() {
		return itemDetailRepository.findAll();
	}

	public Order ordersNullChecked(Integer userId) {
		return itemDetailRepository.ordersNullChecked(userId);
	}
	
	@Transactional
	public synchronized void ordersUpdate(Order order, OrderItem orderItem, List<Integer> toppingIdList) {
		System.out.println(order.getId() + "order.getid");
		itemDetailRepository.ordersUpdate(order);
		itemDetailRepository.OrderItemInsert(orderItem);
		int id = itemDetailRepository.findByItemId();
		itemDetailRepository.OrderToppingInsert(toppingIdList, id);
	}

	@Transactional
	public synchronized void OrderInsert(Order order, OrderItem orderItem, List<Integer> toppingIdList) {
		System.out.println(order.getUserId());
		itemDetailRepository.ordersInsert(order);
		int orderId = itemDetailRepository.findByOrderId();
		orderItem.setOrderId(orderId);
		System.out.println(orderItem.getOrderId());
		itemDetailRepository.OrderItemInsert(orderItem);
		int id = itemDetailRepository.findByItemId();
		itemDetailRepository.OrderToppingInsert(toppingIdList, id);
	}
	
	public Order findShoppingCartByUserId(Integer userId) {
		return cartListRepository.findShoppingCartByUserId(userId);
	}
}
