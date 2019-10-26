package com.epam.mentoring.springboot.mapper;

import com.epam.mentoring.springboot.entity.Bid;
import com.epam.mentoring.springboot.entity.Item;
import com.epam.mentoring.springboot.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BidMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Bid bid = new Bid();
        Item item = new Item();
        User bidder = new User();
        User seller = new User();
        
        bidder.setUserID(rs.getLong("User_ID"));
        bidder.setName(rs.getString("Full_Name"));
        bidder.setBillingaddress(rs.getString("Billing_address"));
        bidder.setLogin(rs.getString("Login"));
        
        seller.setUserID(rs.getLong("Seller_ID"));
        seller.setName(rs.getString("Seller_Name"));
        seller.setBillingaddress(rs.getString("Seller_address"));
        seller.setLogin(rs.getString("Seller_Login"));
        
        item.setItemID(rs.getLong("Item_ID"));
        item.setSeller(seller);
        item.setTitle(rs.getString("Title"));
        item.setDescription(rs.getString("Description"));
        item.setStartPrice(rs.getDouble("Start_Price"));
        item.setTimeLeft(rs.getDouble("Time_Left"));
        item.setStartBiddingDate(rs.getTimestamp("Start_Bidding_Date"));
        item.setBuyItNow(rs.getBoolean("Buy_It_Now"));
        item.setSold(rs.getBoolean("Sold"));
        item.setBidIncrement(rs.getDouble("Bid_Increment"));
        
        bid.setBidID(rs.getLong("Bid_ID"));
        bid.setBidder(bidder);
        bid.setItem(item);
        bid.setBid(rs.getDouble("Bid"));
        
        return bid;
    }
}
