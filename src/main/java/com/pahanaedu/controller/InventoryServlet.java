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
    private final ItemDAO itemDAO = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("delete".equals(action)) {
                deleteItem(request, response);
            } else if ("edit".equals(action)) {
                showEditForm(request, response);
            } else {
                showItemList(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error in Inventory GET", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("update".equals(action)) {
                updateItem(request, response);
            } else {
                addItem(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error in Inventory POST", e);
        }
    }

    private void showItemList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Item> itemList = itemDAO.getAllItems();
        request.setAttribute("items", itemList);
        request.getRequestDispatcher("InventoryManagement.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Item existingItem = itemDAO.getItemById(id);
        request.setAttribute("item", existingItem);
        request.getRequestDispatcher("edit-item.jsp").forward(request, response);
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        itemDAO.deleteItem(id);
        response.sendRedirect("inventory");
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Item newItem = new Item();
        newItem.setTitle(request.getParameter("title"));
        newItem.setAuthor(request.getParameter("author"));
        newItem.setIsbn(request.getParameter("isbn"));
        newItem.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        newItem.setPrice(new BigDecimal(request.getParameter("price")));
        newItem.setCategory(request.getParameter("category"));
        itemDAO.addItem(newItem);
        response.sendRedirect("inventory");
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Item item = new Item();
        item.setItemId(Integer.parseInt(request.getParameter("id")));
        item.setTitle(request.getParameter("title"));
        item.setAuthor(request.getParameter("author"));
        item.setIsbn(request.getParameter("isbn"));
        item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        item.setPrice(new BigDecimal(request.getParameter("price")));
        item.setCategory(request.getParameter("category"));
        itemDAO.updateItem(item);
        response.sendRedirect("inventory");
    }
}