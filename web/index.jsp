<%@ page import="com.lxgzhw.service.impl.UserInfoServiceImpl" %>
<%@ page import="com.lxgzhw.domain.UserInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lxgzhw.domain.Page" %><%--
  编辑器: IntelliJ IDEA.
  作者: 理想国真恵玩-张大鹏
  日期: 2019/11/23
  时间: 20:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>理想国学生信息管理系统</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<%--调用list方法获取所有的用户数据--%>
<%
    UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
    //ArrayList<UserInfo> userInfoList = userInfoService.list();
    //request.setAttribute("userInfoList", userInfoList);
    //获取当前页和总页码数
    String p = request.getParameter("currentPage");
    String size = request.getParameter("rows");
    //System.out.println("p=" + p);
    //System.out.println("size=" + size);
    //System.out.println("boolean p=" + (p == null));
    //System.out.println("boolean size=" + (size == null));
    //查看
    int currentPage = 1;
    int rows = 10;
    if (p != null) {
        currentPage = Integer.parseInt(p);
    }
    if (size != null) {
        rows = Integer.parseInt(size);
    }
    Page<UserInfo> userInfoPage = userInfoService.getUserInfoPage(currentPage, rows);
    request.setAttribute("userInfoPage", userInfoPage);
    request.setAttribute("userInfoList", userInfoPage.getList());
