<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Reports</title>
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
                    <p class="text-white tracking-light text-[32px] font-bold">Reports</p>
                    <p class="text-[#90cbcb] text-sm">Generate and view sales reports.</p>
                </div>
            </div>
            
            <h3 class="text-white text-lg font-bold leading-tight px-4 pb-2 pt-4">Generate Sales Report</h3>
            <form action="reports" method="POST" class="p-4">
                <div class="flex flex-wrap items-end gap-4">
                    <label class="flex flex-col min-w-40 flex-1">
                        <p class="text-white text-base font-medium pb-2">Start Date</p>
                        <input name="startDate" type="date" value="${startDate}" class="form-input rounded-lg text-white bg-[#224949] border-none h-14" required />
                    </label>
                    <label class="flex flex-col min-w-40 flex-1">
                        <p class="text-white text-base font-medium pb-2">End Date</p>
                        <input name="endDate" type="date" value="${endDate}" class="form-input rounded-lg text-white bg-[#224949] border-none h-14" required />
                    </label>
                    <button type="submit" class="flex items-center justify-center rounded-lg h-10 px-4 bg-[#0df2f2] text-[#102323] text-sm font-bold">
                        <span class="truncate">Generate Report</span>
                    </button>
                </div>
            </form>

            <c:if test="${not empty reportResults}">
                <h3 class="text-white text-lg font-bold leading-tight px-4 pb-2 pt-4">Report Preview</h3>
                <div class="px-4 py-3">
                    <div class="flex overflow-hidden rounded-lg border border-[#316868] bg-[#102323]">
                        <table class="flex-1">
                            <thead>
                                <tr class="bg-[#183434]">
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Date</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Invoice #</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Customer</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Total Amount</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="invoice" items="${reportResults}">
                                    <tr class="border-t border-t-[#316868]">
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><fmt:formatDate value="${invoice.invoiceDate}" pattern="yyyy-MM-dd" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]">INV-2025-${invoice.invoiceId}</td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><c:out value="${customerMap[invoice.customerId]}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><fmt:setLocale value="en_US"/><fmt:formatNumber value="${invoice.totalAmount}" type="currency"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>