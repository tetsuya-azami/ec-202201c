(function () {
  const $logoutSpan = document.getElementById("logoutSpan");
  const $logoutForm = document.getElementById("logoutForm");
  $logoutSpan.addEventListener("click", () => {
    $logoutForm.submit();
  });
})();
