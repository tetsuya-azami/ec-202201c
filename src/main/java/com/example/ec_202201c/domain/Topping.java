package com.example.ec_202201c.domain;

public class Topping {
	private Integer id;
	private String name;
	private Integer priceM;
	private Integer priceL;

	public Topping() {}

	public Topping(Integer id, String name, Integer priceM, Integer priceL) {
		this.id = id;
		this.name = name;
		this.priceM = priceM;
		this.priceL = priceL;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	@Override
	public String toString() {
		return "Topping [id=" + id + ", name=" + name + ", priceL=" + priceL + ", priceM=" + priceM
				+ "]";
	}
}
