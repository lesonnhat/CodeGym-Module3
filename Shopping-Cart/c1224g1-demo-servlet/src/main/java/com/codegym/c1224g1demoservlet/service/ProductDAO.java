package com.codegym.c1224g1demoservlet.service;

import com.codegym.c1224g1demoservlet.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IDAO<Product> {

    private static final String SELECT_ALL_PRODUCTS = "select * from product";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (Name, Price ) VALUES (?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from product where id = ?";
    private static final String UPDATE_PRODUCT_SQL = "update product set name = ?,price= ? where id = ?;";

    public ProductDAO() {
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<Product>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    @Override
    public List<Product> findAllWithStoreProcedure() {
        List<Product> products = new ArrayList<Product>();
        String query = "{CALL sp_get_all_products()}";

        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    @Override
    public void save(Product product) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void saveWithStoreProcedure(Product product) throws SQLException {
        String query = "{CALL sp_insert_product(?,?)}";
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, product.getName());
            callableStatement.setDouble(2, product.getPrice());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = Double.parseDouble(rs.getString("price"));
                product = new Product(id, name, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public Product findByIdWithStoreProcedure(int id) {
        String query = "{CALL sp_find_product_by_id(?)}";
        Product product = null;
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = Double.parseDouble(rs.getString("price"));
                product = new Product(id, name, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean updateWithStoreProcedure(Product product) throws SQLException {
        String query = "{CALL sp_update_product(?,?,?)}";
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection();
             CallableStatement statement = connection.prepareCall(query);) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

