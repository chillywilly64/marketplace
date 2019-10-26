package com.epam.mentoring.springboot.dao;

import com.epam.mentoring.springboot.entity.Bid;
import java.util.List;

public interface BidDAO {
    List<Bid> getAll();
    Bid getByID(long id);
    List<Bid> getByItemID(long id);
    Bid getBestBidByItemID(long id);
    void insert(Bid bid);
    void update(Bid bid);
    void delete(long id);
}
