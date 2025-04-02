<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách khách hàng</title>
    <style>
        img {
            background-color: aquamarine;
        }
    </style>
</head>
<body>
<h1>Danh sách khách hàng</h1>
<table border="1">
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.birthdate}</td>
            <td>${customer.address}</td>
            <td><img src="${customer.imageUrl}" alt="Ảnh" width="100"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>