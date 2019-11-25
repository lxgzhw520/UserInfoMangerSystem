package com.lxgzhw.dao.impl;

import com.lxgzhw.dao.UserInfoDao;
import com.lxgzhw.domain.Search;
import com.lxgzhw.domain.UserInfo;
import com.lxgzhw.utils.DruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {
    JdbcTemplate template =
            new JdbcTemplate(DruidUtils.getDataSource());

    @Override
    public ArrayList<UserInfo> list() {
        //获取所有的用户信息,封装到列表中
        String sql = "select *from userinfo order by id desc";
        List<UserInfo> userInfoList = template.query(
                sql,
                new BeanPropertyRowMapper<>(UserInfo.class)
        );
        return (ArrayList<UserInfo>) userInfoList;
    }

    @Override
    public boolean add(UserInfo user) {
        //添加user到数据库
        // id   name, gender, age, address, qq, email
        String sql = "insert into userinfo(name, gender, age, address, qq, email) values(?,?,?,?,?,?)";
        int update = template.update(
                sql, user.getName(), user.getGender(), user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail()
        );
        return update > 0;
    }

    @Override
    public boolean delete(String id) {
        String sql = "delete from userinfo where id=?";
        int update = template.update(sql, id);
        return update > 0;
    }

    @Override
    public UserInfo getUserInfoById(Integer id) {
        String sql = "select *from userinfo where id=?";
        UserInfo userInfo = template.queryForObject(sql, new BeanPropertyRowMapper<>(UserInfo.class), id);
        return userInfo;
    }

    @Override
    public boolean update(UserInfo user) {
        //更新用户的信息
        // id   name, gender, age, address, qq, email
        String sql = "update userinfo set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        int update = template.update(sql, user.getName(), user.getGender(), user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail(), user.getId());
        return update > 0;
    }

    @Override
    public int count() {
        String sql = "select count(id) from userinfo";
        Integer count = template.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public int count(Search search) {
        if (search.getName() == null) {
            search.setName("");
        }
        if (search.getAddress() == null) {
            search.setAddress("");
        }
        if (search.getEmail() == null) {
            search.setEmail("");
        }
        String sql = "select count(id) from userinfo where name like ? and address like ? and email like ?";
        Integer integer = template.queryForObject(sql, Integer.class,
                "%" + search.getName() + "%",
                "%" + search.getAddress() + "%",
                "%" + search.getEmail() + "%");
        return integer;
    }

    @Override
    public List<UserInfo> limit(int start, int rows) {
        String sql = "select *from userinfo limit ?,?";
        List<UserInfo> list = template.query(sql, new BeanPropertyRowMapper<>(UserInfo.class), start, rows);
        return list;
    }

    @Override
    public List<UserInfo> limit(int start, int rows, Search search) {
        //根据查询条件返回列表
        String sql = "select *from userinfo where name like ? and address like ? and email like ? limit ?,?";
        //处理判断条件为空的情况
        if (search.getName() == null) {
            search.setName("");
        }
        if (search.getAddress() == null || "null".equals(search.getAddress())) {
            search.setAddress("");
        }
        if (search.getEmail() == null || "null".equals(search.getEmail())) {
            search.setEmail("");
        }
        //System.out.println("-------------------------------");
        //System.out.println(search.getEmail());
        //System.out.println(search);
        List<UserInfo> list = template.query(
                sql,
                new BeanPropertyRowMapper<>(UserInfo.class),
                "%" + search.getName() + "%",
                "%" + search.getAddress() + "%",
                "%" + search.getEmail() + "%",
                start, rows
        );
        return list;
    }
}
