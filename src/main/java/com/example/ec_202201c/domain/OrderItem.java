package com.example.ec_202201c.domain;

import java.util.List;

public class OrderItem {
	private Integer id;
	private Integer itemId;
	private Integer orderId;
	private Integer quantity;
	private Character size;
	private Item item;
	private List<OrderTopping> orderToppingList;

	public OrderItem() {}

	public OrderItem(Integer id, Integer itemId, Integer orderId, Integer quantity, Character size,
			Item item, List<OrderTopping> orderToppingList) {
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.size = size;
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
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", item=" + item + ", itemId=" + itemId + ", orderId="
				+ orderId + ", orderToppingList=" + orderToppingList + ", quantity=" + quantity
				+ ", size=" + size + "]";
	}

	/**
	 * 商品1種類のトッピングを含めた値段を返す
	 *
	 * @return 商品1種類のトッピングを含めた値段
	 * @author Tetsuya Azami
	 */
	public int getSubTotal() {
		int itemPrice = 0;
		int toppingPrice = 0;
		if (getSize() == 'M') {
			itemPrice = getItem().getPriceM();
			// orderToppingリストの合計金額(サイズM)
			toppingPrice = getOrderToppingList().stream()
					.mapToInt(orderTopping -> orderTopping.getTopping().getPriceM()).sum();
		} else if (getSize() == 'L') {
			itemPrice = getItem().getPriceL();
			// orderToppingリストの合計金額(サイズL)
			toppingPrice = getOrderToppingList().stream()
					.mapToInt(orderTopping -> orderTopping.getTopping().getPriceL()).sum();
		}
		int totalPrice = (itemPrice + toppingPrice) * getQuantity();
		return totalPrice;
	}
}
