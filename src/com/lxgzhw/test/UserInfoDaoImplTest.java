package com.lxgzhw.test;

import com.lxgzhw.dao.impl.UserInfoDaoImpl;
import com.lxgzhw.domain.Search;
import com.lxgzhw.domain.UserInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImplTest {
    UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();

    @Test
    public void demo01() {
        //测试list方法
        ArrayList<UserInfo> list = userInfoDao.list();
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
        boolean add = userInfoDao.add(userInfo);
        System.out.println(add);
    }

    @Test
    public void deleteTest() {
        boolean delete = userInfoDao.delete("1403");
        System.out.println(delete);
    }

    @Test
    public void getUserInfoByIdTest() {
        UserInfo userInfoById = userInfoDao.getUserInfoById(1);
        System.out.println(userInfoById);
    }

    @Test
    public void updateTest() {
        //UserInfo{id=1402, name='lxgzhw002', gender='男', age=25, address='花果山', qq='1156956636', email='lxgzhw002@qq.com'}
        UserInfo userInfo = new UserInfo();
        userInfo.setName("理想国真恵玩");
        userInfo.setId(1402);
        userInfo.setGender("男");
        boolean update = userInfoDao.update(userInfo);
        System.out.println(update);
    }

    @Test
    public void countTest() {
        int count = userInfoDao.count();
        System.out.println(count);
    }

    @Test
    public void limitTest() {
        List<UserInfo> limit = userInfoDao.limit(0, 10);
        System.out.println(limit);
    }

    @Test
    public void limitTest01() {
        Search search = new Search();
        //search.setName("大鹏");
        search.setAddress("贵州");
        //System.out.println(search.getEmail());
        List<UserInfo> limit = userInfoDao.limit(0, 10, search);
        //System.out.println(limit);
        limit.stream().forEach(System.out::println);
    }

    @Test
    public void countTest01() {
        //根据条件查询总记录数
        Search search = new Search();
        search.setName("鹏");
        int count = userInfoDao.count(search);
        System.out.println(count);
    }
}
