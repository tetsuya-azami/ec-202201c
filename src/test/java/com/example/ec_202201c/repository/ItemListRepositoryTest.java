package com.example.ec_202201c.repository;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.example.ec_202201c.domain.Item;

@SpringBootTest
class ItemListRepositoryTest {

	@Autowired
	private ItemListRepository itemListRepository;
	
	@Test
	public void findItemList(@PageableDefault(page = 0, size = 9) Pageable pageable) {
		Page<Item> pageList = itemListRepository.findAll(pageable);	
		assertEquals("旨辛味噌麺", pageList.getContent().get(0).getName(), "nameは旨辛味噌麺");
	}

}
