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
    <!-- Favicon -->
    <link rel="shortcut icon" href="<c:url value="/resources/images/logo/favicon.png"/>" type="image/x-icon">

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
<c:import url="parts/header.jsp"/>

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
                    <c:when test="${sessionScope.filterClothesType ne null}">
                        <h2>${clothesTypeMessage} "${sessionScope.filterClothesType}"</h2>
                    </c:when>
                    <c:when test="${sessionScope.searchAd ne null}">
                        <h2>${searchResults} ${sessionScope.searchAd}</h2>
                    </c:when>
                    <c:when test="${empty sessionScope.adsList}">
                        <h2>${emptyAds}</h2>
                        <p>${emptyAdsContinue}</p>
                        <c:if test="${sessionScope.user.role.value eq 1}">
                            <a href="Controller?command=go_to_add_ad_page" class="template-btn">${addButton}</a>
                        </c:if>
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
                                <input type="hidden" name="command" value="search_ads"/>
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
                                            <a href="Controller?command=filter_ads&filterIdType=${type.id}"
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
    <c:when test="${not empty sessionScope.filterAdsList}">
        <section class="blog-posts-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 post-list blog-post-list">
                        <c:forEach var="filterAd" items="${sessionScope.filterAdsList}">
                            <div class="single-post">
                                <c:choose>
                                    <c:when test="${sessionScope.user.role.value eq 0}">
                                        <div class="row">
                                            <img class="img-fluid" width="80%"
                                                 src="<c:url value="${filterAd.ad.picture}"/>"
                                                 alt="">
                                            <a href="Controller?command=go_to_edit_ad_page&editAdIdInfo=${filterAd.id}"
                                               style="margin-left: 50px; color: #0b2e13">
                                                <em class="fa fa-edit fa-2x"></em>
                                            </a>
                                            <a href="Controller?command=delete_ad&deleteAdIdInfo=${filterAd.id}"
                                               style="margin-left: 15px; color: #0b2e13">
                                                <em class="fa fa-close fa-2x"></em>
                                            </a>
                                        </div>
                                    </c:when>
                                    <c:when test="${sessionScope.user.role.value eq 1 or sessionScope.user == null}">
                                        <img class="img-fluid" src="<c:url value="${filterAd.ad.picture}"/>" alt="">
                                    </c:when>
                                </c:choose>
                                <ul class="tags">
                                    <li>
                                        <c:out value="${filterAd.ad.date}"/>
                                    </li>
                                </ul>
                                <a href="Controller?command=go_to_ad_page&adIdInfo=${filterAd.id}">
                                    <h2>
                                        <c:out value="${filterAd.ad.topic}"/>
                                    </h2>
                                </a>
                                <p>
                                    <c:out value="${filterAd.ad.description}"/>
                                </p>
                                <div class="bottom-meta">
                                    <div class="user-details row align-items-center">
                                        <div class="comment-wrap col-lg-6">
                                            <ul>
                                                <c:choose>
                                                    <c:when test="${filterAd.likesCount == 0 || filterAd.likesCount > 4}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${filterAd.likesCount} ${likesFirst}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${filterAd.likesCount == 1}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${filterAd.likesCount} ${likesSecond}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${filterAd.likesCount >= 2 && filterAd.likesCount <= 4}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${filterAd.likesCount} ${likesThird}
                                                        </li>
                                                    </c:when>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${filterAd.commentsCount == 0 || filterAd.commentsCount > 4}">
                                                        <li>
                                                            <em class="icon-comment"></em>${filterAd.commentsCount} ${commentsFirst}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${filterAd.commentsCount == 1}">
                                                        <li>
                                                            <em class="icon-comment"></em> ${filterAd.commentsCount} ${commentsSecond}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${filterAd.commentsCount >= 2 && filterAd.commentsCount <= 4}">
                                                        <li>
                                                            <em class="icon-comment"></em> ${filterAd.commentsCount} ${commentsThird}
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
                                <input type="hidden" name="command" value="search_ads"/>
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
                                            <a href="Controller?command=filter_ads&filterIdType=${type.id}"
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
    <c:when test="${not empty sessionScope.searchAdsList}">
        <section class="blog-posts-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 post-list blog-post-list">
                        <c:forEach var="searchAd" items="${sessionScope.searchAdsList}">
                            <div class="single-post">
                                <c:choose>
                                    <c:when test="${sessionScope.user.role.value eq 0}">
                                        <div class="row">
                                            <img class="img-fluid" width="80%"
                                                 src="<c:url value="${searchAd.ad.picture}"/>"
                                                 alt="">
                                            <a href="Controller?command=go_to_edit_ad_page&editAdIdInfo=${searchAd.id}"
                                               style="margin-left: 50px; color: #0b2e13">
                                                <em class="fa fa-edit fa-2x"></em>
                                            </a>
                                            <a href="Controller?command=delete_ad&deleteAdIdInfo=${searchAd.id}"
                                               style="margin-left: 15px; color: #0b2e13">
                                                <em class="fa fa-close fa-2x"></em>
                                            </a>
                                        </div>
                                    </c:when>
                                    <c:when test="${sessionScope.user.role.value eq 1 or sessionScope.user == null}">
                                        <img class="img-fluid" src="<c:url value="${searchAd.ad.picture}"/>" alt="">
                                    </c:when>
                                </c:choose>
                                <ul class="tags">
                                    <li>
                                        <c:out value="${searchAd.ad.date}"/>
                                    </li>
                                </ul>
                                <a href="Controller?command=go_to_ad_page&adIdInfo=${searchAd.id}">
                                    <h2>
                                        <c:out value="${searchAd.ad.topic}"/>
                                    </h2>
                                </a>
                                <p>
                                    <c:out value="${searchAd.ad.description}"/>
                                </p>
                                <div class="bottom-meta">
                                    <div class="user-details row align-items-center">
                                        <div class="comment-wrap col-lg-6">
                                            <ul>
                                                <c:choose>
                                                    <c:when test="${searchAd.likesCount == 0 || searchAd.likesCount > 4}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${searchAd.likesCount} ${likesFirst}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${searchAd.likesCount == 1}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${searchAd.likesCount} ${likesSecond}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${searchAd.likesCount >= 2 && searchAd.likesCount <= 4}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${searchAd.likesCount} ${likesThird}
                                                        </li>
                                                    </c:when>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${searchAd.commentsCount == 0 || searchAd.commentsCount > 4}">
                                                        <li>
                                                            <em class="icon-comment"></em>${searchAd.commentsCount} ${commentsFirst}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${searchAd.commentsCount == 1}">
                                                        <li>
                                                            <em class="icon-comment"></em> ${searchAd.commentsCount} ${commentsSecond}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${searchAd.commentsCount >= 2 && searchAd.commentsCount <= 4}">
                                                        <li>
                                                            <em class="icon-comment"></em> ${searchAd.commentsCount} ${commentsThird}
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
                                <input type="hidden" name="command" value="search_ads"/>
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
                                            <a href="Controller?command=filter_ads&filterIdType=${type.id}"
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

<div id="wrapper"></div>

<!-- Footer Area -->
<c:import url="parts/footer.jsp"/>
</body>
</html>
