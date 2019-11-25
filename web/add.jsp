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
    <title>添加页面</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
        .text-danger {
            color: red;
        }
    </style>
</head>
<body>
<%--如果没有登录,则跳转到登录页面--%>
<c:if test="${cookie.get('username')==null}">
    <%response.sendRedirect(request.getContextPath() + "/login.jsp");%>
</c:if>
<div class="container">
    <div class="row">
        <h3 class="text-center">添加新用户</h3>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="/ServletAdd" method="post">
                <%-- // id   name, gender, age, address, qq, email--%>
                <div class="form-group">
                    <label for="name">姓名</label>
                    <input type="text" class="form-control" id="name" placeholder="请输入姓名" name="name">
                    <p class="help-block text-danger">${addNameError}</p>
                </div>
                <div class="form-group">
                    <label for="gender">性别</label>
                    <select class="form-control" name="gender" id="gender">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="age">年龄</label>
                    <input type="number" class="form-control" id="age" placeholder="请输入年龄" name="age">
                    <p class="help-block text-danger">${addAgeError}</p>
                </div>
                <div class="form-group">
                    <label for="address">地址</label>
                    <input type="text" class="form-control" id="address" placeholder="请输入地址" name="address">
                </div>
                <div class="form-group">
                    <label for="qq">QQ</label>
                    <input type="text" class="form-control" id="qq" placeholder="请输入QQ" name="qq">
                </div>
                <div class="form-group">
                    <label for="email">邮箱</label>
                    <input type="email" class="form-control" id="email" placeholder="请输入邮箱" name="email">
                </div>
                <button type="submit" class="btn btn-default">添加</button>
                <button type="reset" class="btn btn-default">重置</button>
                <button type="reset" class="btn btn-default">返回</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
