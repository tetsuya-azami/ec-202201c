package com.example.ec_202201c.domain;

import java.util.List;

public class OrderItem {
	private Integer id;
	private Integer itemId;
	private Integer orderId;
	private Integer quantity;
	private Character Size;
	private Item item;
	private List<Item> orderToppingList;

	public OrderItem() {}

	public OrderItem(Integer id, Integer itemId, Integer orderId, Integer quantity, Character size,
			Item item, List<Item> orderToppingList) {
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.quantity = quantity;
		Size = size;
		this.item = item;
		this.orderToppingList = orderToppingList;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return Size;
	}

	public void setSize(Character size) {
		Size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<Item> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	// 後で実装
	public int getSubTotal() {
		return 0;
	}
}
