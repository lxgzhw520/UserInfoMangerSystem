package com.lxgzhw.service.impl;

import com.lxgzhw.dao.UserInfoDao;
import com.lxgzhw.dao.impl.UserInfoDaoImpl;
import com.lxgzhw.domain.Page;
import com.lxgzhw.domain.Search;
import com.lxgzhw.domain.UserInfo;
import com.lxgzhw.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    UserInfoDao userInfoDao = new UserInfoDaoImpl();

    @Override
    public ArrayList<UserInfo> list() {
        return userInfoDao.list();
    }

    @Override
    public boolean add(UserInfo user) {
        return userInfoDao.add(user);
    }

    @Override
    public boolean delete(String id) {
        return userInfoDao.delete(id);
    }

    @Override
    public UserInfo getUserInfoById(String id) {
        UserInfo userInfoById = userInfoDao.getUserInfoById(Integer.parseInt(id));
        return userInfoById;
    }

    @Override
    public boolean update(UserInfo user) {
        return userInfoDao.update(user);
    }

    @Override
    public boolean deleteIdList(String[] idList) {
        int length = idList.length;
        int count = 0;
        for (String id : idList) {
            boolean delete = userInfoDao.delete(id);
            if (delete) {
                count += 1;
            }
        }
        return length == count;
    }

    @Override
    public Page<UserInfo> getUserInfoPage(int currentPage, int rows) {
        //1.获取总记录数
        int count = userInfoDao.count();

        //2.获取分页数据
        //2.1 计算开始索引
        //2.2 开始索引 = (当前页 - 1) * 每页数量
        int start = (currentPage - 1) * rows;
        List<UserInfo> userInfos = userInfoDao.limit(start, rows);

        //3.创建分页对象
        Page<UserInfo> userInfoPage = new Page<>();

        //4.计算总页数
        int totalPage = count % rows == 0 ? count % rows : count % rows + 1;

        //5.设置分页对象的信息
        userInfoPage.setCurrentPage(currentPage);
        userInfoPage.setRows(rows);
        userInfoPage.setList(userInfos);
        userInfoPage.setTotalCount(count);
        userInfoPage.setTotalPage(totalPage);

        return userInfoPage;
    }

    @Override
    public Page<UserInfo> getUserInfoPage(int currentPage, int rows, Search search) {
        //根据查询条件获取分页信息
        //1.获取总记录数
        int count = userInfoDao.count(search);
        //2.获取分页数据
        //2.1 计算开始索引
        //2.2 开始索引 = (当前页 - 1) * 每页数量
        int start = (currentPage - 1) * rows;
        List<UserInfo> userInfos = userInfoDao.limit(start, rows, search);

        //3.创建分页对象
        Page<UserInfo> userInfoPage = new Page<>();

        //4.计算总页数
        int totalPage = count % rows == 0 ? count % rows : count % rows + 1;

        //5.设置分页对象的信息
        userInfoPage.setCurrentPage(currentPage);
        userInfoPage.setRows(rows);
        userInfoPage.setList(userInfos);
        userInfoPage.setTotalCount(count);
        userInfoPage.setTotalPage(totalPage);

        return userInfoPage;
    }
}
