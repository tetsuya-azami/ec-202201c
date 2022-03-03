/**
 * パスワードの確認処理
 */
'use strict';
$(function() {

	$('#inputConfirmationPassword').on('keyup', function() {
		let conPass = $('#inputConfirmationPassword').val();
		let pass = $('#inputPassword').val();
		if (conPass != pass) {
			$('#mismatchMessage').text('パスワードが一致しません！');
			$('#btn').prop('disabled', true);
		} else {
			$('#mismatchMessage').text('OK!');
			$('#btn').prop('disabled', false);
		}
	});

		$(document).on('click','#get_address_btn', function() {
		$.ajax({
			url: 'http://zipcoda.net/api',
			dataType: 'jsonp',
			data: {
				zipcode: $('#zipcode').val(),
			},
			async: true,
		})
			.done(function(data) {
				console.log(data);
				console.dir(JSON.stringify(data));
				$('#inputAddress').val(data.items[0].address);
			})
			.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				alert('正しい結果を得られませんでした。');
				console.log('XMLHttpRequest : ' + XMLHttpRequest.status);
				console.log('textStatus     : ' + textStatus);
				console.log('errorThrown    : ' + errorThrown.message);
			});
	});


});