package com.example.ec_202201c.domain;

public class OrderTopping {
	private Integer id;
	private Integer toppingId;
	private Integer orderItemId;
	private Topping topping;

	public OrderTopping() {}

	public OrderTopping(Integer id, Integer toppingId, Integer orderItemId, Topping topping) {
		this.id = id;
		this.toppingId = toppingId;
		this.orderItemId = orderItemId;
		this.topping = topping;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}



}
