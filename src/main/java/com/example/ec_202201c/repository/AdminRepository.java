package com.example.ec_202201c.repository;

import java.util.List;
import com.example.ec_202201c.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
	@Autowired
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 商品名の重複チェック
	 *
	 * @param name 商品名
	 * @return 商品1件もしくはnull
	 * @author Tetsuya Azami
	 */
	public Item findItemByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT name FROM items ");
		sql.append("WHERE name = :name");
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		List<Item> itemList = template.query(sql.toString(), param, ITEM_ROW_MAPPER);
		if (itemList.size() == 0) {
			return null;
		} else {
			return itemList.get(0);
		}
	}

	public void insertItem(Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO items ");
		sql.append("(name, description, price_m, price_l, image_path)");
		sql.append("VALUES (:name,:description, :priceM, :priceL, :imagePath);");
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql.toString(), param);
	}
}
