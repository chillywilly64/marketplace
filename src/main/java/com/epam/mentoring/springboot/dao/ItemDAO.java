package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemDAO extends CrudRepository<Item,Long> {
    List<Item> findBySellerLogin(String seller);
}
