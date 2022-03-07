(function () {
  //SpringSecurityの関係でPOSTメソッドでログアウトをし、かつ表示崩れを起こさないための記述
  const $logoutSpan = document.getElementById("logoutSpan");
  const $logoutForm = document.getElementById("logoutForm");
  $logoutSpan.addEventListener("click", () => {
    $logoutForm.submit();
  });

  //SpringBootのFormで保持している値がresetボタンで消えないため、JavaScriptで消す
  const $resetButton = document.getElementById("resetButton");
  const $fuzzySearchName = document.getElementById("fuzzySearchName");
  $resetButton.addEventListener("click", () => {
    $fuzzySearchName.value = "";
  });
})();
