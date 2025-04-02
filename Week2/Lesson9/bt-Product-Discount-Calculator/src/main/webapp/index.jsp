<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<body>
<h1>Product Discount Calculator</h1>
<form action="/calculator" method="post">
    <label>Product Description: </label>
    <input type="text" name="description" placeholder=". . ."><br/>
    <label>List Price: </label>
    <input type="number" name="price" placeholder=". . ."><br/>
    <label>Discount Percent: </label>
    <input type="number" name="percent" placeholder=". . .">
    <input type="submit" id="submit" value="Calculate">
</form>
</body>
</html>