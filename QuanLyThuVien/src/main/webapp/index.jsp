<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.quanlythuvien.model.Sach" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Danh Sách Sách</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2>Danh Sách Sách</h2>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
  %>
  <div class="alert alert-danger" role="alert">
    <%= errorMessage %>
  </div>
  <%
    }
  %>
  <table class="table">
    <thead>
    <tr>
      <th>Mã Sách</th>
      <th>Tên Sách</th>
      <th>Tác Giả</th>
      <th>Mô Tả</th>
      <th>Số Lượng</th>
      <th>Hành Động</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Sach> danhSachSach = (List<Sach>) request.getAttribute("danhSachSach");
      if (danhSachSach != null && !danhSachSach.isEmpty()) {
        for (Sach sach : danhSachSach) {
    %>
    <tr>
      <td><%= sach.getMaSach() %></td>
      <td><%= sach.getTenSach() %></td>
      <td><%= sach.getTacGia() %></td>
      <td><%= sach.getMoTa() %></td>
      <td><%= sach.getSoLuong() %></td>
      <td>
        <%
          if (sach.getSoLuong() > 0) {
        %>
        <a class="btn btn-primary" href="MuonSachPrepareServlet?maSach=<%= sach.getMaSach() %>&tenSach=<%= java.net.URLEncoder.encode(sach.getTenSach(), "UTF-8") %>">Mượn</a>
        <%
        } else {
        %>
        <button class="btn btn-secondary" disabled>Hết Sách</button>
        <%
          }
        %>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr><td colspan="6">Không có sách nào để hiển thị.</td></tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>