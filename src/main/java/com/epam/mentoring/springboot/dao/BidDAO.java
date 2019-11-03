package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Bid;
import com.epam.mentoring.springboot.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidDAO extends CrudRepository<Bid,Long> {
    List<Bid> findByItem(Item itemId);
    Bid findFirstBidByItemOrderByBidDesc(Item item);
}
