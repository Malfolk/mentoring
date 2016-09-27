<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Shop</title>
</head>
<body>
Shop
<p/>
<table>
    <c:forEach items="${books}" var="book">
    <tr>
        <td><a href="/info/${book.id}">${book.title}</a></td>
        <td>${book.price}$</td>
        <td><a href="/cart/add/${book.id}">Add to cart</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>