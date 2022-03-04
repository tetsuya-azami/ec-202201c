/**
 * パスワードの確認処理
 */
"use strict";
$(function () {
  $("#inputConfirmationPassword").on("keyup", function () {
    let conPass = $("#inputConfirmationPassword").val();
    let pass = $("#inputPassword").val();
    if (conPass != pass) {
      $("#mismatchMessage").text("パスワードが一致しません！");
      $("#btn").prop("disabled", true);
    } else {
      $("#mismatchMessage").text("OK!");
      $("#btn").prop("disabled", false);
    }
  });
});
