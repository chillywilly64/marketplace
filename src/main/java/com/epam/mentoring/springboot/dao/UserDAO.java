package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> find(long id);
    Optional<User> findByLogin(String login);
    User save(User user);
    void delete(User user);
}
