package com.pahanaedu.controller;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

// CORRECTED: URL is now all lowercase to match the form action
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This forwards the request to the JSP page so it can be displayed
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Check if the username already exists in the database
            if (userDAO.getUserByUsername(username) != null) {
                request.setAttribute("errorMessage", "Username already exists. Please choose another.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                return;
            }

            // If username is available, create the new user
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userDAO.addUser(newUser);

            // Redirect to the login page with a success message
            response.sendRedirect("login.jsp?success=true");

        } catch (SQLException e) {
            throw new ServletException("Database error during signup", e);
        }
    }
}