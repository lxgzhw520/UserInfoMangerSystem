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
import java.util.Map;

@WebServlet("/ServletUpdateUserInfo")
public class ServletUpdateUserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当提交的数据用中文字段的时候,一定不要忘了设置编码
        request.setCharacterEncoding("utf-8");
        //获取用户信息,转发到update.jsp
        Map<String, String[]> parameterMap = request.getParameterMap();
        UserInfo userInfo = new UserInfo();
        try {
            BeanUtils.populate(userInfo, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //System.out.println(userInfo);
        //更新
        UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
        boolean update = userInfoService.update(userInfo);
        if (!update) {
            HttpSession session = request.getSession();
            session.setAttribute("updateUserInfoError", "更新用户信息失败");
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
