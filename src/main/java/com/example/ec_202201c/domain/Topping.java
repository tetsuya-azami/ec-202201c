package com.example.ec_202201c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topping {
	private Integer id;
	private String name;
	private Integer priceM;
	private Integer priceL;
}
