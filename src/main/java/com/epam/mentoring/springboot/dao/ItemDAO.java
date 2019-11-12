package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDAO {
    List<Item> findAll();
    Optional<Item> find(long id);
    List<Item> findBySellerLogin(String seller);
    Optional<Item> findById(long id);
    void delete(Item item);
    void deleteById(long id);
    void save(Item item);
}
