package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Item;
import java.util.List;

public interface ItemDAO {
    public List<Item> getAll();
    public Item getByID(long id);
    public List<Item> getBySellerID(long seller);
    public List<Item> getBySellerLogin(String seller);
    public void insert(Item item);
    public void update(Item item);
    public void sell(long id);
    public void delete(long id);
}
