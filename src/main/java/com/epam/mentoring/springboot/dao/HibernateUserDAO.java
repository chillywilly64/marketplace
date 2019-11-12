package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class HibernateUserDAO implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> find(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return entityManager
            .createNamedQuery("User.findByLogin", User.class)
            .setParameter("login", login)
            .getResultStream().findFirst();
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
