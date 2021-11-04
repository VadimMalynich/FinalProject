<%--
  Created by IntelliJ IDEA.
  User: Vadim
  Date: 21.10.2021
  Time: 23:08
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

    <!-- Locale-->
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="langs.labels" var="loc"/>

    <fmt:message bundle="${loc}" key="add.ad.button" var="addButton"/>
    <fmt:message bundle="${loc}" key="logout.button" var="logout"/>
    <fmt:message bundle="${loc}" key="profile.button" var="profile"/>
    <fmt:message bundle="${loc}" key="assortment.button" var="assortment"/>
    <fmt:message bundle="${loc}" key="users.button" var="usersButton"/>
    <fmt:message bundle="${loc}" key="home.button" var="home"/>
    <fmt:message bundle="${loc}" key="edit.button" var="editButton"/>

    <fmt:message bundle="${loc}" key="label.login" var="login"/>
    <fmt:message bundle="${loc}" key="label.newPassword" var="newPassword"/>
    <fmt:message bundle="${loc}" key="label.oldPassword" var="oldPassword"/>
    <fmt:message bundle="${loc}" key="password.input.title" var="passwordTitle"/>
    <fmt:message bundle="${loc}" key="oldPassword.input.placeholder" var="oldPasswordPlaceholder"/>
    <fmt:message bundle="${loc}" key="newPassword.input.placeholder" var="newPasswordPlaceholder"/>

    <fmt:message bundle="${loc}" key="label.name" var="name"/>
    <fmt:message bundle="${loc}" key="name.input.title" var="nameTitle"/>

    <fmt:message bundle="${loc}" key="label.role" var="role"/>
    <fmt:message bundle="${loc}" key="label.admin" var="adminLabel"/>
    <fmt:message bundle="${loc}" key="label.user" var="userLabel"/>

    <fmt:message bundle="${loc}" key="label.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="phone.input.title" var="phoneTitle"/>

    <fmt:message bundle="${loc}" key="label.messengers" var="messengers"/>
    <fmt:message bundle="${loc}" key="label.location" var="location"/>

    <fmt:message bundle="${loc}" key="region.brest" var="brest"/>
    <fmt:message bundle="${loc}" key="region.vitebsk" var="vitebsk"/>
    <fmt:message bundle="${loc}" key="region.gomel" var="gomel"/>
    <fmt:message bundle="${loc}" key="region.grodno" var="grodno"/>
    <fmt:message bundle="${loc}" key="region.minsk" var="minsk"/>
    <fmt:message bundle="${loc}" key="region.mogilev" var="mogilev"/>

    <fmt:message bundle="${loc}" key="page.editUser" var="pageTitle"/>

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
                                <c:when test="${sessionScope.user.role.value eq 0}">
                                    <li><a href="Controller?command=go_to_types_page">${assortment}</a></li>
                                    <li><a href="Controller?command=go_to_users_page">${usersButton}</a></li>
<%--                                    <li><a href="#">FAQ</a></li>--%>
                                </c:when>
                                <c:when test="${sessionScope.user.role.value eq 1}">
                                    <li><a href="Controller?command=go_to_add_ad_page">${addButton}</a></li>
