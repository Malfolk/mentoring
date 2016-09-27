<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Cart</title>
</head>
<body>
Cart
<p/>
<table>
    <c:set var="total" value="0" scope="page" />
    <c:forEach items="${booksInCart}" var="book">
        <c:set var="total" value="${total + book.price}" scope="page"/>
        <tr>
            <td>${book.title}</td>
            <td>${book.price}</td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>TotalAmount</td>
        <td>${total}</td>
    </tr>
</table>
</body>
</html>