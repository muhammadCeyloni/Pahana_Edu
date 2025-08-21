package com.pahanaedu.controller;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.InvoiceDAO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {
    private final InvoiceDAO invoiceDAO = new InvoiceDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Just display the initial report generation page
        request.getRequestDispatcher("Reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        try {
            // Convert the date strings from the form into Date objects
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);

            // Call the new DAO method to get the filtered report data
            List<Invoice> reportResults = invoiceDAO.getInvoicesByDateRange(startDate, endDate);
            
            // Get customer data to display names in the report
            Map<Integer, String> customerMap = customerDAO.getAllCustomers().stream()
                .collect(Collectors.toMap(Customer::getCustomerId, Customer::getName));

            // Send the results and original dates back to the JSP
            request.setAttribute("reportResults", reportResults);
            request.setAttribute("customerMap", customerMap);
            request.setAttribute("startDate", startDateStr);
            request.setAttribute("endDate", endDateStr);

            // Forward back to the same page to display the results
            request.getRequestDispatcher("Reports.jsp").forward(request, response);

        } catch (SQLException | ParseException e) {
            request.setAttribute("errorMessage", "Error generating report. Please check the dates.");
            request.getRequestDispatcher("Reports.jsp").forward(request, response);
        }
    }
}