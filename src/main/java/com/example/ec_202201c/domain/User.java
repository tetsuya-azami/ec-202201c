package com.example.ec_202201c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String zipcode;
	private String address;
	private String telephone;

	public User(String name, String email, String password, String zipcode, String address,
			String telephone) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.zipcode = zipcode;
		this.address = address;
		this.telephone = telephone;
	}
}
