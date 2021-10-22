<%--
  Created by IntelliJ IDEA.
  User: Vadim
  Date: 02.10.2021
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required Meta Tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

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

    <!-- Locale -->
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="langs.labels" var="loc"/>

    <fmt:message bundle="${loc}" key="signIn.button" var="signIn"/>
    <fmt:message bundle="${loc}" key="signUp.button" var="signUp"/>
    <fmt:message bundle="${loc}" key="add.ad.button" var="addButton"/>
    <fmt:message bundle="${loc}" key="logout.button" var="logout"/>
    <fmt:message bundle="${loc}" key="profile.button" var="profile"/>
    <fmt:message bundle="${loc}" key="assortment.button" var="assortment"/>
    <fmt:message bundle="${loc}" key="users.button" var="usersButton"/>
    <fmt:message bundle="${loc}" key="home.button" var="home"/>

    <fmt:message bundle="${loc}" key="label.clothesTypes" var="types"/>
    <fmt:message bundle="${loc}" key="label.commentsFirst" var="commentsFirst"/>
    <fmt:message bundle="${loc}" key="label.commentsSecond" var="commentsSecond"/>
    <fmt:message bundle="${loc}" key="label.commentsThird" var="commentsThird"/>
    <fmt:message bundle="${loc}" key="label.likesFirst" var="likesFirst"/>
    <fmt:message bundle="${loc}" key="label.likesSecond" var="likesSecond"/>
    <fmt:message bundle="${loc}" key="label.likesThird" var="likesThird"/>

    <fmt:message bundle="${loc}" key="search.input.placeholder" var="searchPlaceholder"/>

    <fmt:message bundle="${loc}" key="page.home" var="pageTitle"/>

    <fmt:message bundle="${loc}" key="message.searchResults" var="searchResults"/>
    <fmt:message bundle="${loc}" key="message.clothesType" var="clothesTypeMessage"/>
    <fmt:message bundle="${loc}" key="message.emptyAds" var="emptyAds"/>
    <fmt:message bundle="${loc}" key="message.emptyAds.continue" var="emptyAdsContinue"/>
    <c:if test="${requestScope.message ne null}">
        <fmt:message bundle="${loc}" key="${requestScope.message}" var="messageText"/>
    </c:if>

    <!-- Page Title -->
    <title>${pageTitle}</title>
</head>
<body>
<!-- Preloader Starts -->
<div class="preloader">
    <div class="spinner"></div>
</div>
<!-- Preloader End -->

<!-- Header Area Starts -->
<header>
    <div class="header-area blog-menu">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <div class="logo-area">
                        <a href="Controller?command=go_to_home_page">
                            <img src="<c:url value="/resources/images/logo.png"/>" alt="logo">
                        </a>
                    </div>
                </div>
                <div class="col-lg-10">
                    <div class="custom-navbar">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    <div class="main-menu">
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
                            <c:choose>
                                <c:when test="${sessionScope.user eq null}">
                                    <li class="menu-btn">
                                        <a href="Controller?command=go_to_sign_in_page"
                                           class="template-btn">${signIn}</a>
                                    </li>
                                    <li>
                                        <a href="Controller?command=go_to_sign_up_page"
                                           class="template-btn">${signUp}</a>
                                    </li>
                                </c:when>
                                <c:when test="${sessionScope.user.role.value eq 0}">
                                    <a href="Controller?command=logout" class="template-btn">${logout}</a>
                                </c:when>
                                <c:when test="${sessionScope.user.role.value eq 1}">
                                    <li class="menu-btn">
                                        <a href="Controller?command=go_to_user_profile_page"
                                           class="login">${profile}</a>
                                    </li>
                                    <li>
                                        <a href="Controller?command=logout" class="template-btn">${logout}</a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-7">
                    <div class="custom-navbar">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    <div class="main-menu">
                        <ul>
                            <li class="active"><a href="Controller?command=go_to_home_page">${home}</a></li>
                            <c:choose>
                                <c:when test="${sessionScope.user eq null}">
                                    <li><a href="#">FAQ</a></li>
                                </c:when>
                                <c:when test="${sessionScope.user.role.value eq 0}">
                                    <li><a href="Controller?command=go_to_types_page">${assortment}</a></li>
                                    <li><a href="Controller?command=go_to_users_page">${usersButton}</a></li>
                                    <li><a href="#">FAQ</a></li>
                                </c:when>
                                <c:when test="${sessionScope.user.role.value eq 1}">
                                    <li><a href="Controller?command=go_to_add_ad_page">${addButton}</a></li>
                                    <li><a href="#">FAQ</a></li>
                                </c:when>
                            </c:choose>
                            <c:if test="${requestScope.message ne null}">
                                <li>
                                    <c:out value="${messageText}"/>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header Area End -->

