<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Inventory Management</title>
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
                        <p class="text-white tracking-light text-[32px] font-bold">Inventory Management</p>
                        <p class="text-[#90cbcb] text-sm">Manage your book inventory, add new items, and update existing ones.</p>
                    </div>
                </div>

                <div class="px-4 py-3">
                    <div class="flex overflow-hidden rounded-lg border border-[#316868] bg-[#102323]">
                        <table class="flex-1">
                            <thead>
                                <tr class="bg-[#183434]">
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Title</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Author</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">ISBN</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Quantity</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Price</th>
                                    <th class="px-4 py-3 text-left text-white text-sm font-medium">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${items}">
                                    <tr class="border-t border-t-[#316868]">
                                        <td class="h-[72px] px-4 py-2 text-white"><c:out value="${item.title}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><c:out value="${item.author}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><c:out value="${item.isbn}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]"><c:out value="${item.quantity}" /></td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb]">
                                            <fmt:setLocale value="en_US"/>
                                            <fmt:formatNumber value="${item.price}" type="currency"/>
                                        </td>
                                        <td class="h-[72px] px-4 py-2 text-[#90cbcb] text-sm font-bold">
                                            <a href="#" class="hover:underline">Edit</a> | 
                                            <a href="inventory?action=delete&id=${item.itemId}" class="hover:underline text-red-400" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <h2 class="text-white text-[22px] font-bold leading-tight px-4 pb-3 pt-5">Add New Item</h2>
                <form action="inventory" method="POST">
                    <div class="flex gap-4 px-4 py-3">
                        <input name="title" placeholder="Enter book title" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                        <input name="author" placeholder="Enter author's name" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    </div>
                    <div class="flex gap-4 px-4 py-3">
                        <input name="isbn" placeholder="Enter ISBN" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                        <input name="quantity" type="number" placeholder="Enter quantity" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                        <input name="price" type="text" placeholder="Enter price (e.g., 9.99)" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    </div>
                     <div class="flex gap-4 px-4 py-3 items-center">
                        <input name="category" placeholder="Enter category" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                        <button type="submit" class="flex items-center justify-center rounded-lg h-10 px-6 bg-[#0df2f2] text-[#102323] text-sm font-bold">
                            <span class="truncate">Add Item</span>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>