%>
<%--主体部分开始--%>
<div id="main">
    <div class="container">
        <div class="row"><h1 class="text-center">用户信息管理系统</h1></div>
        <div class="row">
            <div class="well bg-warning">
                <%--登录用户提示--%>
                <c:if test="${cookie.get('username')!=null}">
                    <span>${cookie.get('username').value},欢迎您使用本系统!</span>
                </c:if>
                <c:if test="${cookie.get('username')==null}">
                    <span>暂未登录 <a href="${pageContext.request.servletContext}/login.jsp">立即登录?</a></span>
                </c:if>
            </div>
        </div>
        <div class="row">
            <%--展示错误信息--%>
            <c:if test="${sessionScope.deleteSelectedError!=null}">
                <div class="alert alert-danger" role="alert">删除选中失败</div>
            </c:if>
            <c:if test="${sessionScope.updateUserInfoError!=null}">
                <div class="alert alert-danger" role="alert">更新用户信息失败</div>
            </c:if>
        </div>
        <div class="row">
            <div class="col-md-9">
                <form class="form-inline mb-2" method="post" action="/ServletIndexSearch">
                    <div class="form-group">
                        <label for="name">姓名</label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <label for="address">籍贯</label>
                        <input type="text" class="form-control" id="address" name="address">
                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <label for="email">邮箱</label>
                        <input type="email" class="form-control" id="email" name="email">
                    </div>
                    <button type="submit" class="btn btn-primary mb-2">查询</button>
                </form>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/add.jsp" class="btn btn-primary">添加联系人</a>
                <a href="javascript:void(0);" class="btn btn-primary" id="delSelected">删除选中</a>
            </div>
        </div>

        <%--表格--%>
        <div class="row">
            <form action="${pageContext.request.contextPath}/ServletDeleteSelected" id="form"
                  method="post">
                <table class="table table-striped">
                    <tr class="bg-success">
                        <th class="bg-success">
                            <input type="checkbox" id="selectAll">
                        </th>
                        <th class="bg-success">编号</th>
                        <th class="bg-success">姓名</th>
                        <th class="bg-success">性别</th>
                        <th class="bg-success">年龄</th>
                        <th class="bg-success">籍贯</th>
                        <th class="bg-success">QQ</th>
                        <th class="bg-success">邮箱</th>
                        <th class="bg-success">操作</th>
                    </tr>
                    <%--渲染用户信息--%>
                    <c:if test="${empty userInfoList}">
                        <tr>
                            <td>
                                <input type="checkbox">
                            </td>
                            <td>1</td>
                            <td>大鹏</td>
                            <td>男</td>
                            <td>22</td>
                            <td>贵州</td>
                            <td>111</td>
                            <td>111@qq.com</td>
                            <td>
                                <a href="" class="btn btn-default btn-sm">修改</a>
                                <a href="" class="btn btn-default btn-sm">删除</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox">
                            </td>
                            <td>2</td>
                            <td>萃萃</td>
                            <td>女</td>
                            <td>22</td>
                            <td>贵州</td>
                            <td>111</td>
                            <td>111@qq.com</td>
                            <td>
                                <a href="" class="btn btn-sm btn-default">修改</a>
                                <a href="" class="btn btn-sm btn-default">删除</a>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty userInfoList}">
                        <c:forEach items="${userInfoList}" var="user" varStatus="i">
                            <tr>
                                <td>
                                    <input type="checkbox" name="uid" value="${user.id}">
                                </td>
                                <td>${i.count}</td>
                                <td>${user.name}</td>
                                <td>${user.gender}</td>
                                <td>${user.age}</td>
                                <td>${user.address}</td>
                                <td>${user.qq}</td>
                                <td>${user.email}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/ServletUpdateGetUser?id=${user.id}"
                                       class="btn btn-default btn-sm">修改</a>
                                    <a href="#" onclick="deleteUser(${user.id})" class="btn btn-default btn-sm">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </form>
        </div>

        <%--分页--%>
        <div class="row">
            <div class="col-md-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <%--上一页--%>
                        <c:if test="${userInfoPage.currentPage-1>0}">
                            <li>
                                <a href="?currentPage=${userInfoPage.currentPage-1}&rows=10" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${userInfoPage.currentPage-1<=0}">
                            <li class="disabled">
                                <a href="?currentPage=1&rows=10" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <%--中间页--%>
                        <c:if test="${not empty userInfoPage.pageRange}">
                            <c:forEach var="page" varStatus="i" items="${userInfoPage.pageRange}">
                                <%--激活状态--%>
                                <c:if test="${userInfoPage.currentPage==page}">
                                    <li class="active"><a href="?currentPage=${page}&rows=10">${page}</a></li>
                                </c:if>
                                <%--非激活状态--%>
                                <c:if test="${userInfoPage.currentPage!=page}">
                                    <li><a href="?currentPage=${page}&rows=10">${page}</a></li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty userInfoPage.pageRange}">
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                        </c:if>
                        <%--下一页--%>
                        <c:if test="${userInfoPage.currentPage+1<=userInfoPage.totalPage}">
                            <li>
                                <a href="?currentPage=${userInfoPage.currentPage+1}&rows=10" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${userInfoPage.currentPage+1>userInfoPage.totalPage}">
                            <li class="disabled">
                                <a href="?currentPage=${userInfoPage.currentPage+1}&rows=10" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
            <div class="col-md-8">
                <div class="center-block" style="line-height: 316%;font-size: 24px;">共${userInfoPage.totalCount}条记录,共${userInfoPage.totalPage}页</div>
            </div>
        </div>
    </div>
</div>
<%--主体部分结束--%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script>
    //根据id删除用户
    function deleteUser(id) {
        if (confirm("您确定要删除该用户吗?")) {
            //访问路径
            location.href = "${pageContext.request.contextPath}/ServletDeleteUserInfo?id=" + id
        }
    }

    //给删除选中按钮添加单击事件
    document.getElementById("delSelected").onclick = function () {
        if (confirm("您确定要删除选中条目吗？")) {

            var flag = false;
            //判断是否有选中条目
            var cbs = document.getElementsByName("uid");
            for (var i = 0; i < cbs.length; i++) {
                if (cbs[i].checked) {
                    //有一个条目选中了
                    flag = true;
                    break;
                }
            }

            if (flag) {//有条目被选中
                //表单提交
                document.getElementById("form").submit();
            }

        }

    }
    //1.获取第一个cb
    document.getElementById("selectAll").onclick = function () {
        //2.获取下边列表中所有的cb
        var cbs = document.getElementsByName("uid");
        //3.遍历
        for (var i = 0; i < cbs.length; i++) {
            //4.设置这些cbs[i]的checked状态 = firstCb.checked
            cbs[i].checked = this.checked;
        }
    }
</script>
</body>
</html>
