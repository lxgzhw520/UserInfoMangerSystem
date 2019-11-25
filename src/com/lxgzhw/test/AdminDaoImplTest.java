package com.lxgzhw.test;

import com.lxgzhw.dao.impl.AdminDaoImpl;
import com.lxgzhw.domain.Admin;
import org.junit.Test;

public class AdminDaoImplTest {
    @Test
    public void demo01() {
        //测试登录方法
        Admin admin = new Admin();
        admin.setUsername("lxgzhw");
        admin.setPassword("lxgzhw");

        AdminDaoImpl adminDao = new AdminDaoImpl();
        Admin login = adminDao.login(admin);
        if (login != null) {
            System.out.println(login);
        }
    }
}
