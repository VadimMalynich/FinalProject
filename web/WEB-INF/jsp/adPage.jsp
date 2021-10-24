<%--
  Created by IntelliJ IDEA.
  User: Vadim
  Date: 13.10.2021
  Time: 17:19
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

    <!-- Page Title -->
    <title>${sessionScope.adPageInfo.ad.topic}</title>

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
    <fmt:message bundle="${loc}" key="comment.button" var="commentButton"/>

    <fmt:message bundle="${loc}" key="label.noComments" var="noCommentsLabel"/>
    <fmt:message bundle="${loc}" key="label.clothesType" var="typeLabel"/>
    <fmt:message bundle="${loc}" key="label.material" var="materialLabel"/>
    <fmt:message bundle="${loc}" key="label.size" var="sizeLabel"/>
    <fmt:message bundle="${loc}" key="label.sex" var="sexLabel"/>
    <fmt:message bundle="${loc}" key="label.description" var="descriptionLabel"/>
    <fmt:message bundle="${loc}" key="label.commentsFirst" var="commentsFirst"/>
    <fmt:message bundle="${loc}" key="label.commentsSecond" var="commentsSecond"/>
    <fmt:message bundle="${loc}" key="label.commentsThird" var="commentsThird"/>
    <fmt:message bundle="${loc}" key="label.likesFirst" var="likesFirst"/>
    <fmt:message bundle="${loc}" key="label.likesSecond" var="likesSecond"/>
    <fmt:message bundle="${loc}" key="label.likesThird" var="likesThird"/>
    <fmt:message bundle="${loc}" key="label.messengersInfo" var="messengersInfo"/>
    <fmt:message bundle="${loc}" key="label.sex.man" var="sexMan"/>
    <fmt:message bundle="${loc}" key="label.sex.woman" var="sexWoman"/>
    <fmt:message bundle="${loc}" key="label.sex.unisex" var="sexUnisex"/>
    <fmt:message bundle="${loc}" key="label.sex.child" var="sexChild"/>

    <fmt:message bundle="${loc}" key="comment.input.placeholder" var="commentsPlaceholder"/>

    <c:if test="${requestScope.message ne null}">
        <fmt:message bundle="${loc}" key="${requestScope.message}" var="messageText"/>
    </c:if>
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
                                    <img src="<c:url value="/resources/images/elements/flag_russia.png"/>" height="30"
                                         width="40" alt="RU">
                                </a>
                            </li>
                            <li>
                                <a href="Controller?command=en_US">
                                    <img src="<c:url value="/resources/images/elements/flag_usa.png"/>" height="30"
                                         width="40" alt="EN">
                                </a>
                            </li>
                            <c:choose>
                                <c:when test="${sessionScope.user eq null}">
                                    <li class="menu-btn">
                                        <a href="Controller?command=go_to_sign_in_page"
                                           class="template-btn">${signIn}</a>
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
                                        <a href="Controller?command=logout" class="template-btn">${logout}</a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6">
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
<%--<div class="page-title page-title-blog text-center">--%>
<%--    <div class="container">--%>
<%--        <div class="row">--%>
<%--            <div class="col-md-6 offset-md-3">--%>
<%--                <h2>Научу выживать в тяжелых условиях. </h2>--%>
<%--                <p>Тяжелые условия прилагаются.</p>--%>
<%--                <c:if test="${!isAdmin}">--%>
<%--                    <a href="Controller?command=gotoaddadpage" class="template-btn">Добавить объявление</a>--%>
<%--                </c:if>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<!-- Page Title End -->


