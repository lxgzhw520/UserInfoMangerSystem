package com.lxgzhw.test;

import com.lxgzhw.domain.Page;
import com.lxgzhw.domain.Search;
import com.lxgzhw.domain.UserInfo;
import com.lxgzhw.service.UserInfoService;
import com.lxgzhw.service.impl.UserInfoServiceImpl;
import org.junit.Test;

import java.util.ArrayList;

public class UserInfoServiceImplTest {
    UserInfoService userInfoService =
            new UserInfoServiceImpl();

    @Test
    public void demo01() {
        //测试list方法
        ArrayList<UserInfo> list = userInfoService.list();
        list.stream().forEach(System.out::println);
    }

    @Test
    public void addTest() {
        //测试添加的方法
        UserInfo userInfo = new UserInfo();
        userInfo.setName("大黄");
        userInfo.setGender("男");
        userInfo.setAge(100);
        userInfo.setAddress("异世大陆");
        userInfo.setQq("123");
        userInfo.setEmail("123@qq.com");
        boolean add = userInfoService.add(userInfo);
        System.out.println(add);
    }

    @Test
    public void getUserInfoByIdTest() {
        UserInfo userInfoById = userInfoService.getUserInfoById("1402");
        System.out.println(userInfoById);
    }

    @Test
    public void getUserInfoPageTest() {
        Page<UserInfo> userInfoPage = userInfoService.getUserInfoPage(2, 10);
//        System.out.println(userInfoPage);
        //userInfoPage.getList().stream().forEach(System.out::println);
        System.out.println(userInfoPage.getTotalPage());
        System.out.println(userInfoPage.getPageRange());
    }

    @Test
    public void getUserInfoPageTest01() {
        Search search = new Search();
        search.setName("鹏");
        //search.setEmail("");
        Page<UserInfo> userInfoPage = userInfoService.getUserInfoPage(1, 10, search);
        System.out.println(userInfoPage);
        //userInfoPage.getList().stream().forEach(System.out::println);
    }
}
