package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
