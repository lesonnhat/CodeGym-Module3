package com.codegym.c1224g1demoservlet.controller;

import com.codegym.c1224g1demoservlet.model.Item;
import com.codegym.c1224g1demoservlet.model.Order;
import com.codegym.c1224g1demoservlet.model.OrderDetail;
import com.codegym.c1224g1demoservlet.model.Product;
import com.codegym.c1224g1demoservlet.service.OrderDAO;
import com.codegym.c1224g1demoservlet.service.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ShoppingCartServlet", urlPatterns = "/carts")
public class ShoppingCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
        System.out.println("Initializing Cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    //insertProduct(request, response);
                    break;
                case "edit":
                    //updateProduct(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    showCart(request, response);
                    break;
                case "checkout":
                    checkout(request, response);
                    break;
                case "delete":
                    //deleteUser(request, response);
                    break;
                default:
                    //listProducts(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println("Destroy Cart");
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<Item>();
            Product product = productDAO.findById(id);
            cart.add(new Item(product, 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = getIndex(id, session);
            if (index == -1)
                cart.add(new Item(productDAO.findById(id), 1));
            else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart/list.jsp");
        dispatcher.forward(request, response);
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");

        //Add new Order
        Order order = new Order();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String orderDate = format.format(new Date());
        order.setOrderDate(orderDate);
        double totalPrice = 0;
        for (Item item : cart) {
            Product product = item.getProduct();
            totalPrice += product.getPrice() * item.getQuantity();
        }
        order.setTotalPrice(totalPrice);
        int orderId = orderDAO.saveOrder(order);

        //Add new OrderDetail
        for (Item item : cart) {
            Product product = item.getProduct();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(product.getId());
            orderDetail.setQuantity(item.getQuantity());
            orderDAO.saveOrderDetail(orderDetail);
        }

        session.removeAttribute("cart");
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart/list.jsp");
        dispatcher.forward(request, response);
    }

    private int getIndex(int id, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i).getProduct();
            if (product.getId() == id) {
                return i;
            }
        }
        return -1;
    }
}

