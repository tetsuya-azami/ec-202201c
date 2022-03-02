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
	private String zipCode;
	private String address;
	private String telephone;
}
