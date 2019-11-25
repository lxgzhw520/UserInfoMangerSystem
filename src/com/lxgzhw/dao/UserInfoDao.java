package com.lxgzhw.dao;

import com.lxgzhw.domain.Search;
import com.lxgzhw.domain.UserInfo;

import java.util.ArrayList;
import java.util.List;

public interface UserInfoDao {
    /**
     * 获取所有的用户信息,封装到列表中
     */
    ArrayList<UserInfo> list();

    /**
     * 添加用户数据到数据库的方法,返回添加结果
     */
    boolean add(UserInfo user);

    /**
     * 根据id删除用户信息
     */
    boolean delete(String id);


    /**
     * 根据id获取用户
     */
    UserInfo getUserInfoById(Integer id);

    /**
     * 根据用户实体类对象修改用户在数据库的信息
     */
    boolean update(UserInfo user);

    /**
     * 查询数据库中的记录数
     *
     * @return 记录数
     */
    int count();

    /**
     * 查询数据库中的记录数
     *
     * @param search 搜索条件
     * @return 满足搜索条件的记录数
     */
    int count(Search search);

    /**
     * 分页查询
     *
     * @param start 开始索引
     * @param rows  每页数量
     * @return 查询到的list集合
     */
    List<UserInfo> limit(int start, int rows);

    /**
     * 分页查询
     *
     * @param start  开始索引
     * @param rows   每页数量
     * @param search 搜索条件
     * @return 查询到的List集合
     */
    List<UserInfo> limit(int start, int rows, Search search);
}
