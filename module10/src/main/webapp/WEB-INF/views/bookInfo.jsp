<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Book Info</title>
</head>
<body>
Book Info
<p/>
<table>
        <tr><td>Title: ${book.title}</td></tr>
        <tr><td>Price: ${book.price}$</td></tr>
        <tr><td>Description: ${book.description}</td></tr>
</table>
</body>
</html>