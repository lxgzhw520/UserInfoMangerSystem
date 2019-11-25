package com.lxgzhw.test;

import com.lxgzhw.utils.DruidUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    public void demo01() throws SQLException {
        //连接数据库测试
        Connection connection = DruidUtils.getConnection();
        String sql = "select *from admin";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("password"));
            System.out.println("-------------------------------");
        }
    }
}
