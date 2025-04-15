package org.example.quanlythuvien.service;

import org.example.quanlythuvien.model.HocSinh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HocSinhDAO implements IDAO<HocSinh> {
    @Override
    public List<HocSinh> getAll() {
        List<HocSinh> danhSachHocSinh = new ArrayList<>();
        String sql = "SELECT * FROM HocSinh";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                HocSinh hocSinh = new HocSinh(
                        rs.getString("maHocSinh"),
                        rs.getString("hoTen"),
                        rs.getString("lop")
                );
                danhSachHocSinh.add(hocSinh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHocSinh;
    }

    @Override
    public HocSinh getById(String id) {
        String sql = "SELECT * FROM HocSinh WHERE maHocSinh = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new HocSinh(
                            rs.getString("maHocSinh"),
                            rs.getString("hoTen"),
                            rs.getString("lop")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(HocSinh hocSinh) {
        String sql = "INSERT INTO HocSinh (maHocSinh, hoTen, lop) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hocSinh.getMaHocSinh());
            stmt.setString(2, hocSinh.getHoTen());
            stmt.setString(3, hocSinh.getLop());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(HocSinh hocSinh) {
        String sql = "UPDATE HocSinh SET hoTen = ?, lop = ? WHERE maHocSinh = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hocSinh.getHoTen());
            stmt.setString(2, hocSinh.getLop());
            stmt.setString(3, hocSinh.getMaHocSinh());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM HocSinh WHERE maHocSinh = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}