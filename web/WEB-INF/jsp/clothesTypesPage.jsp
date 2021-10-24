<%--
  Created by IntelliJ IDEA.
  User: Vadim
  Date: 20.10.2021
  Time: 16:16
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

    <fmt:message bundle="${loc}" key="label.clothesTypes" var="typesLabel"/>
    <fmt:message bundle="${loc}" key="label.clothesType" var="typeLabel"/>
    <fmt:message bundle="${loc}" key="label.adCount" var="countLabel"/>

    <fmt:message bundle="${loc}" key="category.input.placeholder" var="categoryPlaceholder"/>

    <fmt:message bundle="${loc}" key="message.emptyTypes" var="emptyTypes"/>
    <c:if test="${requestScope.message ne null}">
        <fmt:message bundle="${loc}" key="${requestScope.message}" var="messageText"/>
    </c:if>


    <title>${assortment}</title>
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
                            <img src="<c:url value="/resources/images/logo-light.png"/>" alt="logo"></a>
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
        <div class="page-title text-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <c:if test="${not empty sessionScope.clothesTypeList}">
                            <h2>${typesLabel}</h2>
                        </c:if>
                        <c:if test="${empty sessionScope.clothesTypeList}">
                            <p>${emptyTypes}</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header Area End -->


<!-- Start Align Area -->
<c:choose>
    <c:when test="${not empty sessionScope.clothesTypeList}">
        <div class="whole-wrap">
            <div class="container">
                <div class="section-top-border">
                    <div class="progress-table-wrap">
                        <div class="progress-table">
                            <div class="table-head">
                                <div class="serial">ID</div>
                                <div class="country">${typeLabel}</div>
                                <div class="country">${countLabel}</div>
                                <div class="percentage"></div>
                            </div>
                            <c:forEach var="type" items="${sessionScope.clothesTypeList}">
                                <div class="table-row">
                                    <form action="Controller" method="post"
                                          style="width: 100%; display: inherit">
                                        <input type="hidden" name="command" value="edit_category">
                                        <input type="hidden" name="typeId" value="${type.id}">
                                        <div class="serial">${type.id}</div>
                                        <div class="country">
                                            <input type="text" value="${type.category}"
                                                   placeholder="${type.category}"
                                                   onfocus="this.placeholder = '${type.category}'"
                                                   name="editCategory"
                                                   onblur="this.placeholder = '${type.category}'"
                                                   class="single-input"
                                                   style="margin-right: 15px" required>
                                        </div>
                                        <div class="country">${type.count}</div>
                                        <div class="serial">
                                            <button type="submit"><em class="fa fa-save fa-2x"></em></button>
                                        </div>
                                    </form>
                                    <form action="Controller" method="post" style="display: inherit">
                                        <input type="hidden" name="command" value="delete_category">
                                        <input type="hidden" name="deleteTypeId" value="${type.id}">
                                        <div class="serial">
                                            <button type="submit">
                                                <em class="fa fa-close fa-2x"></em>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </c:forEach>
                            <div class="table-row">
                                <form action="Controller" method="post" style="display: inherit; width: 100%">
                                    <input type="hidden" name="command" value="add_category">
                                    <div class="serial"></div>
                                    <div class="country">
                                        <input type="text" placeholder="${categoryPlaceholder}"
                                               onfocus="this.placeholder = '${categoryPlaceholder}'"
                                               onblur="this.placeholder = '${categoryPlaceholder}'" class="single-input"
                                               name="addCategory" style="margin-right: 15px" required>
                                    </div>
                                    <div class="country"></div>
                                    <div class="percentage">
                                        <button type="submit"><em class="fa fa-plus fa-2x"></em></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:when test="${empty sessionScope.clothesTypeList}">
        <div class="whole-wrap">
            <div class="container">
                <div class="section-top-border">
                    <div class="progress-table-wrap">
                        <div class="progress-table">
                            <div class="table-head">
                                <div class="serial">ID</div>
                                <div class="country">${typeLabel}</div>
                                <div class="country">${countLabel}</div>
                                <div class="country"></div>
                            </div>
                            <div class="table-row">
                                <form action="Controller" method="post" style="display: inherit; width: 100%">
                                    <input type="hidden" name="command" value="add_category">
                                    <div class="serial"></div>
                                    <div class="country">
                                        <input type="text" placeholder="${categoryPlaceholder}"
                                               onfocus="this.placeholder = '${categoryPlaceholder}'"
                                               onblur="this.placeholder = '${categoryPlaceholder}'" class="single-input"
                                               name="addCategory" style="margin-right: 15px" required>
                                    </div>
                                    <div class="country"></div>
                                    <div class="country">
                                        <button type="submit"><em class="fa fa-plus fa-2x"></em></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
</c:choose>
<!-- End Align Area -->

<div id="wrapper"></div>

<!-- Footer Area -->
<c:import url="parts/footer.jsp"/>

</body>
</html>

