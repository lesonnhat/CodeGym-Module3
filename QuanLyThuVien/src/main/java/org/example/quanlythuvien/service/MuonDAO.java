package org.example.quanlythuvien.service;

import org.example.quanlythuvien.model.TheMuon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MuonDAO implements IDAO<TheMuon> {
    @Override
    public List<TheMuon> getAll() {
        List<TheMuon> danhSachMuon = new ArrayList<>();
        String sql = "SELECT * FROM TheMuon";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TheMuon theMuon = new TheMuon(
                        rs.getString("maMuonSach"),
                        rs.getString("maSach"),
                        rs.getString("maHocSinh"),
                        rs.getBoolean("trangThai"),
                        rs.getDate("ngayMuon"),
                        rs.getDate("ngayTra")
                );
                danhSachMuon.add(theMuon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachMuon;
    }

    @Override
    public TheMuon getById(String id) {
        String sql = "SELECT * FROM TheMuon WHERE maMuonSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TheMuon(
                            rs.getString("maMuonSach"),
                            rs.getString("maSach"),
                            rs.getString("maHocSinh"),
                            rs.getBoolean("trangThai"),
                            rs.getDate("ngayMuon"),
                            rs.getDate("ngayTra")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(TheMuon theMuon) {
        String sql = "INSERT INTO TheMuon (maMuonSach, maSach, maHocSinh, trangThai, ngayMuon, ngayTra) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, theMuon.getMaMuonSach());
            stmt.setString(2, theMuon.getMaSach());
            stmt.setString(3, theMuon.getMaHocSinh());
            stmt.setBoolean(4, theMuon.isTrangThai());
            stmt.setDate(5, new java.sql.Date(theMuon.getNgayMuon().getTime()));
            stmt.setDate(6, new java.sql.Date(theMuon.getNgayTra().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TheMuon theMuon) {
        String sql = "UPDATE TheMuon SET maSach = ?, maHocSinh = ?, trangThai = ?, ngayMuon = ?, ngayTra = ? WHERE maMuonSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, theMuon.getMaSach());
            stmt.setString(2, theMuon.getMaHocSinh());
            stmt.setBoolean(3, theMuon.isTrangThai());
            stmt.setDate(4, new java.sql.Date(theMuon.getNgayMuon().getTime()));
            stmt.setDate(5, new java.sql.Date(theMuon.getNgayTra().getTime()));
            stmt.setString(6, theMuon.getMaMuonSach());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM TheMuon WHERE maMuonSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}