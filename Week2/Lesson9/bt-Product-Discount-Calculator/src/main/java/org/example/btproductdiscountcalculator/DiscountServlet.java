package org.example.btproductdiscountcalculator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "DiscountServlet", value = "/calculator")
public class DiscountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        float percent = Float.parseFloat(request.getParameter("percent"));

        float discount = (price * percent) / 100;

        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<h2>Product Description: " + description + "</h2>");
        writer.println("<h2>List Price: " + price + "</h2>");
        writer.println("<h2>Discount Percent: " + percent + "</h2>");
        writer.println("<h2>Discount Amount: " + discount + "</h2>");
        writer.println("</html>");
    }
}
