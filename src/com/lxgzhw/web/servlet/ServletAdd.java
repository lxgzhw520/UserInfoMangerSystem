package com.lxgzhw.web.servlet;

import com.lxgzhw.domain.UserInfo;
import com.lxgzhw.service.impl.UserInfoServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

@WebServlet("/ServletAdd")
public class ServletAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //获取用户数据
        request.setCharacterEncoding("utf8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        UserInfo userInfo = new UserInfo();
        try {
            //封装数据
            BeanUtils.populate(userInfo, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //保存
        //判断userinfo是否为空
        System.out.println(userInfo);
        boolean flag = false;
        if (userInfo.getName().isEmpty()) {
            session.setAttribute("addNameError", "用户名不能为空");
            flag = true;
        } else if (userInfo.getAge() == 0) {
            session.setAttribute("addAgeError", "年龄不能为空");
            flag = true;
        }
        if (flag) {
            //遇到错误,就返回
            response.sendRedirect(request.getContextPath() + "/add.jsp");
        } else {
            UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
            boolean add = userInfoService.add(userInfo);
            if (add) {
                //添加成功
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                session.setAttribute("addError", "数据有误,请检查后重新输入");
                response.sendRedirect(request.getContextPath() + "/add.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
