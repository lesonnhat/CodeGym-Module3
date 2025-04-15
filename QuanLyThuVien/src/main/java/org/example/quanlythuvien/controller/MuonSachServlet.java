package org.example.quanlythuvien.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import org.example.quanlythuvien.model.Sach;
import org.example.quanlythuvien.model.TheMuon;
import org.example.quanlythuvien.service.MuonDAO;
import org.example.quanlythuvien.service.SachDAO;

@WebServlet("/MuonSachServlet")
public class MuonSachServlet extends HttpServlet {
    private SachDAO sachDAO;
    private MuonDAO muonDAO;

    @Override
    public void init() throws ServletException {
        sachDAO = new SachDAO();
        muonDAO = new MuonDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String maSach = request.getParameter("maSach");
            String maHocSinh = request.getParameter("maHocSinh");
            String ngayTraStr = request.getParameter("ngayTra");

            // Kiểm tra dữ liệu đầu vào
            if (maSach == null || maHocSinh == null || ngayTraStr == null || maSach.isEmpty() || maHocSinh.isEmpty() || ngayTraStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thông tin mượn sách không đầy đủ.");
                return;
            }

            // Chuyển đổi ngày trả
            java.sql.Date ngayTra;
            try {
                ngayTra = java.sql.Date.valueOf(ngayTraStr);
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Định dạng ngày trả không hợp lệ. Vui lòng sử dụng yyyy-MM-dd.");
                return;
            }

            // Kiểm tra sách và số lượng
            Sach sach = sachDAO.getById(maSach);
            if (sach == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã sách không tồn tại.");
                return;
            }
            if (sach.getSoLuong() <= 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sách đã hết.");
                return;
            }

            // Giảm số lượng sách
            sach.setSoLuong(sach.getSoLuong() - 1);
            sachDAO.update(sach);

            // Tạo bản ghi mượn sách
            TheMuon theMuon = new TheMuon(
                    "M" + System.currentTimeMillis() % 1000000, // Đảm bảo độ dài không vượt quá 10 ký tự
                    maSach,
                    maHocSinh,
                    true,
                    new Date(), // Ngày mượn là ngày hiện tại
                    ngayTra
            );
            muonDAO.add(theMuon);

            // Chuyển hướng về trang hiển thị sách
            response.sendRedirect("SachServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi mượn sách: " + e.getMessage());
        }
    }
}