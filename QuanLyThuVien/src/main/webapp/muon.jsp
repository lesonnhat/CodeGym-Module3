<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.quanlythuvien.model.HocSinh" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mượn Sách</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Mượn Sách</h2>
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
    <form action="MuonSachServlet" method="post">
        <input type="hidden" name="maSach" value="<%= request.getParameter("maSach") %>">
        <div class="form-group">
            <label for="tenSach">Tên Sách</label>
            <input type="text" class="form-control" id="tenSach" name="tenSach" value="<%= request.getParameter("tenSach") != null ? request.getParameter("tenSach") : "" %>" readonly>
        </div>
        <div class="form-group">
            <label for="maHocSinh">Học Sinh</label>
            <select class="form-control" id="maHocSinh" name="maHocSinh" required>
                <option value="">Chọn Học Sinh</option>
                <%
                    List<HocSinh> danhSachHocSinh = (List<HocSinh>) request.getAttribute("danhSachHocSinh");
                    if (danhSachHocSinh != null && !danhSachHocSinh.isEmpty()) {
                        for (HocSinh hocSinh : danhSachHocSinh) {
                %>
                <option value="<%= hocSinh.getMaHocSinh() %>"><%= hocSinh.getHoTen() %></option>
                <%
                    }
                } else {
                %>
                <option value="">Không có học sinh nào.</option>
                <%
                    }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="ngayMuon">Ngày Mượn</label>
            <input type="text" class="form-control" id="ngayMuon" name="ngayMuon" value="<%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()) %>" readonly>
        </div>
        <div class="form-group">
            <label for="ngayTra">Ngày Trả</label>
            <input type="date" class="form-control" id="ngayTra" name="ngayTra" required>
        </div>
        <button type="submit" class="btn btn-primary">Mượn Sách</button>
        <a href="SachServlet" class="btn btn-secondary">Trở Về</a>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>