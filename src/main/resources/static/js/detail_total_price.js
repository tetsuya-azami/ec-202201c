/**
 * 
 */
'use strict';
$(function() {
	calc_price();
	$('.size').on('change', function() {
		console.log('サイズ判定')
		calc_price();
	});

	$('.checkbox').on('change', function() {
		calc_price();
		console.log('チェックボックスチェンジ')
	});

	$('#quantity').on('change', function() {
		console.log('数量')
		calc_price();
	});

	function calc_price() {
		let $size = $('.size:checked').val();
		console.log($size);
		let $topping_count = $('.topping input:checkbox:checked').length;
		console.log($topping_count);
		let $quantity = $('#quantity').val();
		console.log($quantity);
		let $size_price = 0;
		let $topping_price = 0;

		if ($size === 'M') {
			console.log('Mサイズ')
			$size_price = parseInt($('#itemPriceM').val());
			$topping_price = 200 * $topping_count;
		} else if ($size === 'L'){
			$size_price = parseInt($('#itemPriceL').val());
			$topping_price = 300 * $topping_count;
		}
		let price = ($size_price + $topping_price) * $quantity;
		$('#total-price').text(price.toLocaleString());
	}
});
