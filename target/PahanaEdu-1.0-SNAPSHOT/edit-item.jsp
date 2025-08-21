<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Item</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
</head>
<body class="bg-[#102323]">
<div class="relative flex size-full min-h-screen flex-col">
    <header class="flex items-center justify-between whitespace-nowrap border-b border-solid border-b-[#224949] px-10 py-3">
         <h2 class="text-white text-lg font-bold">Bookshop Admin</h2>
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
            <h2 class="text-white text-[22px] font-bold leading-tight px-4 pb-3 pt-5">Edit Item: ${item.title}</h2>

            <form action="inventory" method="POST">
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="id" value="${item.itemId}" />

                <div class="flex gap-4 px-4 py-3">
                    <input name="title" value="${item.title}" placeholder="Book title" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    <input name="author" value="${item.author}" placeholder="Author's name" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                </div>
                <div class="flex gap-4 px-4 py-3">
                    <input name="isbn" value="${item.isbn}" placeholder="ISBN" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    <input name="quantity" value="${item.quantity}" type="number" placeholder="Quantity" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    <input name="price" value="${item.price}" type="text" placeholder="Price (e.g., 9.99)" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                </div>
                <div class="flex gap-4 px-4 py-3 items-center">
                    <input name="category" value="${item.category}" placeholder="Category" class="form-input flex-1 rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    <button type="submit" class="flex items-center justify-center rounded-lg h-10 px-6 bg-[#0df2f2] text-[#102323] text-sm font-bold">
                        <span class="truncate">Update Item</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>