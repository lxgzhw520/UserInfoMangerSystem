package com.lxgzhw.dao.impl;

import com.lxgzhw.dao.AdminDao;
import com.lxgzhw.domain.Admin;
import com.lxgzhw.utils.DruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate template =
            new JdbcTemplate(DruidUtils.getDataSource());

    @Override
    public Admin login(Admin loginUser) {
        //登录数据库
        String sql = "select *from admin where username=? and password=?";
        Admin admin = template.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(Admin.class),
                loginUser.getUsername(),
                loginUser.getPassword()
        );
        return admin;
    }
}
