package org.example.quanlythuvien.service;

import org.example.quanlythuvien.model.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SachDAO implements IDAO<Sach> {
    @Override
    public List<Sach> getAll() {
        List<Sach> danhSachSach = new ArrayList<>();
        String sql = "SELECT * FROM Sach";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sach sach = new Sach(
                        rs.getString("maSach"),
                        rs.getString("tenSach"),
                        rs.getString("tacGia"),
                        rs.getString("moTa"),
                        rs.getInt("soLuong")
                );
                danhSachSach.add(sach);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Kiểm tra xem có lỗi xảy ra không
        }
        return danhSachSach;
    }

    @Override
    public Sach getById(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Sach(
                            rs.getString("maSach"),
                            rs.getString("tenSach"),
                            rs.getString("tacGia"),
                            rs.getString("moTa"),
                            rs.getInt("soLuong")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Sach sach) {
        String sql = "INSERT INTO Sach (maSach, tenSach, tacGia, moTa, soLuong) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sach.getMaSach());
            stmt.setString(2, sach.getTenSach());
            stmt.setString(3, sach.getTacGia());
            stmt.setString(4, sach.getMoTa());
            stmt.setInt(5, sach.getSoLuong());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Sach sach) {
        String sql = "UPDATE Sach SET tenSach = ?, tacGia = ?, moTa = ?, soLuong = ? WHERE maSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sach.getTenSach());
            stmt.setString(2, sach.getTacGia());
            stmt.setString(3, sach.getMoTa());
            stmt.setInt(4, sach.getSoLuong());
            stmt.setString(5, sach.getMaSach());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Sach WHERE maSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}