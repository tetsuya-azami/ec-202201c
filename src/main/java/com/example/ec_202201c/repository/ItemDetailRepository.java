package com.example.ec_202201c.repository;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Topping;

@Repository
public class ItemDetailRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);
	
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = new BeanPropertyRowMapper<>(Topping.class);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Item load(Integer id){
		String sql = "SELECT name, description, price_m, price_l,  image_path FROM items WHERE id =:id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
	
	public List<Topping> findAll(){
		String sql = "SELECT name, price_m, price_l FROM toppings";
		
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		
		return toppingList;
	}
}
