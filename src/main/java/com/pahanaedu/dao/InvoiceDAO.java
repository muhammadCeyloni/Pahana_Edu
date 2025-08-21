package com.pahanaedu.dao;

import com.pahanaedu.model.Invoice;
import com.pahanaedu.model.InvoiceItem;
import com.pahanaedu.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    
    public void createInvoice(Invoice invoice) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();
            // START TRANSACTION: Disable auto-commit
            conn.setAutoCommit(false);

            // 1. Insert into the main 'invoices' table
            String invoiceSql = "INSERT INTO invoices (customer_id, invoice_date, total_amount, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmtInvoice = conn.prepareStatement(invoiceSql, Statement.RETURN_GENERATED_KEYS)) {
                pstmtInvoice.setInt(1, invoice.getCustomerId());
                pstmtInvoice.setDate(2, new java.sql.Date(invoice.getInvoiceDate().getTime()));
                pstmtInvoice.setBigDecimal(3, invoice.getTotalAmount());
                pstmtInvoice.setString(4, invoice.getStatus());
                pstmtInvoice.executeUpdate();

                // Get the newly generated invoice_id from the database
                try (ResultSet generatedKeys = pstmtInvoice.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int invoiceId = generatedKeys.getInt(1);

                        // 2. Insert each item into the 'invoice_items' junction table
                        String itemsSql = "INSERT INTO invoice_items (invoice_id, item_id, quantity, price_at_purchase) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement pstmtItems = conn.prepareStatement(itemsSql)) {
                            for (InvoiceItem item : invoice.getInvoiceItems()) {
                                pstmtItems.setInt(1, invoiceId);
                                pstmtItems.setInt(2, item.getItemId());
                                pstmtItems.setInt(3, item.getQuantity());
                                pstmtItems.setBigDecimal(4, item.getPriceAtPurchase());
                                pstmtItems.addBatch();
                            }
                            pstmtItems.executeBatch();
                        }

                        // 3. Update the stock quantity in the main 'items' table
                        String updateStockSql = "UPDATE items SET quantity = quantity - ? WHERE item_id = ?";
                        try (PreparedStatement pstmtUpdateStock = conn.prepareStatement(updateStockSql)) {
                            for (InvoiceItem item : invoice.getInvoiceItems()) {
                                pstmtUpdateStock.setInt(1, item.getQuantity());
                                pstmtUpdateStock.setInt(2, item.getItemId());
                                pstmtUpdateStock.addBatch();
                            }
                            pstmtUpdateStock.executeBatch();
                        }
                    } else {
                        throw new SQLException("Creating invoice failed, no ID obtained.");
                    }
                }
            }
            // If all operations were successful, commit the transaction
            conn.commit();
        } catch (SQLException e) {
            // If any error occurs, roll back the entire transaction
            if (conn != null) {
                conn.rollback();
            }
            throw e; // Re-throw the exception to be handled by the servlet
        } finally {
            // Restore default behavior and close connection
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    
    // Method to get all invoices to display on the page
    public List<Invoice> getAllInvoices() throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        // This query joins tables to get customer name along with invoice details
        String sql = "SELECT i.*, c.name as customer_name FROM invoices i JOIN customers c ON i.customer_id = c.customer_id ORDER BY i.invoice_date DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setInvoiceDate(rs.getDate("invoice_date"));
                invoice.setTotalAmount(rs.getBigDecimal("total_amount"));
                invoice.setStatus(rs.getString("status"));
                // We are not setting items here, but you can retrieve them with another query if needed
                invoices.add(invoice);
            }
        }
        return invoices;
    }
    // Add this new method to your InvoiceDAO.java file
public List<Invoice> getInvoicesByDateRange(java.util.Date startDate, java.util.Date endDate) throws SQLException {
    List<Invoice> invoices = new ArrayList<>();
    String sql = "SELECT i.*, c.name as customer_name FROM invoices i " +
                 "JOIN customers c ON i.customer_id = c.customer_id " +
                 "WHERE i.invoice_date BETWEEN ? AND ? " +
                 "ORDER BY i.invoice_date DESC";

    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
        pstmt.setDate(2, new java.sql.Date(endDate.getTime()));

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setInvoiceDate(rs.getDate("invoice_date"));
                invoice.setTotalAmount(rs.getBigDecimal("total_amount"));
                invoice.setStatus(rs.getString("status"));
                // The customer's name is retrieved via the JOIN for display purposes
                // You can add a transient field in your Invoice model if you wish
                invoices.add(invoice);
            }
        }
    }
    return invoices;
}
}