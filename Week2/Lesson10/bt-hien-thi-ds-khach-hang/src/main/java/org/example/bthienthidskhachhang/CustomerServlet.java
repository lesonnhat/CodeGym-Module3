package org.example.bthienthidskhachhang;

import org.example.bthienthidskhachhang.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Trịnh Thu Liên", "1999-11-27", "Hải Dương", "images\\sv-01.jpg"));
        customers.add(new Customer("Lê Sơn Nhất", "1997-09-25", "Hà Nội", "images\\sv-02.jpg"));
        customers.add(new Customer("Hoàng Thu Trang", "2000-10-15", "Hà Nội", "images\\sv-03.jpg"));
        customers.add(new Customer("Phạm Mai Hương", "1996-03-18", "Hải Phòng", "images\\sv-04.jpg"));

        // Thêm vào request
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}