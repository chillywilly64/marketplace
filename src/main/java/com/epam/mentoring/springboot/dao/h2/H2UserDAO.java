package com.epam.mentoring.springboot.dao.h2;

import com.epam.mentoring.springboot.dao.UserDAO;
import com.epam.mentoring.springboot.entity.User;
import com.epam.mentoring.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class H2UserDAO implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll(){
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, new UserMapper());
    }
    
    @Override
    public User getByID(long id){
        String sql = "SELECT * FROM Marketplace.Users WHERE User_ID = ?";
        return (User) jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
    }
    
    @Override
    public User getByLogin(String login){
        String sql = "SELECT * FROM Users WHERE Login = ?";
        try{
            return (User) jdbcTemplate.queryForObject(sql, new Object[]{login}, new UserMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    
    @Override
    public void insert(User user){
        String sql = "INSERT INTO Users(Full_Name, Billing_address, Login, Password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getBillingaddress(), user.getLogin(), user.getPassword());
    }
    
    @Override
    public void update(User user){
        String sql = "UPDATE Users SET Full_Name = ?, Billing_address = ?, Login = ?, Password = ? WHERE User_ID = ?";
        jdbcTemplate.update(sql, user.getName(), user.getBillingaddress(), user.getLogin(), user.getPassword(), user.getUserID());
    }
    
    @Override
    public void delete(long id){
        String sql = "DELETE FROM Users WHERE User_ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
