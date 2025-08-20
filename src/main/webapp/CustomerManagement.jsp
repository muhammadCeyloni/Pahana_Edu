<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Customer Management</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
</head>
<body class="bg-[#102323]">
<div class="relative flex size-full min-h-screen flex-col">
    <div class="layout-container flex h-full grow flex-col">
        
        <header class="flex items-center justify-between whitespace-nowrap border-b border-solid border-b-[#224949] px-10 py-3">
            <h2 class="text-white text-lg font-bold">Bookshop Admin</h2>
            <div class="flex items-center gap-9">
                <a class="text-white text-sm font-medium" href="dashboard">Dashboard</a>
                <a class="text-white text-sm font-medium" href="inventory">Inventory</a>
                <a class="text-white text-sm font-medium" href="#">Billing</a>
                <a class="text-white text-sm font-medium" href="customers">Customers</a>
                <a class="text-white text-sm font-medium" href="#">Reports</a>
                <a class="text-red-400 text-sm font-medium" href="logout">Logout</a>
            </div>
        </header>

        <div class="px-40 flex flex-1 justify-center py-5">
            <div class="layout-content-container flex flex-col max-w-[960px] flex-1">
                
                <div class="flex flex-wrap justify-between gap-3 p-4">
                    <div class="flex min-w-72 flex-col gap-3">
                        <p class="text-white tracking-light text-[32px] font-bold">Customer Management</p>
                        <p class="text-[#90cbcb] text-sm">Manage customer records, including adding, editing, and deleting customer information.</p>
                    </div>
                </div>

                <div class="px-4 py-3">
                    <div class="flex overflow-hidden rounded-lg border border-[#316868] bg-[#102323]">
                        <table class="flex-1">
                            <thead>
                                <tr class="bg-[#183434]">
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Name</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Contact</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Address</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Account #</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="customer" items="${customers}">
                                    <tr class="border-t border-t-[#316868]">
                                        <td class="h-[72px] px-4 py-2 text-white"><c:out value="${customer.name}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><c:out value="${customer.telephone}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><c:out value="${customer.address}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><c:out value="${customer.accountNumber}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb] text-sm font-bold">
    <a href="#" class="hover:underline">Edit</a> |
    <a href="customers?action=delete&id=${customer.customerId}" class="hover:underline text-red-400" onclick="return confirm('Are you sure you want to delete this customer?');">
        Delete
    </a>
</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <h2 class="text-white text-[22px] font-bold leading-tight px-4 pb-3 pt-5">Add New Customer</h2>
                
                <form action="customers" method="POST">
                    <div class="flex max-w-[480px] flex-wrap items-end gap-4 px-4 py-3">
                        <label class="flex flex-col min-w-40 flex-1">
                            <p class="text-white text-base font-medium leading-normal pb-2">Name</p>
                            <input name="name" placeholder="Enter customer name" class="form-input flex w-full rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                        </label>
                    </div>
                    <div class="flex max-w-[480px] flex-wrap items-end gap-4 px-4 py-3">
                        <label class="flex flex-col min-w-40 flex-1">
                            <p class="text-white text-base font-medium leading-normal pb-2">Contact</p>
                            <input name="contact" placeholder="Enter contact information" class="form-input flex w-full rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                        </label>
                    </div>
                    <div class="flex max-w-[480px] flex-wrap items-end gap-4 px-4 py-3">
                        <label class="flex flex-col min-w-40 flex-1">
                            <p class="text-white text-base font-medium leading-normal pb-2">Address</p>
                            <input name="address" placeholder="Enter customer address" class="form-input flex w-full rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                        </label>
                    </div>
                    <div class="flex px-4 py-3 justify-end">
                        <button type="submit" class="flex min-w-[84px] cursor-pointer items-center justify-center rounded-lg h-10 px-4 bg-[#0df2f2] text-[#102323] text-sm font-bold">
                            <span class="truncate">Add Customer</span>
                        </button>
                    </div>
                </form>
                
            </div>
        </div>
    </div>
</div>
</body>
</html>