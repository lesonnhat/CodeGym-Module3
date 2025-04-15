package org.example.quanlythuvien.controller;

import org.example.quanlythuvien.model.Sach;
import org.example.quanlythuvien.model.TheMuon;
import org.example.quanlythuvien.service.MuonDAO;
import org.example.quanlythuvien.service.SachDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TraSach")
public class TraSachServlet extends HttpServlet {
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
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã thẻ mượn không hợp lệ.");
                return;
            }

            // Lấy thông tin thẻ mượn
            TheMuon theMuon = muonDAO.getById(id);
            if (theMuon == null || !theMuon.isTrangThai()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thẻ mượn không tồn tại hoặc sách đã được trả.");
                return;
            }

            // Cập nhật trạng thái thẻ mượn thành đã trả
            muonDAO.updateStatus(id, "daTra");

            // Tăng số lượng sách trong kho
            Sach sach = sachDAO.getById(theMuon.getMaSach());
            if (sach != null) {
                sach.setSoLuong(sach.getSoLuong() + 1);
                sachDAO.update(sach);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không tìm thấy sách để cập nhật số lượng.");
                return;
            }

            response.sendRedirect("SachDangMuon");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi trả sách: " + e.getMessage());
        }
    }
}