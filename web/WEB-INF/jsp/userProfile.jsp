<%--
  Created by IntelliJ IDEA.
  User: Vadim
  Date: 18.10.2021
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Favicon -->
    <link rel="shortcut icon" href="<c:url value="/resources/images/logo/favicon.png"/>" type="image/x-icon">

    <!-- CSS Files -->
    <link rel="stylesheet" href="<c:url value="/resources/css/fontello.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/fontello-codes.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/fontello-embedded.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/fontello-ie7.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/fontello-ie7-codes.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/animation.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/animate-3.7.0.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-4.7.0.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/fonts/flat-icon/flaticon.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-4.1.3.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/owl-carousel.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/nice-select.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="langs.labels" var="loc"/>

    <fmt:message bundle="${loc}" key="add.ad.button" var="addButton"/>
    <fmt:message bundle="${loc}" key="logout.button" var="logout"/>
    <fmt:message bundle="${loc}" key="profile.button" var="profile"/>
    <fmt:message bundle="${loc}" key="home.button" var="home"/>

    <fmt:message bundle="${loc}" key="label.welcome" var="welcome"/>
    <fmt:message bundle="${loc}" key="label.userAds" var="userAds"/>
    <fmt:message bundle="${loc}" key="label.commentsFirst" var="commentsFirst"/>
    <fmt:message bundle="${loc}" key="label.commentsSecond" var="commentsSecond"/>
    <fmt:message bundle="${loc}" key="label.commentsThird" var="commentsThird"/>
    <fmt:message bundle="${loc}" key="label.likesFirst" var="likesFirst"/>
    <fmt:message bundle="${loc}" key="label.likesSecond" var="likesSecond"/>
    <fmt:message bundle="${loc}" key="label.likesThird" var="likesThird"/>

    <fmt:message bundle="${loc}" key="search.input.placeholder" var="searchPlaceholder"/>

    <fmt:message bundle="${loc}" key="message.searchResults" var="searchResults"/>
    <fmt:message bundle="${loc}" key="message.emptyAds.user" var="emptyAds"/>
    <fmt:message bundle="${loc}" key="message.emptyAds.continue" var="emptyAdsContinue"/>
    <c:if test="${requestScope.message ne null}">
        <fmt:message bundle="${loc}" key="${requestScope.message}" var="messageText"/>
    </c:if>

    <title>${profile}</title>
</head>
<body>

<!-- Preloader Starts -->
<div class="preloader">
    <div class="spinner"></div>
</div>
<!-- Preloader End -->

<!-- Header Area Starts -->
<header class="newsletter-area section-padding3">
    <div class="header-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <div class="logo-area">
                        <a href="Controller?command=go_to_home_page">
                            <img src="<c:url value="/resources/images/logo-light.png"/>" alt="logo">
                        </a>
                    </div>
                </div>
                <div class="col-lg-10">
                    <div class="custom-navbar">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    <div class="main-menu main-menu-light">
                        <ul>
                            <li>
                                <a href="Controller?command=ru_RU">
                                    <img src="<c:url value="/resources/images/elements/flag_russia.png"/> " height="30"
                                         width="40" alt="RU">
                                </a>
                            </li>
                            <li>
                                <a href="Controller?command=en_US">
                                    <img src="<c:url value="/resources/images/elements/flag_usa.png"/> " height="30"
                                         width="40" alt="EN">
                                </a>
                            </li>
                            <li class="menu-btn">
                                <a href="Controller?command=go_to_user_profile_page" class="login">${profile}</a>
                                <a href="Controller?command=logout" class="template-btn">${logout}</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-lg-6">
                        <div class="custom-navbar">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="main-menu main-menu-light">
                            <ul>
                                <li class="active"><a href="Controller?command=go_to_home_page">${home}</a></li>
                                <li><a href="Controller?command=go_to_add_ad_page">${addButton}</a></li>
                                <li><a href="#">FAQ</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <c:if test="${requestScope.message ne null}">
                    <div class="col-lg-6">
                        <div class="main-menu main-menu-light">
                            <ul>
                                <li style="color: #fff">
                                    <c:out value="${messageText}"/>
                                </li>
                            </ul>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="page-title text-center">
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <br>
                    <h2 id="welcome">${welcome} ${sessionScope.user.name}</h2>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header Area End -->

<div class="page-title text-center">
    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <br><br><br>
                <c:choose>
                    <c:when test="${sessionScope.searchAd ne null}">
                        <h2>${searchResults} ${sessionScope.searchAd}</h2>
                    </c:when>
                    <c:when test="${empty sessionScope.userAdsProfileList}">
                        <h2>${emptyAds}</h2>
                        <p>${emptyAdsContinue}</p>
                        <a href="Controller?command=go_to_add_ad_page" class="template-btn">${addButton}</a>
                    </c:when>
                    <c:when test="${sessionScope.userAdsProfileList ne null}">
                        <h2>${userAds}</h2>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<!-- Start blog-posts Area -->
