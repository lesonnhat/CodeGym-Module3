package org.example.quanlythuvien.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.example.quanlythuvien.model.Sach;
import org.example.quanlythuvien.service.SachDAO;

@WebServlet("/SachServlet")
public class SachServlet extends HttpServlet {
    private SachDAO sachDAO;

    @Override
    public void init() throws ServletException {
        sachDAO = new SachDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Sach> danhSachSach = sachDAO.getAll();
            if (danhSachSach.isEmpty()) {
                System.out.println("Không có sách nào được lấy từ cơ sở dữ liệu.");
            } else {
                System.out.println("Đã lấy được " + danhSachSach.size() + " sách từ cơ sở dữ liệu.");
            }
            request.setAttribute("danhSachSach", danhSachSach);

            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lấy danh sách sách: " + e.getMessage());
        }
    }
}