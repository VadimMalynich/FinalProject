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
    <link rel="stylesheet" href="<c:url value="/resources/css/animate-3.7.0.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-4.7.0.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/fonts/flat-icon/flaticon.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-4.1.3.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/owl-carousel.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/nice-select.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">

    <!-- Locale-->
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="langs.labels" var="loc"/>
    <fmt:message bundle="${loc}" key="signUp.button" var="signUp"/>
    <fmt:message bundle="${loc}" key="signIn.button" var="signIn"/>

    <fmt:message bundle="${loc}" key="label.login" var="login"/>
    <fmt:message bundle="${loc}" key="login.input.title" var="loginTitle"/>
    <fmt:message bundle="${loc}" key="login.input.placeholder" var="loginPlaceholder"/>

    <fmt:message bundle="${loc}" key="label.password" var="password"/>
    <fmt:message bundle="${loc}" key="label.confirmPassword" var="confirmPassword"/>
    <fmt:message bundle="${loc}" key="password.input.title" var="passwordTitle"/>
    <fmt:message bundle="${loc}" key="password.input.placeholder" var="passwordPlaceholder"/>
    <fmt:message bundle="${loc}" key="confirmPassword.input.placeholder" var="confirmPasswordPlaceholder"/>

    <fmt:message bundle="${loc}" key="label.name" var="name"/>
    <fmt:message bundle="${loc}" key="name.input.title" var="nameTitle"/>
    <fmt:message bundle="${loc}" key="name.input.placeholder" var="namePlaceholder"/>

    <fmt:message bundle="${loc}" key="label.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="phone.input.title" var="phoneTitle"/>
    <fmt:message bundle="${loc}" key="phone.input.placeholder" var="phonePlaceholder"/>

    <fmt:message bundle="${loc}" key="label.messengers" var="messengers"/>
    <fmt:message bundle="${loc}" key="label.location" var="location"/>

    <fmt:message bundle="${loc}" key="region.brest" var="brest"/>
    <fmt:message bundle="${loc}" key="region.vitebsk" var="vitebsk"/>
    <fmt:message bundle="${loc}" key="region.gomel" var="gomel"/>
    <fmt:message bundle="${loc}" key="region.grodno" var="grodno"/>
    <fmt:message bundle="${loc}" key="region.minsk" var="minsk"/>
    <fmt:message bundle="${loc}" key="region.mogilev" var="mogilev"/>

    <fmt:message bundle="${loc}" key="passwordsEquals" var="match"/>
    <fmt:message bundle="${loc}" key="passwordsNotEquals" var="notMatch"/>
    <c:if test="${message ne null}">
        <fmt:message bundle="${loc}" key="${message}" var="messageText"/>
    </c:if>
    <fmt:message bundle="${loc}" key="page.signUp" var="pageTitle"/>

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
                    <div class="main-menu">
                        <ul>
                            <c:if test="${message ne null}">
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
                                <a href="Controller?command=go_to_sign_in_page"
                                   class="template-btn">${signIn}</a>
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
<div class="whole-wrap">
    <div class="container">
        <div class="section-top-border">
            <div class="row">
                <div class="col-lg-4 mb-5 mb-lg-0">
                    <br>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${login}</h4>
                        </div>
                    </div>
                    <p></p>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${password}</h4>
                        </div>
                    </div>
                    <br>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${confirmPassword}</h4>
                        </div>
                    </div>
                    <p></p>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${name}</h4>
                        </div>
                    </div>
                    <br>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${phone}</h4>
                        </div>
                    </div>
                    <p></p>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${messengers}</h4>
                        </div>
                    </div>
                    <br>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${location}</h4>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="sign_up"/>
                        <div class="mt-10">
                            <input type="text"
                                   pattern="${"^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"}"
                                   title="${loginTitle}"
                                   placeholder="${loginPlaceholder}" name="signUpLogin"
                                   onfocus="this.placeholder = 'example@gmail.com'"
                                   onblur="this.placeholder = '${loginPlaceholder}'" required class="single-input">
                        </div>
                        <div class="mt-10">
                            <input type="password"
                                   pattern="${"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\w{6,20}$"}"
                                   title="${passwordTitle}"
                                   maxlength="20" placeholder="${passwordPlaceholder}" name="signUpPassword"
                                   id="newPassword"
                                   onfocus="this.placeholder = 'Password1'"
                                   onblur="this.placeholder = '${passwordPlaceholder}'" required class="single-input">
                        </div>
                        <div class="mt-10">
                            <input type="password"
                                   pattern="${"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\w{6,20}$"}"
                                   title="${passwordTitle}"
                                   maxlength="20" onChange="checkPasswordMatch();"
                                   placeholder="${confirmPasswordPlaceholder}" name="signUpConfirmPassword"
                                   id="confirmPassword"
                                   onfocus="this.placeholder = 'Password1'"
                                   onblur="this.placeholder = '${confirmPasswordPlaceholder}'" required
                                   class="single-input">
                        </div>
                        <div class="mt-10">
                            <input type="text"
                                   title="${nameTitle}"
                                   placeholder="${namePlaceholder}" name="signUpName"
                                   onfocus="this.placeholder = 'Vasya'"
                                   onblur="this.placeholder = '${namePlaceholder}'" required class="single-input">
                        </div>
                        <div class="input-group-icon mt-10">
                            <div class="icon"><i class="fa fa-phone" aria-hidden="true"></i></div>
                            <input type="text"
                                   pattern="${"^\\+375([2][459]|33|44)[1-9]\\d{6}$"}"
                                   title="${phoneTitle}"
                                   placeholder="${phonePlaceholder}" name="signUpPhone"
                                   onfocus="this.placeholder = '+375295552299'"
                                   onblur="this.placeholder = '${phonePlaceholder}'" required class="single-input">
                        </div>
                        <div class="row">
                            <div class="col-lg-4 col-md-1 mt-sm-3 element-wrap">
                                <div class="single-element-widget">
                                    <div class="switch-wrap d-flex justify-content-between">
                                        <div class="icon"><i class="fab fa-telegram fa-3x" aria-hidden="true"></i></div>
                                        <div class="confirm-checkbox">
                                            <input type="checkbox" id="default-checkbox1" name="messengers[]"
                                                   value="telegram">
                                            <label for="default-checkbox1"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-1 mt-sm-3 element-wrap">
                                <div class="single-element-widget">
                                    <div class="switch-wrap d-flex justify-content-between">
                                        <div class="icon"><i class="fab fa-viber fa-3x" aria-hidden="true"></i></div>
                                        <div class="confirm-checkbox">
                                            <input type="checkbox" id="default-checkbox2" name="messengers[]"
                                                   value="viber">
                                            <label for="default-checkbox2"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-1 mt-sm-3 element-wrap">
                                <div class="single-element-widget">
                                    <div class="switch-wrap d-flex justify-content-between">
                                        <div class="icon"><i class="fab fa-whatsapp fa-3x" aria-hidden="true"></i></div>
                                        <div class="confirm-checkbox">
                                            <input type="checkbox" id="default-checkbox3" name="messengers[]"
                                                   value="whatsapp">
                                            <label for="default-checkbox3"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="input-group-icon">
                                    <div class="icon"><i class="fa fa-globe" aria-hidden="true"></i></div>
                                    <div class="form-select" id="default-select">
                                        <select name="signUpRegion" id="region" class="nice-select list">
                                            <option value=1 class="option">${brest}</option>
                                            <option value=2 class="option">${vitebsk}</option>
                                            <option value=3 class="option">${gomel}</option>
                                            <option value=4 class="option">${grodno}</option>
                                            <option value=5 class="option">${minsk}</option>
                                            <option value=6 class="option">${mogilev}</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="input-group-icon">
                                    <div class="icon"><i class="fa fa-map-marker" aria-hidden="true"></i></div>
                                    <div class="form-select" id="default-select2">
                                        <select name="signUpCity" id="city" class="nice-select list">
                                            <c:forEach var="city" items="${citiesList}">
                                                <option value="${city.id}" class="option">${city.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div id="divCheckPasswordsMatch">${match}</div>
                        <div id="divCheckPasswordsNotMatch">${notMatch}</div>
                        <br>
                        <button type="submit" class="template-btn" id="signUpButton">${signUp}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Contact Form End -->

<!-- Javascript -->
<script src="<c:url value="/resources/js/vendor/jquery-2.2.4.min.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/jquery-3.5.0.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/bootstrap-4.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/vendor/owl-carousel.min.js"/>"></script>
<%--<script src="<c:url value="/resources/js/vendor/jquery.nice-select.min.js"/>"></script>--%>
<script src="<c:url value="/resources/js/vendor/ion.rangeSlider.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
<script src="<c:url value="/resources/js/custom.js"/>"></script>
</body>
</html>
