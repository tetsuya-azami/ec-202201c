package com.example.ec_202201c.form;

public class InsertOrderItemForm {
	
	private Integer quantity;
	private Character size;
	
	private Integer toppingId;
	private Integer orderItemId;
	
	@Override
	public String toString() {
		return "InsertOrderItemForm [quantity=" + quantity + ", size=" + size + ", toppingId=" + toppingId
				+ ", orderItemId=" + orderItemId + "]";
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

	public Integer getToppingId() {
		return toppingId;
	}

	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	
	
	
	
	

	
	
}
