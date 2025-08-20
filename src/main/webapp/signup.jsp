<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign Up</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
</head>
<body class="bg-[#f8fcfc]">
<div class="relative flex size-full min-h-screen flex-col">
    <div class="px-40 flex flex-1 justify-center py-5">
        <div class="layout-content-container flex flex-col w-[512px] max-w-[512px] py-5">
            <h2 class="text-[#0d1c1c] tracking-light text-[28px] font-bold text-center pb-3 pt-5">Sign Up for an Account</h2>

            <c:if test="${not empty errorMessage}">
                <p class="text-red-500 text-center py-2">${errorMessage}</p>
            </c:if>

            <form action="signup" method="POST">
                <div class="px-4 py-3">
                    <label class="flex flex-col">
                        <p class="text-[#0d1c1c] text-base font-medium pb-2">Username</p>
                        <input name="username" placeholder="Enter a username" class="form-input w-full rounded-lg border-[#cee8e8] h-14" required />
                    </label>
                </div>
                <div class="px-4 py-3">
                    <label class="flex flex-col">
                        <p class="text-[#0d1c1c] text-base font-medium pb-2">Password</p>
                        <input name="password" type="password" placeholder="Enter a password" class="form-input w-full rounded-lg border-[#cee8e8] h-14" required />
                    </label>
                </div>
                <div class="flex px-4 py-3">
                    <button type="submit" class="flex min-w-[84px] cursor-pointer items-center justify-center rounded-lg h-10 px-4 flex-1 bg-[#0df2f2] text-[#0d1c1c] text-sm font-bold">
                        <span class="truncate">Sign Up</span>
                    </button>
                </div>
                <p class="text-[#499c9c] text-sm text-center pt-1 px-4">
                    Already have an account? <a href="login.jsp" class="underline">Sign In</a>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>