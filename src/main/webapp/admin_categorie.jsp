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
<title><fmt:message key="categories.titre.page"/></title>
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
<h1><fmt:message key="categories.titre.liste"/></h1>
<form method='post' action='adminCategory'>
 <table border='1' align='center'>
   <tr>
    <td>Chercher une catégorie:<input type='text' name='keyWord'
	value='${(not empty categoryForm)?categoryForm.keyWord:""}'></td>
    <td><input type='submit' name='chercheCat' value='Chercher'></td>
   </tr>
 </table>
</form>

<form method='post' action='adminCategory'>
  <table border='1' align='center'>
    <tr>
      <td>Id Catégorie<td>Nom Catégorie</td><td>Description</td>
    </tr>
    <tr>
     <td></td>
     <td><input type='text' name='nameCat'></td>
     <td><input type='text' name='description' size='40'></td>
     <td><input type='submit' name='addCat' value='Ajouter'></td>
    </tr>
     <c:if test="${not empty categoryForm}">
      
     <c:forEach items="${categoryForm.categories}" var="cat">
 		<tr>
		      <td>${cat.idCat}</td>
		      <td>${cat.nameCat}</td>
		      <td>${cat.description}</td>
		      <c:url value="adminCategory" var="myURL">
				  <c:param name="idCat" value="${cat.idCat}"/>
				</c:url>
 

		      <td><a href='<c:out value="${myURL}"/>'>Supprimer</a></td>
		 </tr>
	</c:forEach>
	</c:if>
  </table>
</form>

</body>
</html>
