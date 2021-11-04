<%--
  Created by IntelliJ IDEA.
  User: Vadim
  Date: 19.10.2021
  Time: 17:54
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

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="langs.labels" var="loc"/>

    <fmt:message bundle="${loc}" key="logout.button" var="logout"/>
    <fmt:message bundle="${loc}" key="profile.button" var="profile"/>
    <fmt:message bundle="${loc}" key="assortment.button" var="assortment"/>
    <fmt:message bundle="${loc}" key="home.button" var="home"/>
    <fmt:message bundle="${loc}" key="users.button" var="usersButton"/>
    <fmt:message bundle="${loc}" key="add.ad.button" var="addAdButton"/>
    <fmt:message bundle="${loc}" key="edit.ad.button" var="editAdButton"/>

    <fmt:message bundle="${loc}" key="label.clothesType" var="typeLabel"/>
    <fmt:message bundle="${loc}" key="label.topic" var="topicLabel"/>
    <fmt:message bundle="${loc}" key="label.material" var="materialLabel"/>
    <fmt:message bundle="${loc}" key="label.size" var="sizeLabel"/>
    <fmt:message bundle="${loc}" key="label.sex" var="sexLabel"/>
    <fmt:message bundle="${loc}" key="label.sex.man" var="sexMan"/>
    <fmt:message bundle="${loc}" key="label.sex.woman" var="sexWoman"/>
    <fmt:message bundle="${loc}" key="label.sex.unisex" var="sexUnisex"/>
    <fmt:message bundle="${loc}" key="label.sex.child" var="sexChild"/>
    <fmt:message bundle="${loc}" key="label.description" var="descriptionLabel"/>
    <fmt:message bundle="${loc}" key="label.photo" var="photoLabel"/>

    <fmt:message bundle="${loc}" key="page.editAd" var="pageTitle"/>
    <c:if test="${requestScope.message ne null}">
        <fmt:message bundle="${loc}" key="${requestScope.message}" var="messageText"/>
    </c:if>

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

<header class="header-area single-page">
    <div class="header-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <div class="logo-area">
                        <a href="Controller?command=go_to_home_page">
                            <img src="<c:url value="/resources/images/logo-light.png"/>"
                                 alt="logo">
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
                                    <img src="<c:url value="/resources/images/elements/flag_russia.png"/> "
                                         height="30"
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
                    <div class="col-lg-6">
                        <div class="custom-navbar">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="main-menu main-menu-light">
                            <ul>
                                <li class="active"><a href="Controller?command=go_to_home_page">${home}</a></li>
                                <c:choose>
                                    <c:when test="${sessionScope.user.role.value eq 0}">
                                        <li><a href="Controller?command=go_to_types_page">${assortment}</a></li>
                                        <li><a href="Controller?command=go_to_users_page">${usersButton}</a></li>
<%--                                        <li><a href="#">FAQ</a></li>--%>
                                    </c:when>
                                    <c:when test="${sessionScope.user.role.value eq 1}">
                                        <li><a href="Controller?command=go_to_add_ad_page">${addAdButton}</a></li>
<%--                                        <li><a href="#">FAQ</a></li>--%>
                                    </c:when>
                                </c:choose>
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
        <div class="page-title text-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <h2>${pageTitle}</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header Area End -->

