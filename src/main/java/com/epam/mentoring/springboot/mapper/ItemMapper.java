package com.epam.mentoring.springboot.mapper;

import com.epam.mentoring.springboot.entity.Item;
import com.epam.mentoring.springboot.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ItemMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Item item = new Item();
        User seller = new User();
        
        seller.setUserID(rs.getLong("User_ID"));
        seller.setBillingaddress(rs.getString("Billing_address"));
        seller.setName(rs.getString("Full_Name"));
        seller.setLogin(rs.getString("Login"));
        seller.setPassword(rs.getString("Password"));
        
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
        return item;
    }
    
}
