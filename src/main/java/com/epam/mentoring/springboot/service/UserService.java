package com.epam.mentoring.springboot.service;

import com.epam.mentoring.springboot.dao.UserDAO;
import com.epam.mentoring.springboot.dto.UserDTO;
import com.epam.mentoring.springboot.entity.User;
import com.epam.mentoring.springboot.hash.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserDAO uDao;
    
    public Optional<User> getByLogin(String login){
        return uDao.findByLogin(login);
    }
    
    public void reqistration(UserDTO userForm){
        User user = new User();
        user.setName(userForm.getName());
        user.setBillingaddress(userForm.getaddress());
        user.setLogin(userForm.getLogin());
        user.setPassword(HashPassword.hashPassword(userForm.getPassword()));
        uDao.save(user);
    }

    public boolean isAuthenticate(String login, String password){
        Optional<User> user = uDao.findByLogin(login);
        return user.filter(value -> HashPassword.checkPassword(password, value.getPassword())).isPresent();
    }
    
}
