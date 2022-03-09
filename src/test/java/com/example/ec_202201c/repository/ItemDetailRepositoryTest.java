package com.example.ec_202201c.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ec_202201c.domain.Item;

@SpringBootTest
class ItemDetailRepositoryTest {

	@Autowired
	ItemDetailRepository itemDetailrepository;

	@Test
	void testLoad_select_item_truee() {

		Item expectedItem = new Item();

		expectedItem = itemDetailrepository.load(1);

		assertEquals(1, expectedItem.getId(), "itemDetailrepository.load()の引数が1の場合、idは1");
		assertEquals("とんこつラーメン", expectedItem.getName(), "itemDetailrepository.load()の引数が1の場合、nameはとんこつラーメン");
		assertEquals(
				"創業当時から今に引き継ぐとんこつラーメンの本流であり、原点の味。18時間の調理と、丸1日の熟成を経て、とんこつの旨味を極限まで抽出した豊かで香り高いシルキーなスープに、博多らしい細麺がマッチします。",
				expectedItem.getDescription(), "itemDetailrepository.load()の引数が1の場合、descriptionはid=1の説明");
		assertEquals(700, expectedItem.getPriceM(), "itemDetailrepository.load()の引数が1の場合、price_Mは700");
		assertEquals(800, expectedItem.getPriceL(), "itemDetailrepository.load()の引数が1の場合、price_Lは800");
		assertEquals("1.jpg", expectedItem.getImagePath(), "itemDetailrepository.load()の引数が1の場合、image_pathは1.jpg");

		expectedItem = itemDetailrepository.load(18);

		assertEquals(18, expectedItem.getId(), "itemDetailrepository.load()の引数が18の場合、idは18");
		assertEquals("真・澄み切った塩らーめん", expectedItem.getName(), "itemDetailrepository.load()の引数が1の場合、nameは真・澄み切った塩らーめん");
		assertEquals("岩塩でキリッとさせた鶏スープがまとまりがある仕上がり。透明度の高いスープは旨味が凝縮された丁寧な一杯。何度でも食べたくなる味のラーメンです。",
				expectedItem.getDescription(), "itemDetailrepository.load()の引数が18の場合、descriptionはid=18の説明");
		assertEquals(990, expectedItem.getPriceM(), "itemDetailrepository.load()の引数が18の場合、price_Mは990");
		assertEquals(1090, expectedItem.getPriceL(), "itemDetailrepository.load()の引数が18の場合、price_Lは1090");
		assertEquals("18.jpg", expectedItem.getImagePath(), "itemDetailrepository.load()の引数が18の場合、image_pathは18.jpg");

	}
	
	@Test
	void testLord_select_fail() {
		
	}

	

}
