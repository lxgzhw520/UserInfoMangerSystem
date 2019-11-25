<%--
  编辑器: IntelliJ IDEA.
  作者: 理想国真恵玩-张大鹏
  日期: 2019/11/23
  时间: 21:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录页面</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
        .text-danger {
            color: red;
        }
    </style>
</head>
<body>
<%--判断cookie--%>
<c:if test="${cookie.get('username')!=null}">
    <%response.sendRedirect(request.getContextPath() + "/index.jsp");%>
</c:if>
<div class="container">
    <div class="row">
        <h3 class="text-center">管理员登录</h3>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="/ServletLogin" method="post">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username">
                    <p class="help-block text-danger">${loginError}</p>
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">
                    <p class="help-block text-danger">${loginError}</p>
                </div>
                <div class="form-group">
                    <label for="checkCode">验证码</label><br>
                    <img src="/ServletCheckCode" alt="验证码" style="margin-bottom: 5px;" id="checkCodeImg">
                    <input class="form-control" type="text" id="checkCode" name="checkCode">
                    <p class="help-block text-danger">${checkCodeError}</p>
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="remember"> 记住我
                    </label>
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>
    </div>
</div>
<script>
    window.onload = function (ev) {
        document.getElementById("checkCodeImg").onclick = function (ev1) {
            this.src = "/ServletCheckCode?date=" + new Date()
        }
    }
</script>
</body>
</html>
