package com.example.ec_202201c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderFInishedController {
	/**
	 * @author Tetsuya Azami
	 * @return 注文確定画面
	 */
	@RequestMapping("finished")
	public String finished() {
		return "order_finished";
	}
}
