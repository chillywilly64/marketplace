package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.User;
import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getByID(long id);
    User getByLogin(String login);
    void insert(User user);
    void update(User user);
    void delete(long id);
}
