package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Bid;
import com.epam.mentoring.springboot.entity.Item;

import java.util.List;
import java.util.Optional;

public interface BidDAO {
    Optional<Bid> find(long id);
    List<Bid> findByItem(Item itemId);
    void save(Bid bid);
    Optional<Bid> findFirstBidByItemOrderByBidDesc(Item item);
    void delete(Bid bid);
}
