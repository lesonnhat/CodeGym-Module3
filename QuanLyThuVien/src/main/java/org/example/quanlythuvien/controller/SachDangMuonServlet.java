package org.example.quanlythuvien.controller;

import org.example.quanlythuvien.model.Sach;
import org.example.quanlythuvien.model.TheMuon;
import org.example.quanlythuvien.service.MuonDAO;
import org.example.quanlythuvien.service.SachDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/SachDangMuon")
public class SachDangMuonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MuonDAO muonDAO;
    private SachDAO sachDAO;

    @Override
    public void init() throws ServletException {
        muonDAO = new MuonDAO();
        sachDAO = new SachDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy danh sách thẻ mượn
            List<TheMuon> danhSachMuon = muonDAO.getAllMuon();
            System.out.println("Số lượng thẻ mượn từ getAllMuon: " + danhSachMuon.size());

            // Tạo map để ánh xạ maSach đến Sach
            Map<String, Sach> sachMap = new HashMap<>();
            for (TheMuon theMuon : danhSachMuon) {
                if (theMuon.isTrangThai()) { // Chỉ lấy sách đang mượn
                    Sach sach = sachDAO.getById(theMuon.getMaSach());
                    if (sach != null) {
                        sachMap.put(theMuon.getMaSach(), sach);
                    } else {
                        System.out.println("Không tìm thấy sách với mã: " + theMuon.getMaSach());
                    }
                }
            }

            request.setAttribute("danhSachMuon", danhSachMuon);
            request.setAttribute("sachMap", sachMap);
            RequestDispatcher dispatcher = request.getRequestDispatcher("sachdangmuon.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lấy danh sách sách đang mượn: " + e.getMessage());
        }
    }
}