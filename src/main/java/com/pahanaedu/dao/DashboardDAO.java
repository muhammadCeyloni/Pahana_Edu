package com.pahanaedu.dao;

import com.pahanaedu.model.DashboardStats;
import com.pahanaedu.util.DatabaseUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO {
    public DashboardStats getDashboardStats() throws SQLException {
        DashboardStats stats = new DashboardStats();

        // Using try-with-resources for each query
        try (Connection conn = DatabaseUtil.getConnection()) {
            // Get Total Sales
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(total_amount) FROM invoices WHERE status = 'Paid'");
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    stats.setTotalSales(rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO);
                }
            }

            // Get Inventory Value
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(price * quantity) FROM items");
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    stats.setInventoryValue(rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO);
                }
            }

            // Get Active Customer Count
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM customers");
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    stats.setActiveCustomers(rs.getLong(1));
                }
            }

            // Get Pending Orders Count (assuming 'Pending' is a status)
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM invoices WHERE status = 'Pending'");
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    stats.setPendingOrders(rs.getLong(1));
                }
            }
        }
        return stats;
    }
}