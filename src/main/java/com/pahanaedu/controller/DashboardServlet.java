package com.pahanaedu.controller;

import com.pahanaedu.dao.DashboardDAO;
import com.pahanaedu.model.DashboardStats;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private final DashboardDAO dashboardDAO = new DashboardDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DashboardStats stats = dashboardDAO.getDashboardStats();
            request.setAttribute("dashboardStats", stats);
            request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error fetching dashboard stats", e);
        }
    }
}