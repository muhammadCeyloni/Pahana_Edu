package com.pahanaedu.model;

import java.math.BigDecimal;

public class DashboardStats {
    private BigDecimal totalSales;
    private BigDecimal inventoryValue;
    private long activeCustomers;
    private long pendingOrders;

    // Getters and Setters
    public BigDecimal getTotalSales() { return totalSales; }
    public void setTotalSales(BigDecimal totalSales) { this.totalSales = totalSales; }
    public BigDecimal getInventoryValue() { return inventoryValue; }
    public void setInventoryValue(BigDecimal inventoryValue) { this.inventoryValue = inventoryValue; }
    public long getActiveCustomers() { return activeCustomers; }
    public void setActiveCustomers(long activeCustomers) { this.activeCustomers = activeCustomers; }
    public long getPendingOrders() { return pendingOrders; }
    public void setPendingOrders(long pendingOrders) { this.pendingOrders = pendingOrders; }
}