<!-- Page Title Starts -->
<div class="page-title text-center">
    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <c:choose>
                    <c:when test="${not empty filterAdsInfoList}">
                        <h2>${clothesTypeMessage} "${filterClothesType}"</h2>
                    </c:when>
                    <c:when test="${not empty searchAdsInfoList}">
                        <h2>${searchResults} ${searchAd}</h2>
                    </c:when>
                    <c:when test="${empty adsList}">
                        <h2>${emptyAds}</h2>
                        <p>${emptyAdsContinue}</p>
                        <a href="Controller?command=go_to_add_ad_page" class="template-btn">${addButton}</a>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<!-- Page Title End -->

<!-- Start blog-posts Area -->
<c:choose>
    <c:when test="${not empty sessionScope.adsList}">
        <section class="blog-posts-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 post-list blog-post-list">
                        <c:forEach var="ad" items="${sessionScope.adsList}">
                            <div class="single-post">
                                <c:choose>
                                    <c:when test="${sessionScope.user.role.value eq 0}">
                                        <div class="row">
                                            <img class="img-fluid" width="80%" src="<c:url value="${ad.ad.picture}"/>"
                                                 alt="">
                                            <a href="Controller?command=go_to_edit_ad_page&editAdIdInfo=${ad.id}"
                                               style="margin-left: 50px; color: #0b2e13">
                                                <em class="fa fa-edit fa-2x"></em>
                                            </a>
                                            <a href="Controller?command=delete_ad&deleteAdIdInfo=${ad.id}"
                                               style="margin-left: 15px; color: #0b2e13">
                                                <em class="fa fa-close fa-2x"></em>
                                            </a>
                                        </div>
                                    </c:when>
                                    <c:when test="${sessionScope.user.role.value eq 1 or sessionScope.user == null}">
                                        <img class="img-fluid" src="<c:url value="${ad.ad.picture}"/>" alt="">
                                    </c:when>
                                </c:choose>
                                <ul class="tags">
                                    <li>
                                        <c:out value="${ad.ad.date}"/>
                                    </li>
                                </ul>
                                <a href="Controller?command=go_to_ad_page&adIdInfo=${ad.id}">
                                    <h2>
                                        <c:out value="${ad.ad.topic}"/>
                                    </h2>
                                </a>
                                <p>
                                    <c:out value="${ad.ad.description}"/>
                                </p>
                                <div class="bottom-meta">
                                    <div class="user-details row align-items-center">
                                        <div class="comment-wrap col-lg-6">
                                            <ul>
                                                <c:choose>
                                                    <c:when test="${ad.likesCount == 0 || ad.likesCount > 4}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${ad.likesCount} ${likesFirst}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${ad.likesCount == 1}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${ad.likesCount} ${likesSecond}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${ad.likesCount >= 2 && ad.likesCount <= 4}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${ad.likesCount} ${likesThird}
                                                        </li>
                                                    </c:when>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${ad.commentsCount == 0 || ad.commentsCount > 4}">
                                                        <li>
                                                            <em class="icon-comment"></em>${ad.commentsCount} ${commentsFirst}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${ad.commentsCount == 1}">
                                                        <li>
                                                            <em class="icon-comment"></em> ${ad.commentsCount} ${commentsSecond}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${ad.commentsCount >= 2 && ad.commentsCount <= 4}">
                                                        <li>
                                                            <em class="icon-comment"></em> ${ad.commentsCount} ${commentsThird}
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
                                <input type="text" placeholder="${searchPlaceholder}" name="searchAd"
                                       onfocus="this.placeholder = ''"
                                       onblur="this.placeholder = '${searchPlaceholder}'" required>
                                <button type="submit"><em class="fa fa-search"></em></button>
                            </form>
                        </div>
                        <c:if test="${sessionScope.categoryCountList ne null}">
                            <div class="single-widget category-widget">
                                <h4 class="title">${types}</h4>
                                <ul>
                                    <c:forEach var="type" items="${sessionScope.categoryCountList}">
                                        <li>
                                            <a href="Controller?command=gotofilteradpage&filterIDType=${type.id}"
                                               class="justify-content-between align-items-center d-flex">
                                                <h6>${type.category}</h6>
                                                <span>${type.count}</span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
    </c:when>
    <c:when test="${not empty filterAdsInfoList}">
        <section class="blog-posts-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 post-list blog-post-list">
                        <c:forEach var="filterAd" items="${filterAdsInfoList}">
                            <div class="single-post">
                                <img class="img-fluid" src="${filterAd.picture}" alt="">
                                <ul class="tags">
                                    <li>
                                        <c:out value="${filterAd.date}"/>
                                    </li>
                                </ul>
                                <a href="Controller?command=gotoadpage&adIDInfo=${filterAd.id}">
                                    <h2>
                                        <c:out value="${filterAd.topic}"/>
                                    </h2>
                                </a>
                                <c:if test="${filterAd.description ne null}">
                                    <p>
                                        <c:out value="${filterAd.description}"/>
                                    </p>
                                </c:if>
                                <div class="bottom-meta">
                                    <div class="user-details row align-items-center">
                                        <div class="comment-wrap col-lg-6">
                                            <ul>
                                                <c:choose>
                                                    <c:when test="${filterAd.likes == 0 || filterAd.likes > 4}">
                                                        <li><em class="icon-heart-empty"></em> ${filterAd.likes} лайков
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${filterAd.likes == 1}">
                                                        <li><em class="icon-heart-empty"></em> ${filterAd.likes} лайк
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${filterAd.likes >= 2 && filterAd.likes <= 4}">
                                                        <li><em class="icon-heart-empty"></em> ${filterAd.likes} лайка
                                                        </li>
                                                    </c:when>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${filterAd.commentsCount == 0 || filterAd.commentsCount > 4}">
                                                        <li><em class="icon-comment"></em> ${filterAd.commentsCount}
                                                            Комментариев
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${filterAd.commentsCount == 1}">
                                                        <li><em class="icon-comment"></em> ${filterAd.commentsCount}
                                                            Комментарий
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${filterAd.commentsCount >= 2 && filterAd.commentsCount <= 4}">
                                                        <li><em class="icon-comment"></em> ${filterAd.commentsCount}
                                                            Комментария
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
                                <input type="text" placeholder="Поиск объявления" name="searchAd"
                                       onfocus="this.placeholder = ''"
                                       onblur="this.placeholder = 'Поиск объявления'" required>
                                <button type="submit"><em class="fa fa-search"></em></button>
                            </form>
                        </div>
                        <c:if test="${categoryCountList ne null}">
                            <div class="single-widget category-widget">
                                <h4 class="title">Виды одежды</h4>
                                <ul>
                                    <c:forEach var="type" items="${categoryCountList}">
                                        <li>
                                            <a href="Controller?command=gotofilteradpage&filterIDType=${type.typeID}"
                                               class="justify-content-between align-items-center d-flex">
                                                <h6>${type.category}</h6>
                                                <span>${type.count}</span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
    </c:when>
    <c:when test="${not empty searchAdsInfoList}">
        <section class="blog-posts-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 post-list blog-post-list">
                        <c:forEach var="searchAds" items="${searchAdsInfoList}">
                            <div class="single-post">
                                <img class="img-fluid" src="${searchAds.picture}" alt="">
                                <ul class="tags">
                                    <li>
                                        <c:out value="${searchAds.date}"/>
                                    </li>
                                </ul>
                                <a href="Controller?command=gotoadpage&adIDInfo=${searchAds.id}">
                                    <h2>
                                        <c:out value="${searchAds.topic}"/>
                                    </h2>
                                </a>
                                <c:if test="${searchAds.description ne null}">
                                    <p>
                                        <c:out value="${searchAds.description}"/>
                                    </p>
                                </c:if>
                                <div class="bottom-meta">
                                    <div class="user-details row align-items-center">
                                        <div class="comment-wrap col-lg-6">
                                            <ul>
                                                <c:choose>
                                                    <c:when test="${searchAds.likes == 0 || searchAds.likes > 4}">
                                                        <li><em class="icon-heart-empty"></em> ${searchAds.likes} лайков
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${searchAds.likes == 1}">
                                                        <li><em class="icon-heart-empty"></em> ${searchAds.likes} лайк
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${searchAds.likes >= 2 && searchAds.likes <= 4}">
                                                        <li><em class="icon-heart-empty"></em> ${searchAds.likes} лайка
                                                        </li>
                                                    </c:when>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${searchAds.commentsCount == 0 || searchAds.commentsCount > 4}">
                                                        <li><em class="icon-comment"></em> ${searchAds.commentsCount}
                                                            Комментариев
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${searchAds.commentsCount == 1}">
                                                        <li><em class="icon-comment"></em> ${searchAds.commentsCount}
                                                            Комментарий
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${searchAds.commentsCount >= 2 && searchAds.commentsCount <= 4}">
                                                        <li><em class="icon-comment"></em> ${searchAds.commentsCount}
                                                            Комментария
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
                                <input type="text" placeholder="Поиск объявления" name="searchAd"
                                       onfocus="this.placeholder = ''"
                                       onblur="this.placeholder = 'Поиск объявления'" required>
                                <button type="submit"><em class="fa fa-search"></em></button>
                            </form>
                        </div>

                        <c:if test="${categoryCountList ne null}">
                            <div class="single-widget category-widget">
                                <h4 class="title">Виды одежды</h4>
                                <ul>
                                    <c:forEach var="type" items="${categoryCountList}">
                                        <li>
                                            <a href="Controller?command=gotofilteradpage&filterIDType=${type.typeID}"
                                               class="justify-content-between align-items-center d-flex">
                                                <h6>${type.category}</h6>
                                                <span>${type.count}</span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
    </c:when>
</c:choose>
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
