package com.lxgzhw.web.servlet;

import com.lxgzhw.dao.impl.AdminDaoImpl;
import com.lxgzhw.domain.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //实现登录的功能
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        String remember = request.getParameter("remember");

        //如果设置了七天免登陆,则直接跳转到首页
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null && cookies.length > 0) {
//            for (Cookie cookie : cookies) {
//                //看看有没有username
//                String name = cookie.getName();
//                if ("username".equalsIgnoreCase(name)) {
//                    System.out.println(name);
//                    System.out.println("需要直接跳转到首页");
//                    request.getRequestDispatcher("/index.jsp").forward(request,response);
////                    response.sendRedirect(request.getContextPath() + "/index.jsp");
//                    return;
//                }
//            }
//        }

        //用户名和密码不能为空
        if (!username.isEmpty() && !password.isEmpty()) {
            //清空登录错误
            session.removeAttribute("loginError");
            //判断验证码
            String serverCheckCode = (String) session.getAttribute("serverCheckCode");
            if (serverCheckCode.equalsIgnoreCase(checkCode)) {
                session.removeAttribute("serverCheckCode");
                //验证码正确
                //封装
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setPassword(password);

                //登录
                AdminDaoImpl adminDao = new AdminDaoImpl();
                Admin login = adminDao.login(admin);
                if (login != null) {
                    //System.out.println(login);
                    session.setAttribute("loginUser", login);
                    //判断是否七天免登陆
                    System.out.println(remember);
                    if (remember != null) {
                        Cookie username1 = new Cookie("username", login.getUsername());
                        //七天免登陆
                        username1.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(username1);
//                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    } else {
//                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    }
                    response.sendRedirect(request.getContextPath() + "/index.jsp");

                } else {
                    System.out.println("登录失败");
                    session.setAttribute("loginError", "用户名或密码不正确");
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                }
            } else {
                session.setAttribute("checkCodeError", "验证码不正确");

                //跳转
                //return;
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            session.setAttribute("loginError", "用户名或密码不能为空");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
