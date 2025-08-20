<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Pahana Edu - Login</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
</head>
<body>
    <div class="relative flex size-full min-h-screen flex-col bg-[#f8fcfc]">
        <div class="px-40 flex flex-1 justify-center py-5">
            <div class="layout-content-container flex flex-col w-[512px] max-w-[512px] py-5">
                <h2 class="text-[#0d1c1c] tracking-light text-[28px] font-bold leading-tight text-center pb-3 pt-5">
                    Pahana Edu - Welcome Back
                </h2>
                
                <c:if test="${not empty errorMessage}">
                    <p class="text-red-500 text-center py-2">${errorMessage}</p>
                </c:if>

                <form action="login" method="post">
                    <div class="flex max-w-[480px] flex-wrap items-end gap-4 px-4 py-3">
                        <input name="username" placeholder="Username" required class="form-input flex w-full min-w-0 flex-1 rounded-lg text-[#0d1c1c] border-[#cee8e8] h-14 p-[15px]" value="admin"/>
                    </div>
                    <div class="flex max-w-[480px] flex-wrap items-end gap-4 px-4 py-3">
                        <input name="password" type="password" placeholder="Password" required class="form-input flex w-full min-w-0 flex-1 rounded-lg text-[#0d1c1c] border-[#cee8e8] h-14 p-[15px]" value="password123"/>
                    </div>
                    <div class="flex px-4 py-3">
                        <button type="submit" class="flex min-w-[84px] max-w-[480px] cursor-pointer items-center justify-center rounded-lg h-12 px-5 flex-1 bg-[#0df2f2] text-[#0d1c1c] text-base font-bold">
                            <span>Log in</span>
                        </button>
                        <p class="text-[#499c9c] text-sm text-center pt-3 px-4">
    Don't have an account? <a href="signup" class="underline">Sign Up</a>
</p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>