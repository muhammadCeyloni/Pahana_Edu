package com.pahanaedu.controller;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    try {
        if (action != null && action.equals("delete")) {
            // Get the ID from the URL parameter
            int customerId = Integer.parseInt(request.getParameter("id"));
            // Call the DAO to delete the customer
            customerDAO.deleteCustomer(customerId);
            // Redirect back to the customer list to show the change
            response.sendRedirect("customers");
        } else {
            // Default action: display the list of customers
            List<Customer> customerList = customerDAO.getAllCustomers();
            request.setAttribute("customers", customerList);
            request.getRequestDispatcher("CustomerManagement.jsp").forward(request, response);
        }
    } catch (SQLException e) {
        throw new ServletException("Database error in Customer Servlet", e);
    }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String accountNumber = "ACC" + System.currentTimeMillis();

        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setTelephone(contact);
        newCustomer.setAddress(address);
        newCustomer.setAccountNumber(accountNumber);

        try {
            customerDAO.addCustomer(newCustomer);
            response.sendRedirect("customers");
        } catch (SQLException e) {
            throw new ServletException("Database error adding customer", e);
        }
    }
}