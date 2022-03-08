package com.example.ec_202201c.form;

import java.util.ArrayList;
import java.util.List;

public class InsertOrderItemForm {

	private Integer quantity;
	private Character size;
	private List<Integer> toppingIdList = new ArrayList<>();
	
	@Override
	public String toString() {
		return "InsertOrderItemForm [quantity=" + quantity + ", size=" + size + ", toppingIdList=" + toppingIdList
				+ "]";
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

	public List<Integer> getToppingIdList() {
		return toppingIdList;
	}

	public void setToppingIdList(List<Integer> toppingIdList) {
		this.toppingIdList = toppingIdList;
	}
	
	
	
	


	

}
