package com.lxgzhw.web.servlet;

import com.lxgzhw.service.impl.UserInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ServletDeleteSelected")
public class ServletDeleteSelected extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取选中
        String[] uids = request.getParameterValues("uid");
//        System.out.println(uids);
        UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
        boolean b = userInfoService.deleteIdList(uids);
        if (!b) {
            HttpSession session = request.getSession();
            session.setAttribute("deleteSelectedError", "删除选中列表失败");
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