<!-- Start blog-posts Area -->
<c:if test="${sessionScope.adPageInfo ne null}">
    <section class="blog-posts-area section-padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 post-list blog-post-list">
                    <div class="single-post">
                        <img class="img-fluid" src="<c:url value="${sessionScope.adPageInfo.ad.picture}"/>" alt="">
                        <ul class="tags">
                            <li>
                                <c:out value="${sessionScope.adPageInfo.ad.date}"/>
                            </li>
                        </ul>
                        <h2>
                            <c:out value="${sessionScope.adPageInfo.ad.topic}"/>
                        </h2>

                        <!-- Start nav Area -->
                        <section class="nav-area py-5">
                            <div class="container">
                                <div class="row justify-content-between">
                                    <div class="col-sm-6 nav-left justify-content-start d-flex">
                                        <div class="post-details">
                                            <p>${typeLabel}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 nav-right justify-content-end d-flex">
                                        <div class="post-details">
                                            <H4>
                                                <c:out value="${sessionScope.adPageInfo.categoryInfo.category}"/>
                                            </H4>
                                        </div>
                                    </div>
                                </div>
                                <div class="row justify-content-between">
                                    <div class="col-sm-6 nav-left justify-content-start d-flex">
                                        <div class="post-details">
                                            <p>${materialLabel}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 nav-right justify-content-end d-flex">
                                        <div class="post-details">
                                            <H4><c:out value="${sessionScope.adPageInfo.ad.material}"/></H4>
                                        </div>
                                    </div>
                                </div>
                                <div class="row justify-content-between">
                                    <div class="col-sm-6 nav-left justify-content-start d-flex">
                                        <div class="post-details">
                                            <p>${sizeLabel}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 nav-right justify-content-end d-flex">
                                        <div class="post-details">
                                            <H4>
                                                <c:choose>
                                                    <c:when test="${sessionScope.adPageInfo.ad.size.value eq 1}">
                                                        <c:out value="XS"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.size.value eq 2}">
                                                        <c:out value="S"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.size.value eq 3}">
                                                        <c:out value="M"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.size.value eq 4}">
                                                        <c:out value="L"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.size.value eq 5}">
                                                        <c:out value="XL"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.size.value eq 6}">
                                                        <c:out value="XXL"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.size.value eq 7}">
                                                        <c:out value="XXXL"/>
                                                    </c:when>
                                                </c:choose>
                                            </H4>
                                        </div>
                                    </div>
                                </div>
                                <div class="row justify-content-between">
                                    <div class="col-sm-6 nav-left justify-content-start d-flex">
                                        <div class="post-details">
                                            <p>${sexLabel}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 nav-right justify-content-end d-flex">
                                        <div class="post-details">
                                            <H4>
                                                <c:choose>
                                                    <c:when test="${sessionScope.adPageInfo.ad.sex.value eq 0}">
                                                        <c:out value="${sexMan}"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.sex.value eq 1}">
                                                        <c:out value="${sexWoman}"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.sex.value eq 2}">
                                                        <c:out value="${sexUnisex}"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.ad.sex.value eq 3}">
                                                        <c:out value="${sexChild}"/>
                                                    </c:when>
                                                </c:choose>
                                            </H4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <div class="content-wrap">
                            <p>${descriptionLabel}</p>
                            <blockquote class="generic-blockquote">
                                <c:out value="${sessionScope.adPageInfo.ad.description}"/>
                            </blockquote>
                        </div>
                        <div class="bottom-meta">
                            <div class="user-details row align-items-center">
                                <div class="comment-wrap col-lg-6 col-sm-6">
                                    <ul>
                                        <c:choose>
                                            <c:when test="${sessionScope.user eq null}">
                                                <c:choose>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount == 0 || sessionScope.adPageInfo.likesCount > 4}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${sessionScope.adPageInfo.likesCount} ${likesFirst}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount == 1}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${sessionScope.adPageInfo.likesCount} ${likesSecond}
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount >= 2 && sessionScope.adPageInfo.likesCount <= 4}">
                                                        <li>
                                                            <em class="icon-heart-empty"></em> ${sessionScope.adPageInfo.likesCount} ${likesThird}
                                                        </li>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                            <c:when test="${sessionScope.isLike eq true}">
                                                <c:choose>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount == 0 || sessionScope.adPageInfo.likesCount > 4}">
                                                        <li>
                                                            <a href="Controller?command=like_ad">
                                                                <em class="icon-heart"></em> ${sessionScope.adPageInfo.likesCount} ${likesFirst}
                                                            </a>
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount == 1}">
                                                        <li>
                                                            <a href="Controller?command=like_ad">
                                                                <em class="icon-heart"></em> ${sessionScope.adPageInfo.likesCount} ${likesSecond}
                                                            </a>
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount >= 2 && sessionScope.adPageInfo.likesCount <= 4}">
                                                        <li>
                                                            <a href="Controller?command=like_ad">
                                                                <em class="icon-heart"></em> ${sessionScope.adPageInfo.likesCount} ${likesThird}
                                                            </a>
                                                        </li>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                            <c:when test="${sessionScope.isLike eq false}">
                                                <c:choose>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount == 0 || sessionScope.adPageInfo.likesCount > 4}">
                                                        <li>
                                                            <a href="Controller?command=like_ad">
                                                                <em class="icon-heart-empty"></em> ${sessionScope.adPageInfo.likesCount} ${likesFirst}
                                                            </a>
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount == 1}">
                                                        <li>
                                                            <a href="Controller?command=like_ad">
                                                                <em class="icon-heart-empty"></em> ${sessionScope.adPageInfo.likesCount} ${likesSecond}
                                                            </a>
                                                        </li>
                                                    </c:when>
                                                    <c:when test="${sessionScope.adPageInfo.likesCount >= 2 && sessionScope.adPageInfo.likesCount <= 4}">
                                                        <li>
                                                            <a href="Controller?command=like_ad">
                                                                <em class="icon-heart-empty"></em> ${sessionScope.adPageInfo.likesCount} ${likesThird}
                                                            </a>
                                                        </li>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                        </c:choose>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!-- Start comment-sec Area -->
                        <section class="comment-sec-area py-5">
                            <div class="container">
                                <div class="row flex-column">
                                    <c:choose>
                                        <c:when test="${sessionScope.adPageInfo.commentsCount == 0 || sessionScope.adPageInfo.commentsCount > 4}">
                                            <h5 class="text-uppercase pb-80">
                                                <em class="icon-comment"></em> ${sessionScope.adPageInfo.commentsCount} ${commentsFirst}
                                            </h5>
                                        </c:when>
                                        <c:when test="${sessionScope.adPageInfo.commentsCount == 1}">
                                            <h5 class="text-uppercase pb-80">
                                                <em class="icon-comment"></em> ${sessionScope.adPageInfo.commentsCount} ${commentsSecond}
                                            </h5>
                                        </c:when>
                                        <c:when test="${sessionScope.adPageInfo.commentsCount >= 2 && sessionScope.adPageInfo.commentsCount <= 4}">
                                            <h5 class="text-uppercase pb-80">
                                                <em class="icon-comment"></em> ${sessionScope.adPageInfo.commentsCount} ${commentsThird}
                                            </h5>
                                        </c:when>
                                    </c:choose>
                                    <br>
                                    <c:if test="${empty sessionScope.commentsList}">
                                        <c:if test="${sessionScope.user ne null}">
                                            <h5 class="text-uppercase pb-80">${noCommentsLabel}</h5>
                                        </c:if>
                                    </c:if>
                                    <c:forEach var="comment" items="${sessionScope.commentsList}">
                                        <div class="comment-list">
                                            <div class="single-comment justify-content-between d-flex">
                                                <div class="user justify-content-between d-flex">
                                                    <div class="thumb">
                                                        <img src="<c:url value="/resources/images/comment_user_profile.png"/>"
                                                             alt="">
                                                    </div>
                                                    <div class="desc">
                                                        <h5>${comment.user.login}</h5>
                                                        <p class="date">${comment.commentDate}</p>
                                                        <p class="comment">
                                                            <c:out value="${comment.commentText}"/>
                                                        </p>
                                                    </div>
                                                </div>
                                                <c:choose>
                                                    <c:when test="${sessionScope.user.role.value eq 0}">
                                                        <a href="Controller?command=delete_comment&adIdInfo=${sessionScope.adPageInfo.id}&commentId=${comment.id}"
                                                           style="color: #0b2e13"><em
                                                                class="fa fa-close fa-2x"></em></a>
                                                    </c:when>
                                                    <c:when test="${sessionScope.user.role.value eq 1 and sessionScope.user.id eq comment.user.id}">
                                                        <a href="Controller?command=delete_comment&adIdInfo=${sessionScope.adPageInfo.id}&commentId=${comment.id}"
                                                           style="color: #0b2e13"><em
                                                                class="fa fa-close fa-2x"></em></a>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </section>
                        <!-- End comment-sec Area -->

                        <!-- Start commentform Area -->
                        <c:if test="${sessionScope.user ne null}">
                            <div class="commentform-area py-5">
                                <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="add_comment"/>
                                    <div class="py-5">
                                <textarea name="userComment" cols="82" rows="5" placeholder="${commentsPlaceholder}"
                                          maxlength="200"
                                          onfocus="this.placeholder = 'Great announcement!'"
                                          onblur="this.placeholder = '${commentsPlaceholder}'"
                                          required></textarea>
                                    </div>
                                    <button type="submit" class="template-btn">${commentButton}</button>
                                </form>
                            </div>
                        </c:if>
                        <!-- End commentform Area -->
                    </div>
                </div>
                <div class="col-lg-4 sidebar mt-5 mt-lg-0">
                    <div class="single-widget protfolio-widget">
                        <img src="<c:url value="/resources/images/man-in-suit-and-tie.png"/>" alt="seller">
                        <h4>
                            <c:out value="${sessionScope.adPageInfo.userInfo.name}"/>
                            <c:out value="${sessionScope.adPageInfo.userInfo.phoneNumber}"/>
                            <div class="icon"><i class="fa fa-map-marker" aria-hidden="true">
                                <c:out value=" ${sessionScope.adPageInfo.userInfo.city.name}"/></i></div>
                        </h4>
                        <p>
                                ${messengersInfo}
                        </p>
                        <ul>
                            <c:if test="${sessionScope.adPageInfo.userInfo.messengers.isTelegram()}">
                                <li><em class="fab fa-telegram fa-3x"></em></li>
                            </c:if>
                            <c:if test="${sessionScope.adPageInfo.userInfo.messengers.isViber()}">
                                <li><em class="fab fa-viber fa-3x" aria-hidden="true"></em></li>
                            </c:if>
                            <c:if test="${sessionScope.adPageInfo.userInfo.messengers.isWhatsapp()}">
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

<div id="wrapper"></div>

<!-- Footer Area -->
<c:import url="parts/footer.jsp"/>
</body>
</html>

