package com.epam.mentoring.springboot.dao.oracle;

import com.epam.mentoring.springboot.dao.BidDAO;
import com.epam.mentoring.springboot.entity.Bid;
import java.util.List;
import com.epam.mentoring.springboot.mapper.BidMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class OracleBidDAO extends JdbcDaoSupport implements BidDAO {

    private final String SELECT_SQL = "SELECT Bid_ID, Bid, " +
            "Bidders.User_ID, Bidders.Full_Name, Bidders.Billing_address, Bidders.Login, " +
            "Items.Item_ID, Title, Description, Start_Price, Time_Left, Start_Bidding_Date, Buy_It_Now, Sold, Bid_Increment, " +
            "Sellers.User_ID AS Seller_ID, Sellers.Full_Name AS Seller_Name, " +
            "Sellers.Billing_address AS Seller_address, Sellers.Login AS Seller_Login " +
        "FROM Bids " +
        "JOIN Users Bidders ON Bidders.User_ID = Bids.Bidder_ID " +
        "JOIN Items ON Items.Item_ID = Bids.Item_ID " +
        "JOIN Users Sellers ON Sellers.User_ID = Items.Seller_ID ";
    
    @Override
    public List<Bid> getAll() {
        return getJdbcTemplate().query(SELECT_SQL, new BidMapper());
    }

    @Override
    public Bid getByID(long id) {
        String sql = SELECT_SQL +
                        "WHERE Bid_ID = ?";
        return (Bid) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BidMapper());
    }

    @Override
    public List<Bid> getByItemID(long id) {
        String sql = SELECT_SQL +
                        "WHERE Bids.Item_ID = ? " +
                        "ORDER BY Bid DESC";
        try {
            return getJdbcTemplate().query(sql, new Object[]{id}, new BidMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Bid getBestBidByItemID(long id) {
        String sql = "SELECT * FROM(" + SELECT_SQL +
                        "WHERE Bids.Item_ID = ? " +
                        "ORDER BY Bid DESC) " +
                        "WHERE ROWNUM = 1";
        try {
            return (Bid) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BidMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void insert(Bid bid) {
        String sql = "INSERT INTO Bids(Bidder_ID, Item_ID, Bid) "
                                        + "VALUES (?, ?, ?)";
        getJdbcTemplate().update(sql, bid.getBidder().getUserID(), bid.getItem().getItemID(), bid.getBid());
    }

    @Override
    public void update(Bid bid) {
        String sql = "UPDATE Bids "
                    + "SET Bidder_ID = ?, Item_ID = ?, Bid = ? "
                    + "WHERE Bid_ID = ?";
        getJdbcTemplate().update(sql, bid.getBidder().getUserID(), bid.getItem().getItemID(), bid.getBid());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Bids "
                    + "WHERE Bid_ID = ?";
        getJdbcTemplate().update(sql, id);
    }
    
}
