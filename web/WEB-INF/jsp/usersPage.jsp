<%--
  Created by IntelliJ IDEA.
  User: Vadim
  Date: 21.10.2021
  Time: 0:28
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

    <fmt:message bundle="${loc}" key="logout.button" var="logout"/>
    <fmt:message bundle="${loc}" key="assortment.button" var="assortment"/>
    <fmt:message bundle="${loc}" key="home.button" var="home"/>
    <fmt:message bundle="${loc}" key="users.button" var="usersButton"/>

    <fmt:message bundle="${loc}" key="label.login" var="loginLabel"/>
    <fmt:message bundle="${loc}" key="label.name" var="nameLabel"/>
    <fmt:message bundle="${loc}" key="label.phone" var="phoneLabel"/>
    <fmt:message bundle="${loc}" key="label.role" var="roleLabel"/>
    <fmt:message bundle="${loc}" key="label.city" var="cityLabel"/>
    <fmt:message bundle="${loc}" key="label.admin" var="adminLabel"/>
    <fmt:message bundle="${loc}" key="label.user" var="userLabel"/>
    <fmt:message bundle="${loc}" key="label.tableMessengers" var="messengersLabel"/>

    <fmt:message bundle="${loc}" key="message.emptyUsers" var="emptyUsers"/>
    <c:if test="${requestScope.message ne null}">
        <fmt:message bundle="${loc}" key="${requestScope.message}" var="messageText"/>
    </c:if>

    <title>${usersButton}</title>
</head>
<body>
<c:import url="parts/header.jsp"/>

<!-- Preloader Starts -->
<div class="preloader">
    <div class="spinner"></div>
</div>
<!-- Preloader End -->

<!-- Header Area Starts -->
<header class="header-area single-page">
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
                                <a href="Controller?command=logout" class="template-btn">${logout}</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-lg-8">
                        <div class="custom-navbar">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="main-menu main-menu-light">
                            <ul>
                                <li class="active"><a href="Controller?command=go_to_home_page">${home}</a></li>
                                <li><a href="Controller?command=go_to_types_page">${assortment}</a></li>
                                <li><a href="Controller?command=go_to_users_page">${usersButton}</a></li>
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
                    <c:if test="${not empty sessionScope.usersList}">
                        <h2><c:out value="${usersButton}"/></h2>
                    </c:if>
                    <c:if test="${empty sessionScope.usersList}">
                        <p><c:out value="${emptyUsers}"/></p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header Area End -->


<!-- Start Align Area -->
<c:if test="${not empty sessionScope.usersList}">
    <div class="whole-wrap">
            <%--        <div class="container">--%>
        <div class="section-top-border">
            <div class="progress-table-wrap">
                <div class="progress-table">
                    <div class="table-head">
                        <div class="serial">ID</div>
                        <div class="percentage">${loginLabel}</div>
                        <div class="country">${nameLabel}</div>
                        <div class="country">${phoneLabel}</div>
                        <div class="visit">${roleLabel}</div>
                        <div class="visit">${cityLabel}</div>
                        <div class="visit">${messengersLabel}</div>
                        <div class="visit"></div>
                    </div>
                    <c:forEach var="user" items="${sessionScope.usersList}">
                        <div class="table-row">
                            <div class="serial">${user.id}</div>
                            <div class="percentage">${user.login}</div>
                            <div class="country">${user.name}</div>
                            <div class="country">${user.phoneNumber}</div>
                            <c:choose>
                                <c:when test="${user.role.value eq 0}">
                                    <div class="visit">${adminLabel}</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="visit">${userLabel}</div>
                                </c:otherwise>
                            </c:choose>
                            <div class="visit">${user.city.name}</div>
                            <div class="visit">
                                <c:if test="${user.messengers.isTelegram()}">
                                    <em class="fab fa-telegram fa-2x"></em>
                                </c:if>
                                <c:if test="${user.messengers.isViber()}">
                                    <em class="fab fa-viber fa-2x" aria-hidden="true"></em>
                                </c:if>
                                <c:if test="${user.messengers.isWhatsapp()}">
                                    <em class="fab fa-whatsapp fa-2x"></em>
                                </c:if>
                            </div>
                            <div class="visit">
                                <button type="button" style="margin-left: 50px;">
                                    <a href="Controller?command=go_to_edit_user_page&userId=${user.id}"
                                       style="color: #0b2e13">
                                        <em class="fa fa-edit fa-2x"></em></a>
                                </button>
                                <form action="Controller" method="post" style="display: inherit">
                                    <input type="hidden" name="command" value="delete_user">
                                    <input type="hidden" name="deleteUserId" value="${user.id}">
                                    <div class="serial">
                                        <button type="submit">
                                            <em class="fa fa-close fa-2x"></em>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
            <%--        </div>--%>
    </div>
</c:if>
<!-- End Align Area -->

<div id="wrapper"></div>

<!-- Footer Area -->
<c:import url="parts/footer.jsp"/>
</body>
</html>

