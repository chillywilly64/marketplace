package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Bid;
import com.epam.mentoring.springboot.entity.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HibernateBidDAO implements BidDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Bid> find(long id) {
        return Optional.ofNullable(entityManager.find(Bid.class, id));
    }

    @Override
    public List<Bid> findByItem(Item item) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bid> criteriaQuery = criteriaBuilder.createQuery(Bid.class);
        Root<Bid> root = criteriaQuery.from(Bid.class);
        Join<Bid,Item> joinItem = root.join("item");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(joinItem.get("id"), item.getId()));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void save(Bid bid) {
        entityManager.persist(bid);
    }

    @Override
    public Optional<Bid> findFirstBidByItemOrderByBidDesc(Item item) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bid> criteriaQuery = criteriaBuilder.createQuery(Bid.class);
        Root<Bid> root = criteriaQuery.from(Bid.class);
        Join<Bid,Item> joinItem = root.join("item");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(joinItem.get("id"), item.getId()));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("bid")));

        return entityManager.createQuery(criteriaQuery).getResultStream().findFirst();
    }

    @Override
    public void delete(Bid bid) {
        entityManager.remove(bid);
    }
}
