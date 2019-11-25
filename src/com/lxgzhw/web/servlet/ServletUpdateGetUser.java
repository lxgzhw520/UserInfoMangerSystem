package com.lxgzhw.web.servlet;

import com.lxgzhw.domain.UserInfo;
import com.lxgzhw.service.impl.UserInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletUpdateGetUser")
public class ServletUpdateGetUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户信息
        String id = request.getParameter("id");
        UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
//        System.out.println(id);
        UserInfo user = userInfoService.getUserInfoById(id);
        //存储用户
        request.setAttribute("user", user);
//        System.out.println(user);
        request.getRequestDispatcher(request.getContextPath() + "/update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
