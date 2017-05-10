package dao;

import entity.Bid;
import java.util.List;

public interface BidDAO {
    public List<Bid> getAll();
    public Bid getByID(long id);
    public List<Bid> getByItemID(long id);
    public Bid getBestBidByItemID(long id);
    public void insert(Bid bid);
    public void update(Bid bid);
    public void delete(long id);
}
