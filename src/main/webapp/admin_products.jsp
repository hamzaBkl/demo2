<%--
  Created by IntelliJ IDEA.
  User: helbakka
  Date: 12/1/2021
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.demo2.model.*" %>
<%@ page import="com.example.demo2.view.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.Bundle" />

<html>
<head>
    <title><fmt:message key="products.titre.page"/></title>
</head>
<body bgcolor="#FFFFFF">
<c:url value="" var="lngFr">
    <c:param name="language" value="fr" />
</c:url>

<c:url value="" var="lngEn">
    <c:param name="language" value="en" />
</c:url>

<c:url value="" var="lngEs">
    <c:param name="language" value="es" />
</c:url>

<p align="right">
    <a href="<c:out value="${lngFr}" />">FR</a>
    <a href="<c:out value="${lngEn}" />">EN</a>
    <a href="<c:out value="${lngEs}" />">ES</a>
</p>

<h1><fmt:message key="products.titre.liste"/></h1>
<form method='post' action='adminProduct'>
    <table border='1' align='center'>
        <tr>
            <td><fmt:message key="products.searchProduct"/><input type='text' name='keyWord'
                                              value='${(not empty productForm)?productForm.keyWord:""}'></td>
            <td>
                <select name="idCat">
                    <option value="0"><fmt:message key="products.allCats"/></option>
                    <c:forEach items="${productForm.categories}" var="cat">
                        <option value="${cat.idCat}" ${cat.idCat == productForm.idCat ? 'selected="selected"' : ''}>${cat.nameCat}</option>
                    </c:forEach>
                </select>
            </td>
            <fmt:message var="searchTxt" key="action.search"/>
            <td><input type='submit' name='searchProduct' value="${searchTxt}"></td>
        </tr>


    </table>
</form>

<form method='post' action='adminProduct'>
    <table border='1' align='center'>
        <tr>
            <td><fmt:message key="products.id"/><td><fmt:message key="products.designation"/></td><td><fmt:message key="products.price"/></td><td><fmt:message key="products.quantity"/></td><td><fmt:message key="products.category"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type='text' name='designation'></td>
            <td><input type="text" name='price'></td>
            <td><input type='number' name='quantity'></td>
            <td>
                <select name="idCat">
                    <c:forEach items="${productForm.categories}" var="cat">
                        <option value="${cat.idCat}">${cat.nameCat}</option>
                    </c:forEach>
                </select>
            </td>
            <fmt:message var="addTxt" key="action.add"/>
            <td><input type='submit' name='addProduct' value="${addTxt}" />


        </tr>
        <c:if test="${not empty productForm}">

            <c:forEach items="${productForm.products}" var="product">
                <tr>
                    <td>${product.idProduct}</td>
                    <td>${product.designation}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.category.nameCat}</td>
                    <c:url value="adminProduct" var="myURL">
                        <c:param name="idProduct" value="${product.idProduct}"/>
                    </c:url>
                    <td><a href='<c:out value="${myURL}"/>'><fmt:message key="action.delete"/></a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</form>

</body>
</html>
