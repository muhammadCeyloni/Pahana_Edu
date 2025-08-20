package com.pahanaedu.model;

import java.math.BigDecimal;

public class InvoiceItem {
    private int invoiceItemId;
    private int invoiceId;
    private int itemId;
    private int quantity;
    private BigDecimal priceAtPurchase;
    
    // You can also add a String field for the item title for display purposes
    private String itemTitle;


    // Getters and Setters
    public int getInvoiceItemId() { return invoiceItemId; }
    public void setInvoiceItemId(int id) { this.invoiceItemId = id; }
    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int id) { this.invoiceId = id; }
    public int getItemId() { return itemId; }
    public void setItemId(int id) { this.itemId = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getPriceAtPurchase() { return priceAtPurchase; }
    public void setPriceAtPurchase(BigDecimal price) { this.priceAtPurchase = price; }
    public String getItemTitle() { return itemTitle; }
    public void setItemTitle(String title) { this.itemTitle = title; }
}