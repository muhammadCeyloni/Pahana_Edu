package com.pahanaedu.dao;

import com.pahanaedu.model.Item;
import com.pahanaedu.util.DatabaseUtil;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items ORDER BY title";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                items.add(mapRowToItem(rs));
            }
        }
        return items;
    }

    public void addItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (title, author, isbn, quantity, price, category) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getTitle());
            pstmt.setString(2, item.getAuthor());
            pstmt.setString(3, item.getIsbn());
            pstmt.setInt(4, item.getQuantity());
            pstmt.setBigDecimal(5, item.getPrice());
            pstmt.setString(6, item.getCategory());
            pstmt.executeUpdate();
        }
    }

    public void deleteItem(int itemId) throws SQLException {
        String sql = "DELETE FROM items WHERE item_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            pstmt.executeUpdate();
        }
    }
    
    // You can add an updateItem() method here later for the edit functionality

    private Item mapRowToItem(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setTitle(rs.getString("title"));
        item.setAuthor(rs.getString("author"));
        item.setIsbn(rs.getString("isbn"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setCategory(rs.getString("category"));
        return item;
    }
    public Item getItemById(int itemId) throws SQLException {
    String sql = "SELECT * FROM items WHERE item_id = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, itemId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return mapRowToItem(rs);
            }
        }
    }
    return null;
}

public void updateItem(Item item) throws SQLException {
    String sql = "UPDATE items SET title = ?, author = ?, isbn = ?, quantity = ?, price = ?, category = ? WHERE item_id = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, item.getTitle());
        pstmt.setString(2, item.getAuthor());
        pstmt.setString(3, item.getIsbn());
        pstmt.setInt(4, item.getQuantity());
        pstmt.setBigDecimal(5, item.getPrice());
        pstmt.setString(6, item.getCategory());
        pstmt.setInt(7, item.getItemId());
        pstmt.executeUpdate();
    }
}
}