(function () {
  $("#getAddressBtn").on("click", () => {
    $("#address").val("");
    $.ajax({
      url: "http://zipcoda.net/api",
      dataType: "jsonp",
      data: {
        zipcode: $("#zipcode").val(),
      },
      async: true,
    })
      .done((data) => {
        $("#address").val(data.items[0].address);
      })
      .fail((XMLHttpRequest, textStatus, errorThrown) => {
        alert("正しい結果を得られませんでした。");
        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });

    // 存在しない郵便番号を入力した場合にzipcodaが結果を返さずdone()の処理にも移行しないため、
    // やむなくsetTimeout()を使って結果が返ってこなかった場合の処理を記述しています
    setTimeout(() => {
      if ($("#address").val() == "") {
        $("#address").val("存在しない郵便番号です");
      }
    }, 800);
  });
})();
