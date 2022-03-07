package com.example.ec_202201c.form;

public class InsertOrderItemForm {
	
	private Integer itemId;
	private Integer orderId;
	private Integer quantity;
	private Character size;
	
	private Integer toppingId;
	private Integer orderItemId;
	
	private Integer UserId;
	private Integer status;
	private Integer totalPrice;
	
	@Override
	public String toString() {
		return "InsertOrderItemForm [itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity + ", size="
				+ size + ", toppingId=" + toppingId + ", orderItemId=" + orderItemId + ", UserId=" + UserId
				+ ", status=" + status + ", totalPrice=" + totalPrice + "]";
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

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	
}
