<%--
  Created by IntelliJ IDEA.
  User: Vadim
  Date: 01.05.2021
  Time: 23:29
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

    <style>
        p {
            font-family: Verdana, Arial, Helvetica, sans-serif;
            font-size: 1pt; /* Размер шрифта в пунктах */
        }
    </style>


    <!-- Locale -->
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="langs.labels" var="loc"/>

    <fmt:message bundle="${loc}" key="signIn.button" var="signIn"/>
    <fmt:message bundle="${loc}" key="signUp.button" var="signUp"/>

    <fmt:message bundle="${loc}" key="label.login" var="login"/>
    <fmt:message bundle="${loc}" key="label.password" var="password"/>

    <fmt:message bundle="${loc}" key="login.input.title" var="loginTitle"/>
    <fmt:message bundle="${loc}" key="password.input.title" var="passwordTitle"/>

    <fmt:message bundle="${loc}" key="login.input.placeholder" var="loginPlaceholder"/>
    <fmt:message bundle="${loc}" key="password.input.placeholder" var="passwordPlaceholder"/>

    <fmt:message bundle="${loc}" key="page.signIn" var="pageTitle"/>

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
    <div class="header-top">
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
                    <div class="main-menu main-menu-light">
                        <ul>
                            <c:if test="${requestScope.message ne null}">
                                <li>
                                    <c:out value="${messageText}"/>
                                </li>
                            </c:if>
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
                                <a href="Controller?command=go_to_sign_up_page"
                                   class="template-btn">${signUp}</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header Area End -->

<!-- Contact Form Starts -->
<section class="contact-form section-padding3">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 mb-5 mb-lg-0">
                <div class="d-flex">
                    <div class="info-text">
                        <p></p>
                        <h4>${login}</h4>
                        <p></p>
                    </div>
                </div>
                <br>
                <div class="d-flex">
                    <div class="info-text">
                        <p></p>
                        <h4>${password}</h4>
                    </div>
                </div>
            </div>
            <div class="col-lg-9">
                <form action="Controller" method="post">
                    <div class="left">
                        <input type="hidden" name="command" value="sign_in"/>
                        <input type="text"
                               pattern="${"^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"}"
                               title="${loginTitle}"
                               placeholder="${loginPlaceholder}" name="login"
                               onfocus="this.placeholder = 'example@gmail.com'"
                               onblur="this.placeholder = '${loginPlaceholder}'" required>
                        <p></p>
                        <input type="password"
                               pattern="${"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\w{6,20}$"}"
                               title="${passwordTitle}"
                               maxlength="20" placeholder="${passwordPlaceholder}" name="password" id="newPassword"
                               onfocus="this.placeholder = 'Password1'"
                               onblur="this.placeholder = '${passwordPlaceholder}'" required>
                    </div>
                    <br>
                    <button type="submit" class="template-btn">${signIn}</button>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- Contact Form End -->

<div id="wrapper"></div>

<!-- Footer Area -->
<c:import url="parts/footer.jsp"/>
</body>
</html>