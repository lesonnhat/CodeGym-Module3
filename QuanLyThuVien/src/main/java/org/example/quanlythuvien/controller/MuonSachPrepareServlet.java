package org.example.quanlythuvien.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.example.quanlythuvien.model.HocSinh;
import org.example.quanlythuvien.service.HocSinhDAO;

@WebServlet("/MuonSachPrepareServlet")
public class MuonSachPrepareServlet extends HttpServlet {
    private HocSinhDAO hocSinhDAO;

    @Override
    public void init() throws ServletException {
        hocSinhDAO = new HocSinhDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<HocSinh> danhSachHocSinh = hocSinhDAO.getAll();
            request.setAttribute("danhSachHocSinh", danhSachHocSinh);
            request.getRequestDispatcher("muon.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi chuẩn bị dữ liệu mượn sách: " + e.getMessage());
        }
    }
}