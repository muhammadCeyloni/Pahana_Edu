package com.pahanaedu.controller;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.InvoiceDAO;
import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Invoice;
import com.pahanaedu.model.InvoiceItem;
import com.pahanaedu.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/billing")
public class BillingServlet extends HttpServlet {
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ItemDAO itemDAO = new ItemDAO();
    private final InvoiceDAO invoiceDAO = new InvoiceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch all data needed to display the page and populate the forms
            List<Customer> customers = customerDAO.getAllCustomers();
            List<Item> items = itemDAO.getAllItems();
            List<Invoice> invoices = invoiceDAO.getAllInvoices();

            // To display customer names in the invoice list, we can create a map
            request.setAttribute("customerMap", customers.stream().collect(Collectors.toMap(Customer::getCustomerId, Customer::getName)));
            request.setAttribute("customers", customers);
            request.setAttribute("items", items);
            request.setAttribute("invoices", invoices);

            // Forward to the JSP page
            request.getRequestDispatcher("BillingManagement.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error loading billing page", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Get data from the form
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // 2. Fetch the full item details to get the price and check stock
            Item selectedItem = itemDAO.getAllItems().stream()
                .filter(i -> i.getItemId() == itemId)
                .findFirst()
                .orElse(null);

            if (selectedItem == null || selectedItem.getQuantity() < quantity) {
                request.setAttribute("errorMessage", "Item not found or not enough stock.");
                doGet(request, response); // Redisplay form with error
                return;
            }

            // 3. Prepare Invoice and InvoiceItem objects
            Invoice invoice = new Invoice();
            invoice.setCustomerId(customerId);
            invoice.setInvoiceDate(new Date());
            invoice.setStatus("Paid"); // Default status

            InvoiceItem itemForInvoice = new InvoiceItem();
            itemForInvoice.setItemId(selectedItem.getItemId());
            itemForInvoice.setQuantity(quantity);
            itemForInvoice.setPriceAtPurchase(selectedItem.getPrice());

            List<InvoiceItem> invoiceItems = new ArrayList<>();
            invoiceItems.add(itemForInvoice);
            invoice.setInvoiceItems(invoiceItems);

            // 4. Calculate total and set it on the invoice
            BigDecimal total = selectedItem.getPrice().multiply(new BigDecimal(quantity));
            invoice.setTotalAmount(total);

            // 5. Save the invoice using the DAO's transaction method
            invoiceDAO.createInvoice(invoice);

            // 6. Redirect back to the billing page to see the new invoice in the list
            response.sendRedirect("billing");

        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error creating invoice", e);
        }
    }
}