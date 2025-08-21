<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Customer</title>
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
            <h2 class="text-white text-[22px] font-bold leading-tight px-4 pb-3 pt-5">Edit Customer: ${customer.name}</h2>

            <form action="customers" method="POST">
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="id" value="${customer.customerId}" />

                <div class="flex max-w-[480px] px-4 py-3">
                    <label class="flex flex-col flex-1">
                        <p class="text-white text-base font-medium pb-2">Name</p>
                        <input name="name" value="${customer.name}" class="form-input w-full rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    </label>
                </div>
                <div class="flex max-w-[480px] px-4 py-3">
                    <label class="flex flex-col flex-1">
                        <p class="text-white text-base font-medium pb-2">Contact</p>
                        <input name="contact" value="${customer.telephone}" class="form-input w-full rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    </label>
                </div>
                <div class="flex max-w-[480px] px-4 py-3">
                    <label class="flex flex-col flex-1">
                        <p class="text-white text-base font-medium pb-2">Address</p>
                        <input name="address" value="${customer.address}" class="form-input w-full rounded-lg text-white bg-[#224949] border-none h-14 p-4" required />
                    </label>
                </div>
                <div class="flex px-4 py-3 justify-end">
                    <button type="submit" class="flex items-center justify-center rounded-lg h-10 px-4 bg-[#0df2f2] text-[#102323] text-sm font-bold">
                        <span class="truncate">Update Customer</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>