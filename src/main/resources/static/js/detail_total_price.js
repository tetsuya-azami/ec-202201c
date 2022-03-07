/**
 * 
 */
 'use strict';
$(function () {
  calc_price();
  $('.size').on('change', function () {
    calc_price();
  });

  $('.checkbox').on('change', function () {
    calc_price();
  });

  $('#quantity').on('change', function () {
    calc_price();
  });

  function calc_price() {
    let $size = $('.size:checked').val();
    let $topping_count = $('#topping input:checkbox:checked').length;
    let $quantity = $('#quantity').val();
    let $size_price = 0;
    let $topping_price = 0;
    if ($size === 'M') {
      $size_price = $('#itemPriceM');
      $topping_price = 200 * $topping_count;
    } else {
      $size_price = $('#itemPriceL');
      $topping_price = 300 * $topping_count;
    }
    let price = ($size_price + $topping_price) * $quantity;
    $('#totalprice').text(price.toLocaleString());
  }
});
