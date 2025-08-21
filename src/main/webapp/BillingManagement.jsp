<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Billing Management</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
</head>
<body class="bg-[#102323]">
<div class="relative flex size-full min-h-screen flex-col">
    <header class="flex items-center justify-between whitespace-nowrap border-b border-solid border-b-[#224949] px-10 py-3">
        <h2 class="text-white text-lg font-bold">Pahana Edu Admin</h2>
        <div class="flex items-center gap-9">
            <a class="text-white text-sm font-medium" href="dashboard">Dashboard</a>
                <a class="text-white text-sm font-medium" href="inventory">Inventory</a>
                <a class="text-white text-sm font-medium" href="billing">Billing</a>
                <a class="text-white text-sm font-medium" href="customers">Customers</a>
                <a class="text-white text-sm font-medium" href="reports">Reports</a>
                 <a class="text-red-400 text-sm font-medium" href="logout">Logout</a>
        </div>
    </header>

    <div class="px-40 flex flex-1 justify-center py-5">
        <div class="layout-content-container flex flex-col max-w-[960px] flex-1">
            
            <div class="flex flex-wrap justify-between gap-3 p-4">
                <div class="flex min-w-72 flex-col gap-3">
                    <p class="text-white tracking-light text-[32px] font-bold">Billing Management</p>
                    <p class="text-[#90cbcb] text-sm">Manage and create invoices for customer orders.</p>
                </div>
            </div>
            
            <h2 class="text-white text-[22px] font-bold leading-tight px-4 pb-3 pt-5">Invoice List</h2>
            <div class="px-4 py-3">
                <div class="flex overflow-hidden rounded-lg border border-[#316868] bg-[#102323]">
                    <table class="flex-1">
                        <thead>
                            <tr class="bg-[#183434]">
                                <th class="px-4 py-3 text-left text-white text-sm font-medium">Invoice #</th>
                                <th class="px-4 py-3 text-left text-white text-sm font-medium">Customer</th>
                                <th class="px-4 py-3 text-left text-white text-sm font-medium">Date</th>
                                <th class="px-4 py-3 text-left text-white text-sm font-medium">Total Amount</th>
                                <th class="px-4 py-3 text-left text-white text-sm font-medium">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="invoice" items="${invoices}">
                                <tr class="border-t border-t-[#316868]">
                                    <td class="h-[72px] px-4 py-2 text-white">INV-2025-${invoice.invoiceId}</td>
                                    <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><c:out value="${customerMap[invoice.customerId]}" /></td>
                                    <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><fmt:formatDate value="${invoice.invoiceDate}" pattern="yyyy-MM-dd" /></td>
                                    <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><fmt:setLocale value="en_US"/><fmt:formatNumber value="${invoice.totalAmount}" type="currency"/></td>
                                    <td class="h-[72px] px-4 py-2"><span class="bg-[#224949] text-white text-xs font-medium me-2 px-2.5 py-0.5 rounded"><c:out value="${invoice.status}"/></span></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <h2 class="text-white text-[22px] font-bold leading-tight px-4 pb-3 pt-5">Create New Invoice</h2>
            <c:if test="${not empty errorMessage}"><p class="px-4 text-red-500">${errorMessage}</p></c:if>
            
            <form action="billing" method="POST">
                <div class="flex max-w-[480px] px-4 py-3">
                    <select name="customerId" class="form-select w-full rounded-lg text-white bg-[#183434] border border-[#316868] h-14" required>
                        <option value="">Select Customer...</option>
                        <c:forEach var="customer" items="${customers}"><option value="${customer.customerId}"><c:out value="${customer.name}" /></option></c:forEach>
                    </select>
                </div>
                <div class="flex max-w-[480px] px-4 py-3">
                    <select name="itemId" class="form-select w-full rounded-lg text-white bg-[#183434] border border-[#316868] h-14" required>
                        <option value="">Select Item...</option>
                        <c:forEach var="item" items="${items}"><option value="${item.itemId}"><c:out value="${item.title}" /> (Stock: ${item.quantity})</option></c:forEach>
                    </select>
                </div>
                <div class="flex max-w-[480px] px-4 py-3">
                    <input name="quantity" type="number" min="1" placeholder="Enter Quantity" class="form-input w-full rounded-lg text-white bg-[#183434] border border-[#316868] h-14" required />
                </div>
                <div class="flex max-w-[480px] px-4 py-3 justify-end">
                    <button type="submit" class="flex min-w-[84px] cursor-pointer items-center justify-center rounded-lg h-10 px-4 bg-[#0df2f2] text-[#102323] text-sm font-bold">
                        <span class="truncate">Create Invoice</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>