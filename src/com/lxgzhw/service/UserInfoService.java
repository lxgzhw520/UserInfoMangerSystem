package com.lxgzhw.service;

import com.lxgzhw.domain.Page;
import com.lxgzhw.domain.Search;
import com.lxgzhw.domain.UserInfo;

import java.util.ArrayList;

public interface UserInfoService {
    /**
     * 返回用户信息列表
     */
    ArrayList<UserInfo> list();

    /**
     * 添加用户到数据库
     */
    boolean add(UserInfo user);

    /**
     * 从数据库删除用户信息
     */
    boolean delete(String id);

    /**
     * 根据id获取用户
     */
    UserInfo getUserInfoById(String id);

    /**
     * 更新用户
     */
    boolean update(UserInfo user);

    /**
     * 根据id的列表,删除列表中所有的记录
     *
     * @param idList 用户id的列表
     * @return 删除结果
     */
    boolean deleteIdList(String[] idList);

    /**
     * 查询用户信息的分页数据,并封装为分页对象
     *
     * @param currentPage 当前页
     * @param rows        每页数量
     * @return 查询到的分页对象
     */
    Page<UserInfo> getUserInfoPage(int currentPage, int rows);

    /**
     * 查询用户信息的分页数据,并封装为分页对象
     *
     * @param currentPage 当前页
     * @param rows        每页数量
     * @param search      搜索条件
     * @return 查询到的分页对象
     */
    Page<UserInfo> getUserInfoPage(int currentPage, int rows, Search search);
}
