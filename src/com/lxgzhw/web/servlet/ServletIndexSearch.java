package com.lxgzhw.web.servlet;

import com.lxgzhw.domain.Page;
import com.lxgzhw.domain.Search;
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
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/ServletIndexSearch")
public class ServletIndexSearch extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        //实现复杂查询的功能
        Search search = new Search();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(search,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //进行查询
        //System.out.println(search);
        UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
        //获取当前页和总页码数
        String p = request.getParameter("currentPage");
        String size = request.getParameter("rows");
        //查看
        int currentPage = 1;
        int rows = 10;
        if (p != null) {
            currentPage = Integer.parseInt(p);
        }
        if (size != null) {
            rows = Integer.parseInt(size);
        }
        //System.out.println("-------");
        Page<UserInfo> userInfoPage = userInfoService.getUserInfoPage(currentPage, rows, search);
        HttpSession session = request.getSession();
        session.setAttribute("userInfoPage", userInfoPage);
        session.setAttribute("search", search);
        session.setAttribute("userInfoList", userInfoPage.getList());
        //System.out.println(userInfoPage.getList());
        //System.out.println(userInfoPage.getList().size());

        //转发
        //System.out.println(userInfoPage);
        response.sendRedirect(request.getContextPath() + "/search.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
