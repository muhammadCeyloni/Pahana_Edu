package com.pahanaedu.controller;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
    private ItemDAO itemDAO = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action != null && action.equals("delete")) {
                int itemId = Integer.parseInt(request.getParameter("id"));
                itemDAO.deleteItem(itemId);
                response.sendRedirect("inventory"); // Redirect to refresh the list
            } else {
                // Default action: display the list of items
                List<Item> itemList = itemDAO.getAllItems();
                request.setAttribute("items", itemList);
                request.getRequestDispatcher("InventoryManagement.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error in Inventory Servlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This handles adding a new item from the form
        Item newItem = new Item();
        newItem.setTitle(request.getParameter("title"));
        newItem.setAuthor(request.getParameter("author"));
        newItem.setIsbn(request.getParameter("isbn"));
        newItem.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        newItem.setPrice(new BigDecimal(request.getParameter("price")));
        newItem.setCategory(request.getParameter("category"));
        
        try {
            itemDAO.addItem(newItem);
            response.sendRedirect("inventory"); // Redirect to show the updated list
        } catch (SQLException e) {
             throw new ServletException("Database error adding item", e);
        }
    }
}