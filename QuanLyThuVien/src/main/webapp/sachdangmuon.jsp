<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sách Đang Mượn</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Sách Đang Mượn</h2>
    <a href="SachServlet" class="btn btn-secondary mb-3">Quay lại danh sách sách</a>
    <table class="table">
        <thead>
        <tr>
            <th>Mã Mượn</th>
            <th>Mã Sách</th>
            <th>Tên Sách</th>
            <th>Mã Học Sinh</th>
            <th>Ngày Mượn</th>
            <th>Ngày Trả</th>
            <th>Trạng Thái</th>
            <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty danhSachMuon}">
                <c:forEach var="theMuon" items="${danhSachMuon}">
                    <c:if test="${theMuon.trangThai}">
                        <tr>
                            <td>${theMuon.maMuonSach}</td>
                            <td>${theMuon.maSach}</td>
                            <td>${sachMap[theMuon.maSach].tenSach}</td>
                            <td>${theMuon.maHocSinh}</td>
                            <td>${theMuon.ngayMuon}</td>
                            <td>${theMuon.ngayTra}</td>
                            <td>Đang mượn</td>
                            <td>
                                <a href="TraSach?id=${theMuon.maMuonSach}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('Bạn có chắc chắn muốn trả sách này không?');">Trả Sách</a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="8">Không có sách nào đang được mượn.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>