package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HibernateItemDAO implements ItemDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> findAll() {
        return entityManager.createNativeQuery("select * from Items", Item.class).getResultList();
    }

    @Override
    public Optional<Item> find(long id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }

    @Override
    public List<Item> findBySellerLogin(String seller) {
        String query = "SELECT item FROM Item item, User user WHERE item.seller = user.id AND user.login = :seller";
        return entityManager.createQuery(query, Item.class).setParameter("seller", seller).getResultList();
    }

    @Override
    public Optional<Item> findById(long id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }

    @Override
    public void delete(Item item) {
        entityManager.remove(item);
    }

    @Override
    public void deleteById(long id) {
        Item item = entityManager.find(Item.class, id);
        entityManager.remove(item);
    }

    @Override
    public void save(Item item) {
        entityManager.persist(item);
    }
}
