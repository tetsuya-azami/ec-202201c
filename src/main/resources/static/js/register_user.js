/**
 * パスワードの確認処理
 */
"use strict";
$(function () {
  let $conPass = $("#inputConfirmationPassword");
  let $pass = $("#password");

  $("#inputConfirmationPassword").on("keyup", function () {
    checkPass();
  });
  $("#password").on("keyup", function () {
    checkPass();
  });

  //パスワードと確認用パスワードが一致しているか確認
  const checkPass = () => {
    if ($conPass.val() != $pass.val()) {
      $("#mismatchMessage").text("パスワードが一致しません！");
      $("#btn").prop("disabled", true);
    } else {
      $("#mismatchMessage").text("OK!");
      $("#btn").prop("disabled", false);
    }
  };
});
