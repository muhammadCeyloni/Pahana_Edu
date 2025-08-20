<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
</head>
<body>
<div class="relative flex size-full min-h-screen flex-col bg-[#102323]">
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
            <div class="flex flex-col max-w-[960px] flex-1">
                 <div class="flex flex-wrap justify-between gap-3 p-4">
                    <div class="flex min-w-72 flex-col gap-3">
                        <p class="text-white tracking-light text-[32px] font-bold">Dashboard</p>
                        <p class="text-[#90cbcb] text-sm">Welcome back, ${sessionScope.user.username}</p>
                    </div>
                </div>
                </div>
        </div>
    </div>
</div>
</body>
</html>