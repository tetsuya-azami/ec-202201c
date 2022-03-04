$(document).on("click", "#getAddressBtn", function () {
  $.ajax({
    url: "http://zipcoda.net/api",
    dataType: "jsonp",
    data: {
      zipcode: $("#inputZipcode").val(),
    },
    async: true,
  })
    .done(function (data) {
      console.log(data);
      console.dir(JSON.stringify(data));
      $("#address").val(data.items[0].address);
    })
    .fail(function (XMLHttpRequest, textStatus, errorThrown) {
      alert("正しい結果を得られませんでした。");
      console.log("XMLHttpRequest : " + XMLHttpRequest.status);
      console.log("textStatus     : " + textStatus);
      console.log("errorThrown    : " + errorThrown.message);
    });
});
