package com.lxgzhw.web.servlet;

import com.lxgzhw.service.impl.UserInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ServletDeleteUserInfo")
public class ServletDeleteUserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理用户的删除请求
        String id = request.getParameter("id");
        UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
        boolean delete = userInfoService.delete(id);
        if (delete) {
            HttpSession session = request.getSession();
            session.setAttribute("deleteUserInfoError", "删除用户失败");
        }
        System.out.println("触发了删除功能");
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
