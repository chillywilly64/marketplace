package com.epam.mentoring.springboot.mapper;

import com.epam.mentoring.springboot.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setUserID(rs.getLong("User_ID"));
        user.setName(rs.getString("Full_Name"));
        user.setBillingaddress(rs.getString("Billing_address"));
        user.setLogin(rs.getString("Login"));
        user.setPassword(rs.getString("Password"));
        return user;
    }
}
