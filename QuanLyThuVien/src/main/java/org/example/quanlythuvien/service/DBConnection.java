package org.example.quanlythuvien.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quanlythuvien?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "lenhat123";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Không tìm thấy MySQL JDBC Driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // Kiểm tra kết nối
            if (conn != null && !conn.isClosed()) {
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
                return conn;
            } else {
                throw new SQLException("Không thể thiết lập kết nối đến cơ sở dữ liệu.");
            }
        } catch (SQLException e) {
            throw new SQLException("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), e);
        }
    }
}