<!-- Start Align Area -->
<div class="whole-wrap">
    <div class="container">
        <div class="section-top-border">
            <%--            <h3 class="mb-30 title_color">Форма для изменения объявления</h3>--%>
            <div class="row">
                <div class="col-lg-3 mb-5 mb-lg-0">
                    <br>
                    <div class="d-flex">
                        <div class="info-text">
                            <h5>${typeLabel}</h5>
                        </div>
                    </div>
                    <div class="d-flex">
                        <div class="info-text">
                            <p></p>
                            <h5>${topicLabel}</h5>
                            <p></p>
                        </div>
                    </div>
                    <div class="d-flex">
                        <div class="info-text">
                            <p></p>
                            <h5>${materialLabel}</h5>
                            <p></p>
                        </div>
                    </div>
                    <div class="d-flex">
                        <div class="info-text">
                            <p></p>
                            <h5>${sizeLabel}</h5>
                            <p></p>
                        </div>
                    </div>
                    <div class="d-flex">
                        <div class="info-text">
                            <p></p>
                            <h5>${sexLabel}</h5>
                            <p></p>
                        </div>
                    </div>
                    <div class="d-flex">
                        <div class="info-text">
                            <p></p>
                            <h5>${descriptionLabel}</h5>
                        </div>
                    </div>
                    <div class="d-flex">
                        <div class="info-text">
                            <br><br><br><br><br><br><br>
                            <h5>${photoLabel}</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <form action="Controller" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="edit_ad"/>
                        <div class="input-group-icon mt-10">
                            <div class="icon"><i class="fa fa-gg" aria-hidden="true"></i></div>
                            <div class="form-select" id="default-select">
                                <select name="editType">
                                    <c:forEach var="type" items="${sessionScope.clothesTypeList}">
                                        <c:choose>
                                            <c:when test="${type.id==sessionScope.editAd.categoryInfo.id}">
                                                <option value="${type.id}" selected>${type.category}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${type.id}">${type.category}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="mt-10">
                            <input type="text" name="editTopic" value="${sessionScope.editAd.ad.topic}" maxlength="50"
                                   placeholder="${sessionScope.editAd.ad.topic}"
                                   onfocus="this.placeholder = '${sessionScope.editAd.ad.topic}'"
                                   onblur="this.placeholder = '${sessionScope.editAd.ad.topic}'" class="single-input">
                        </div>
                        <div class="mt-10">
                            <input type="text" name="editMaterial" value="${sessionScope.editAd.ad.material}"
                                   placeholder="${sessionScope.editAd.ad.material}"
                                   onfocus="this.placeholder = '${sessionScope.editAd.ad.material}'"
                                   onblur="this.placeholder = '${sessionScope.editAd.ad.material}'"
                                   class="single-input">
                        </div>
                        <div class="input-group-icon mt-10">
                            <div class="icon"><i class="fa fa-gg-circle" aria-hidden="true"></i></div>
                            <div class="form-select" id="default-select2">
                                <select name="editSize">
                                    <c:choose>
                                        <c:when test="${sessionScope.editAd.ad.size.value eq 1}">
                                            <option value="1" selected>XS</option>
                                            <option value="2">S</option>
                                            <option value="3">M</option>
                                            <option value="4">L</option>
                                            <option value="5">XL</option>
                                            <option value="6">XXL</option>
                                            <option value="7">XXXL</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.size.value eq 2}">
                                            <option value="1">XS</option>
                                            <option value="2" selected>S</option>
                                            <option value="3">M</option>
                                            <option value="4">L</option>
                                            <option value="5">XL</option>
                                            <option value="6">XXL</option>
                                            <option value="7">XXXL</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.size.value eq 3}">
                                            <option value="1">XS</option>
                                            <option value="2">S</option>
                                            <option value="3" selected>M</option>
                                            <option value="4">L</option>
                                            <option value="5">XL</option>
                                            <option value="6">XXL</option>
                                            <option value="7">XXXL</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.size.value eq 4}">
                                            <option value="1">XS</option>
                                            <option value="2">S</option>
                                            <option value="3">M</option>
                                            <option value="4" selected>L</option>
                                            <option value="5">XL</option>
                                            <option value="6">XXL</option>
                                            <option value="7">XXXL</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.size.value eq 5}">
                                            <option value="1">XS</option>
                                            <option value="2">S</option>
                                            <option value="3">M</option>
                                            <option value="4">L</option>
                                            <option value="5" selected>XL</option>
                                            <option value="6">XXL</option>
                                            <option value="7">XXXL</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.size.value eq 6}">
                                            <option value="1">XS</option>
                                            <option value="2">S</option>
                                            <option value="3">M</option>
                                            <option value="4">L</option>
                                            <option value="5">XL</option>
                                            <option value="6" selected>XXL</option>
                                            <option value="7">XXXL</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.size.value eq 7}">
                                            <option value="1">XS</option>
                                            <option value="2">S</option>
                                            <option value="3">M</option>
                                            <option value="4">L</option>
                                            <option value="5">XL</option>
                                            <option value="6">XXL</option>
                                            <option value="7" selected>XXXL</option>
                                        </c:when>
                                    </c:choose>
                                </select>
                            </div>
                        </div>
                        <div class="input-group-icon mt-10">
                            <div class="icon"><i class="fa fa-intersex" aria-hidden="true"></i></div>
                            <div class="form-select" id="default-select3">
                                <select name="editSex">
                                    <c:choose>
                                        <c:when test="${sessionScope.editAd.ad.sex.value eq 0}">
                                            <option value="0" selected>${sexMan}</option>
                                            <option value="1">${sexWoman}</option>
                                            <option value="2">${sexUnisex}</option>
                                            <option value="3">${sexChild}</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.sex.value eq 1}">
                                            <option value="0">${sexMan}</option>
                                            <option value="1" selected>${sexWoman}</option>
                                            <option value="2">${sexUnisex}</option>
                                            <option value="3">${sexChild}</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.sex.value eq 2}">
                                            <option value="0">${sexMan}</option>
                                            <option value="1">${sexWoman}</option>
                                            <option value="2" selected>${sexUnisex}</option>
                                            <option value="3">${sexChild}</option>
                                        </c:when>
                                        <c:when test="${sessionScope.editAd.ad.sex.value eq 3}">
                                            <option value="0">${sexMan}</option>
                                            <option value="1">${sexWoman}</option>
                                            <option value="2">${sexUnisex}</option>
                                            <option value="3" selected>${sexChild}</option>
                                        </c:when>
                                    </c:choose>
                                </select>
                            </div>
                        </div>
                        <div class="mt-10">
                            <textarea name="editDescription" class="single-textarea" maxlength="500"
                                      placeholder="${sessionScope.editAd.ad.description}"
                                      onfocus="this.placeholder = '${sessionScope.editAd.ad.description}'"
                                      onblur="this.placeholder = '${sessionScope.editAd.ad.description}'"><c:out
                                    value="${sessionScope.editAd.ad.description}"/></textarea>
                        </div>
                        <input class="mt-10" type="file" name="editPicture" accept="image/jpeg, image/png">
                        <br>
                        <br>
                        <button type="submit" class="template-btn">${editAdButton}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Align Area -->

<div id="wrapper"></div>

<!-- Footer Area -->
<c:import url="parts/footer.jsp"/>

<script src="<c:url value="/resources/js/vendor/jquery.nice-select.min.js"/>"></script>

</body>
</html>

