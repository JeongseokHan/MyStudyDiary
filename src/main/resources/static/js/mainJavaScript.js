document.addEventListener('DOMContentLoaded', function() {
    var loginBtn = document.getElementById("login-btn");
    var loginMenu = document.getElementById("login-menu");
    var overlay = document.getElementById("overlay");

    // 로그인 버튼 클릭 시 드롭다운과 오버레이 토글
    loginBtn.addEventListener("click", function(event) {
        event.preventDefault(); // 링크 기본 동작을 막음
        loginMenu.classList.toggle("show");
        overlay.classList.toggle("show"); // 오버레이 토글
    });

    // 페이지의 다른 부분을 클릭하면 드롭다운과 오버레이를 숨김
    window.onclick = function(event) {
        if (!event.target.matches('#login-btn img') && !loginMenu.contains(event.target)) {
            if (loginMenu.classList.contains('show')) {
                loginMenu.classList.remove('show');
                overlay.classList.remove('show'); // 오버레이 숨김
            }
        }
    };

    // 오버레이 클릭 시 드롭다운과 오버레이 숨김
    overlay.addEventListener("click", function() {
        loginMenu.classList.remove("show");
        overlay.classList.remove("show");
    });
});
