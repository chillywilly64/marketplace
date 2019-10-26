package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.User;
import java.util.List;

public interface UserDAO {
    public List<User> getAll();
    public User getByID(long id);
    public User getByLogin(String login);
    public void insert(User user);
    public void update(User user);
    public void delete(long id);
}
