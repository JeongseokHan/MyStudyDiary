<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>나의 공부 일기</title>
    <link rel="stylesheet" href="/css/mainstyle.css">
</head>
<body>
<!-- 오버레이 추가 -->
<div id="overlay" class="overlay"></div>

<div class="container">
    <header>
        <nav>
            <ul>
                <!-- 로고를 왼쪽에 배치 -->
                <li class="left-item"><a href="#"><img src="/images/mainlogo.png" alt="main logo"></a></li>
                <!-- 나머지 메뉴 항목을 가운데 정렬 -->
                <li class = "menu"><a href="#">요약하기</a></li>
                <li class = "menu"><a href="#">문제풀기</a></li>
                <li class = "menu"><a href="#">일기쓰기</a></li>
                <li class = "menu"><a href="#">퀴즈내기</a></li>
                <li class = "menu"><a href="#">커뮤니티</a></li>
                <!-- 로그인을 오른쪽에 배치 -->
                <li class="right-item">
                    <a href="index.jsp" id="login-btn">
                        <img src="/images/login.png" alt="login">
                    </a>
                    <ul id="login-menu" class="login-content">
                        <li><a href="#">로그인</a></li>
                        <li><a href="#">회원가입</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>

    <div class="banner">
        <img src="/images/banner.jpg" alt="Banner Image">
    </div>

    <section class="main-features">
        <div class="feature">
            <img src="/images/diary.png" alt="일기쓰기">
            <h3>일기쓰기</h3>
        </div>
        <div class="feature">
            <img src="/images/quiz.png" alt="퀴즈내기">
            <h3>퀴즈내기</h3>
        </div>
        <div class="feature">
            <img src="/images/summary.png" alt="요약하기">
            <h3>요약하기</h3>
        </div>
    </section>

    <section class="community">
        <h2>커뮤니티</h2>
        <div class="post">
            <h3>우하하 내가 이런글이나 썼어요~~!!!</h3>
            <p>제가 1위를 차지해서 너무 신기해요. 제가 공부하다가...</p>
        </div>
        <!-- 추가적인 게시글들 -->
    </section>
</div>

<script src="/js/mainJavaScript.js"></script>
</body>
</html>
