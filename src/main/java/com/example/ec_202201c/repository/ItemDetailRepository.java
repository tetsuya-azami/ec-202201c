package com.example.ec_202201c.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.OrderItem;
import com.example.ec_202201c.domain.Topping;

@Repository
public class ItemDetailRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Order> ORDER_ROW_MAPPER = new BeanPropertyRowMapper<>(Order.class);

	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = new BeanPropertyRowMapper<>(Topping.class);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Item load(Integer id) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path FROM items WHERE id =:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}

	public List<Topping> findAll() {
		String sql = "SELECT id, name, price_m, price_l FROM toppings";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}

	public Order ordersNullChecked(Integer userId) {
		String sql = "SELECT user_id, status, total_price FROM orders WHERE user_id = :userId AND status = 0";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		 List <Order> orderList =  template.query(sql, param, ORDER_ROW_MAPPER);
		 if(orderList.size()==0) {
			 return null;
		 }else {
			 return orderList.get(0);
		 }
	}
	

	public void ordersUpdate(Order order) {
		String updateSql = "UPDATE orders SET total_price = :totalPrice WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id", order.getId())
				.addValue("totalPrice", order.getCalcTotalPrice());
		template.update(updateSql, param);
	}
	

	public void ordersInsert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String insertSql = "INSERT INTO orders( user_id,  status,  total_price)"
				+ "                      VALUES(:userId, 0, :totalPrice)";
		template.update(insertSql, param);
	}

	public void OrderItemInsert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items( item_id, order_id, quantity,  size)"
				+ " values                   (:itemId, :orderId, :quantity, :size)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		template.update(sql, param);
	}

	public void OrderToppingInsert(List<Integer> toppingIdList, Integer orderItemId) {
		String sql = "INSERT INTO order_toppings( topping_id,  order_item_id)"
				+ " values                      (:toppingId,  :orderItemId)";
		for (Integer toppingId : toppingIdList) {
			SqlParameterSource param = new MapSqlParameterSource()
					.addValue("toppingId", toppingId)
					.addValue("orderItemId", orderItemId);
					template.update(sql, param);
		}
		
	}
	
	public int findByItemId() {
		String sql = "SELECT MAX(id) id from order_items";
		return template.queryForObject(sql,new MapSqlParameterSource(), Integer.class);
	}
	
	public int findByOrderId() {
		String sql = "SELECT MAX(id) id from orders";
		return template.queryForObject(sql,new MapSqlParameterSource(), Integer.class);
	}
}
