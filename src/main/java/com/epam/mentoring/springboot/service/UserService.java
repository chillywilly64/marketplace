package com.epam.mentoring.springboot.service;

import com.epam.mentoring.springboot.dao.UserDAO;
import com.epam.mentoring.springboot.dto.UserDTO;
import com.epam.mentoring.springboot.entity.User;
import com.epam.mentoring.springboot.hash.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserDAO uDao;
    
    public User getByLogin(String login){
        return uDao.getByLogin(login);
    }
    
    public void reqistration(UserDTO userForm){
        User user = new User();
        user.setName(userForm.getName());
        user.setBillingaddress(userForm.getaddress());
        user.setLogin(userForm.getLogin());
        user.setPassword(HashPassword.hashPassword(userForm.getPassword()));
        uDao.insert(user);
    }

    public boolean isAuthenticate(String login, String password){
        User user = uDao.getByLogin(login);
        if (user == null){
            return false;
        } else { 
            return HashPassword.checkPassword(password, user.getPassword());
        }
    }
    
}
