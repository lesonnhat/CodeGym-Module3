<%@ page import="org.example.quanlythuvien.model.TheMuon" %>
<%@ page import="org.example.quanlythuvien.service.MuonDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trả Sách</title>
</head>
<body>
<h1>Trả Sách</h1>

<%
    String id = request.getParameter("id");
    MuonDAO muonDao = new MuonDAO(); // Khởi tạo đối tượng MuonDAO
    TheMuon sachMuon = muonDao.getById(id);  // Sử dụng phương thức getById
%>

<table>
    <tr>
        <th>Mã Sách</th>
        <th>Tên Sách</th>
        <th>Tác Giả</th>
    </tr>
    <tr>
        <td><%= sachMuon.getMaSach() %></td>
        <td><%= sachMuon.getTenSach() %></td>
        <td><%= sachMuon.getTacGia() %></td>
    </tr>
</table>

<form action="TraSach" method="get">
    <input type="hidden" name="id" value="<%= sachMuon.getMaMuonSach() %>"/> <!-- Sử dụng maMuonSach -->
    <button type="submit">Xác Nhận Trả Sách</button>
</form>

<a href="SachDangMuon">Quay lại</a>
</body>
</html>