package com.example.ec_202201c.form;

import javax.validation.constraints.Size;

public class ItemSearchForm {
	@Size(max = 128)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