<%--                                    <li><a href="#">FAQ</a></li>--%>
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
                            <h4>${oldPassword}</h4>
                        </div>
                    </div>
                    <br>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${newPassword}</h4>
                        </div>
                    </div>
                    <p></p>
                    <div class="d-flex">
                        <div class="info-text">
                            <h4>${name}</h4>
                        </div>
                    </div>
                    <br>
                    <c:choose>
                        <c:when test="${sessionScope.user.role.value eq 0}">
                            <div class="d-flex">
                                <div class="info-text">
                                    <h4>${role}</h4>
                                </div>
                            </div>
                            <p></p>
                            <div class="d-flex">
                                <div class="info-text">
                                    <h4>${phone}</h4>
                                </div>
                            </div>
                            <br>
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
                        </c:when>
                        <c:when test="${sessionScope.user.role.value eq 1}">
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
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-lg-5">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="edit_user"/>
                        <div class="mt-10">
                            <input type="text" value="${sessionScope.editUser.login}"
                                   pattern="${"^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"}"
                                   placeholder="${sessionScope.editUser.login}" name="editLogin"
                                   onfocus="this.placeholder = '${sessionScope.editUser.login}'"
                                   onblur="this.placeholder = '${sessionScope.editUser.login}'" disabled
                                   class="single-input">
                        </div>
                        <div class="mt-10">
                            <input type="password"
                                   pattern="${"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\w{6,20}$"}"
                                   title="${passwordTitle}" maxlength="20"
                                   placeholder="${oldPasswordPlaceholder}" name="editOldPassword"
                                   onfocus="this.placeholder = 'Password1'"
                                   onblur="this.placeholder = '${oldPasswordPlaceholder}'" class="single-input">
                        </div>
                        <div class="mt-10">
                            <input type="password"
                                   pattern="${"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\w{6,20}$"}"
                                   title="${passwordTitle}" maxlength="20"
                                   placeholder="${newPasswordPlaceholder}" name="editNewPassword"
                                   onfocus="this.placeholder = 'Password1'"
                                   onblur="this.placeholder = '${newPasswordPlaceholder}'" class="single-input">
                        </div>
                        <div class="mt-10">
                            <input type="text"
                                   title="${nameTitle}" value="${sessionScope.editUser.name}"
                                   placeholder="${sessionScope.editUser.name}" name="editName"
                                   onfocus="this.placeholder = '${sessionScope.editUser.name}'"
                                   onblur="this.placeholder = '${sessionScope.editUser.name}'" required
                                   class="single-input">
                        </div>
                        <c:if test="${sessionScope.user.role.value eq 0}">
                            <c:choose>
                                <c:when test="${sessionScope.editUser.role.value eq 0}">
                                    <div class="input-group-icon mt-10">
                                        <div class="icon"><i class="fa fa-user" aria-hidden="true"></i></div>
                                        <div class="form-select">
                                            <select name="editRole" class="nice-select list" disabled>
                                                <option value="0">${adminLabel}</option>
                                            </select>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${sessionScope.editUser.role.value eq 1}">
                                    <div class="input-group-icon mt-10">
                                        <div class="icon"><i class="fa fa-user" aria-hidden="true"></i></div>
                                        <div class="form-select">
                                            <select name="editRole" class="nice-select list">
                                                <option value="0">${adminLabel}</option>
                                                <option value="1" selected>${userLabel}</option>
                                            </select>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                        </c:if>
                        <div class="input-group-icon mt-10">
                            <div class="icon"><i class="fa fa-phone" aria-hidden="true"></i></div>
                            <input type="text"
                                   title="${phoneTitle}" value="${sessionScope.editUser.phoneNumber}"
                                   placeholder="${sessionScope.editUser.phoneNumber}" name="editPhone"
                                   onfocus="this.placeholder = '${sessionScope.editUser.phoneNumber}'"
                                   onblur="this.placeholder = '${sessionScope.editUser.phoneNumber}'" required
                                   class="single-input">
                        </div>
                        <div class="row">
                            <div class="col-lg-4 col-md-1 mt-sm-3 element-wrap">
                                <div class="single-element-widget">
                                    <div class="switch-wrap d-flex justify-content-between">
                                        <div class="icon"><i class="fab fa-telegram fa-3x" aria-hidden="true"></i></div>
                                        <div class="confirm-checkbox">
                                            <c:choose>
                                                <c:when test="${sessionScope.editUser.messengers.isTelegram()}">
                                                    <input type="checkbox" id="default-checkbox1"
                                                           name="editMessengers[]"
                                                           value="telegram" checked>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="default-checkbox1"
                                                           name="editMessengers[]"
                                                           value="telegram">
                                                </c:otherwise>
                                            </c:choose>
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
                                            <c:choose>
                                                <c:when test="${sessionScope.editUser.messengers.isViber()}">
                                                    <input type="checkbox" id="default-checkbox2"
                                                           name="editMessengers[]"
                                                           value="viber" checked>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="default-checkbox2"
                                                           name="editMessengers[]"
                                                           value="viber">
                                                </c:otherwise>
                                            </c:choose>
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
                                            <c:choose>
                                                <c:when test="${sessionScope.editUser.messengers.isWhatsapp()}">
                                                    <input type="checkbox" id="default-checkbox3"
                                                           name="editMessengers[]"
                                                           value="whatsapp" checked>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="default-checkbox3"
                                                           name="editMessengers[]"
                                                           value="whatsapp">
                                                </c:otherwise>
                                            </c:choose>
                                            <label for="default-checkbox3"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="input-group-icon">
                                    <div class="icon"><i class="fa fa-globe" aria-hidden="true"></i></div>
                                    <div class="form-select" id="default-select">
                                        <select name="region" id="region" class="nice-select list">
                                            <c:choose>
                                                <c:when test="${sessionScope.editUser.city.region.value eq 2}">
                                                    <option value="1">${brest}</option>
                                                    <option value="2" selected>${vitebsk}</option>
                                                    <option value="3">${gomel}</option>
                                                    <option value="4">${grodno}</option>
                                                    <option value="5">${minsk}</option>
                                                    <option value="6">${mogilev}</option>
                                                </c:when>
                                                <c:when test="${sessionScope.editUser.city.region.value eq 3}">
                                                    <option value="1">${brest}</option>
                                                    <option value="2">${vitebsk}</option>
                                                    <option value="3" selected>${gomel}</option>
                                                    <option value="4">${grodno}</option>
                                                    <option value="5">${minsk}</option>
                                                    <option value="6">${mogilev}</option>
                                                </c:when>
                                                <c:when test="${sessionScope.editUser.city.region.value eq 4}">
                                                    <option value="1">${brest}</option>
                                                    <option value="2">${vitebsk}</option>
                                                    <option value="3">${gomel}</option>
                                                    <option value="4" selected>${grodno}</option>
                                                    <option value="5">${minsk}</option>
                                                    <option value="6">${mogilev}</option>
                                                </c:when>
                                                <c:when test="${sessionScope.editUser.city.region.value eq 5}">
                                                    <option value="1">${brest}</option>
                                                    <option value="2">${vitebsk}</option>
                                                    <option value="3">${gomel}</option>
                                                    <option value="4">${grodno}</option>
                                                    <option value="5" selected>${minsk}</option>
                                                    <option value="6">${mogilev}</option>
                                                </c:when>
                                                <c:when test="${sessionScope.editUser.city.region.value eq 6}">
                                                    <option value="1">${brest}</option>
                                                    <option value="2">${vitebsk}</option>
                                                    <option value="3">${gomel}</option>
                                                    <option value="4">${grodno}</option>
                                                    <option value="5">${minsk}</option>
                                                    <option value="6" selected>${mogilev}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="1" selected>${brest}</option>
                                                    <option value="2">${vitebsk}</option>
                                                    <option value="3">${gomel}</option>
                                                    <option value="4">${grodno}</option>
                                                    <option value="5">${minsk}</option>
                                                    <option value="6">${mogilev}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="input-group-icon">
                                    <div class="icon"><i class="fa fa-map-marker" aria-hidden="true"></i></div>
                                    <div class="form-select" id="default-select2">
                                        <select name="editCity" id="city" class="nice-select list">
                                            <c:forEach var="city" items="${sessionScope.citiesList}">
                                                <c:choose>
                                                    <c:when test="${sessionScope.editUser.city.id eq city.id}">
                                                        <option value="${city.id}" class="option"
                                                                selected>${city.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${city.id}" class="option">${city.name}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <button type="submit" class="template-btn">${editButton}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Contact Form End -->

<div id="wrapper"></div>

<!-- Footer Area -->
<c:import url="parts/footer.jsp"/>
</body>
</html>
