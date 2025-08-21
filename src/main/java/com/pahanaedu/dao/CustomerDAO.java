package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers ORDER BY name";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(mapRowToCustomer(rs));
            }
        }
        return customers;
    }

    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (account_number, name, address, telephone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getAccountNumber());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getAddress());
            pstmt.setString(4, customer.getTelephone());
            pstmt.executeUpdate();
        }
    }
    
    // Add updateCustomer and deleteCustomer methods here...

    private Customer mapRowToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setAccountNumber(rs.getString("account_number"));
        customer.setName(rs.getString("name"));
        customer.setAddress(rs.getString("address"));
        customer.setTelephone(rs.getString("telephone"));
        return customer;
    }
    public void deleteCustomer(int customerId) throws SQLException {
    String sql = "DELETE FROM customers WHERE customer_id = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, customerId);
        pstmt.executeUpdate();
    }
}
// Add this method to get a single customer
public Customer getCustomerById(int customerId) throws SQLException {
    String sql = "SELECT * FROM customers WHERE customer_id = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, customerId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return mapRowToCustomer(rs); // Using the helper method you already have
            }
        }
    }
    return null;
}

// Add this method to update a customer's details
public void updateCustomer(Customer customer) throws SQLException {
    String sql = "UPDATE customers SET name = ?, address = ?, telephone = ? WHERE customer_id = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, customer.getName());
        pstmt.setString(2, customer.getAddress());
        pstmt.setString(3, customer.getTelephone());
        pstmt.setInt(4, customer.getCustomerId());
        pstmt.executeUpdate();
    }
}
}