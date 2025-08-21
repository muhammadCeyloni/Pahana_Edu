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
    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                showCustomerList(request, response);
            } else if ("delete".equals(action)) {
                deleteCustomer(request, response);
            } else if ("edit".equals(action)) {
                showEditForm(request, response);
            } else {
                showCustomerList(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error in Customer Servlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("update".equals(action)) {
                updateCustomer(request, response);
            } else {
                addCustomer(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error handling customer POST request", e);
        }
    }

    private void showCustomerList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Customer> customerList = customerDAO.getAllCustomers();
        request.setAttribute("customers", customerList);
        request.getRequestDispatcher("CustomerManagement.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.getCustomerById(id);
        request.setAttribute("customer", existingCustomer);
        request.getRequestDispatcher("edit-customer.jsp").forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerDAO.deleteCustomer(id);
        response.sendRedirect("customers");
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Customer newCustomer = new Customer();
        newCustomer.setName(request.getParameter("name"));
        newCustomer.setTelephone(request.getParameter("contact"));
        newCustomer.setAddress(request.getParameter("address"));
        newCustomer.setAccountNumber("ACC" + System.currentTimeMillis());
        customerDAO.addCustomer(newCustomer);
        response.sendRedirect("customers");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Customer customer = new Customer();
        customer.setCustomerId(Integer.parseInt(request.getParameter("id")));
        customer.setName(request.getParameter("name"));
        customer.setTelephone(request.getParameter("contact"));
        customer.setAddress(request.getParameter("address"));
        customerDAO.updateCustomer(customer);
        response.sendRedirect("customers");
    }
}