<c:if test="${not empty sessionScope.userAdsProfileList}">
    <section class="blog-posts-area section-padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 post-list blog-post-list">
                    <c:forEach var="userAd" items="${sessionScope.userAdsProfileList}">
                        <div class="single-post">
                            <div class="row">
                                <img class="img-fluid" width="80%" src="<c:url value="${userAd.ad.picture}"/>" alt="">
                                <a href="Controller?command=go_to_edit_ad_page&editAdIdInfo=${userAd.id}"
                                   style="margin-left: 50px; color: #0b2e13">
                                    <em class="fa fa-edit fa-2x"></em>
                                </a>
                                <a href="Controller?command=delete_ad&deleteAdIdInfo=${userAd.id}"
                                   style="margin-left: 15px; color: #0b2e13">
                                    <em class="fa fa-close fa-2x"></em>
                                </a>
                            </div>
                            <ul class="tags">
                                <li>${userAd.ad.date}</li>
                            </ul>
                            <a href="Controller?command=go_to_ad_page&adIdInfo=${userAd.id}">
                                <h2>
                                        ${userAd.ad.topic}
                                </h2>
                            </a>
                            <p>
                                    ${userAd.ad.description}
                            </p>
                            <div class="bottom-meta">
                                <div class="user-details row align-items-center">
                                    <div class="comment-wrap col-lg-6">
                                        <ul>
                                            <c:choose>
                                                <c:when test="${userAd.likesCount == 0 || userAd.likesCount > 4}">
                                                    <li>
                                                        <em class="icon-heart-empty"></em> ${userAd.likesCount} ${likesFirst}
                                                    </li>
                                                </c:when>
                                                <c:when test="${userAd.likesCount == 1}">
                                                    <li>
                                                        <em class="icon-heart-empty"></em> ${userAd.likesCount} ${likesSecond}
                                                    </li>
                                                </c:when>
                                                <c:when test="${userAd.likesCount >= 2 && userAd.likesCount <= 4}">
                                                    <li>
                                                        <em class="icon-heart-empty"></em> ${userAd.likesCount} ${likesThird}
                                                    </li>
                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${userAd.commentsCount == 0 || userAd.commentsCount > 4}">
                                                    <li>
                                                        <em class="icon-comment"></em>${userAd.commentsCount} ${commentsFirst}
                                                    </li>
                                                </c:when>
                                                <c:when test="${userAd.commentsCount == 1}">
                                                    <li>
                                                        <em class="icon-comment"></em> ${userAd.commentsCount} ${commentsSecond}
                                                    </li>
                                                </c:when>
                                                <c:when test="${userAd.commentsCount >= 2 && userAd.commentsCount <= 4}">
                                                    <li>
                                                        <em class="icon-comment"></em> ${userAd.commentsCount} ${commentsThird}
                                                    </li>
                                                </c:when>
                                            </c:choose>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="col-lg-4 sidebar">
                    <div class="single-widget search-widget">
                        <form class="example" action="Controller" method="post" style="margin:auto;max-width:300px">
                            <input type="hidden" name="command" value="gotosearchadpage"/>
                            <input type="text" placeholder="${searchPlaceholder}" name="search2"
                                   onfocus="this.placeholder = ''"
                                   onblur="this.placeholder = '${searchPlaceholder}'" required>
                            <button type="submit"><em class="fa fa-search"></em></button>
                        </form>
                    </div>

                    <div class="single-widget protfolio-widget">
                        <div class="right">
                            <a href="Controller?command=go_to_edit_user_page&userId=${sessionScope.user.id}"
                               style="color: #0b2e13">
                                <em class="fa fa-edit fa-2x" style="margin-left: 95%"></em></a>
                        </div>
                        <img src="<c:url value="/resources/images/profile.png"/>" alt="userImage">
                        <h4>
                            <c:out value="${sessionScope.user.name}"/>
                            <c:out value="${sessionScope.user.phoneNumber}"/>
                            <div class="icon"><i class="fa fa-map-marker" aria-hidden="true">
                                <c:out value=" ${sessionScope.user.city.name}"/></i></div>
                        </h4>
                        <ul>
                            <c:if test="${sessionScope.user.messengers.isTelegram()}">
                                <li><em class="fab fa-telegram fa-3x"></em></li>
                            </c:if>
                            <c:if test="${sessionScope.user.messengers.isViber()}">
                                <li><em class="fab fa-viber fa-3x" aria-hidden="true"></em></li>
                            </c:if>
                            <c:if test="${sessionScope.user.messengers.isWhatsapp()}">
                                <li><em class="fab fa-whatsapp fa-3x"></em></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>
<!-- End blog-posts Area -->

<!-- Javascript -->
<script src="<c:url value="/resources/js/vendor/jquery-2.2.4.min.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/bootstrap-4.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/owl-carousel.min.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/jquery.nice-select.min.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/ion.rangeSlider.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
</body>
</html>