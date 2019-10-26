package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Item;
import java.util.List;

public interface ItemDAO {
    List<Item> getAll();
    Item getByID(long id);
    List<Item> getBySellerID(long seller);
    List<Item> getBySellerLogin(String seller);
    void insert(Item item);
    void update(Item item);
    void sell(long id);
    void delete(long id);
}
