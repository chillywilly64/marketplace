package dao.oracle;

import dao.ItemDAO;
import entity.Item;
import java.util.List;
import mapper.ItemMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class OracleItemDAO extends JdbcDaoSupport implements ItemDAO {

    @Override
    public List<Item> getAll() {
        String sql = "SELECT * FROM Items, Users WHERE User_ID = Seller_ID";
        return getJdbcTemplate().query(sql, new ItemMapper());
    }

    @Override
    public Item getByID(long id) {       
        String sql = "SELECT * FROM Items, Users WHERE User_ID = Seller_ID AND Item_ID = ?";
        try{
            return (Item) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new ItemMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Item> getBySellerID(long seller) {
        String sql = "SELECT * FROM Items, Users WHERE User_ID = Seller_ID AND Seller_ID = ?";
        try{
            return getJdbcTemplate().query(sql, new Object[]{seller}, new ItemMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Item> getBySellerLogin(String seller) {
        String sql = "SELECT * FROM Items, Users WHERE User_ID = Seller_ID AND Login = ?";
        try {
            return getJdbcTemplate().query(sql, new Object[]{seller}, new ItemMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void insert(Item item) {
        String sql = "INSERT INTO Items(Seller_ID, Title, Description, Start_Price, Time_Left, Start_Bidding_Date, Buy_It_Now, Sold, Bid_Increment) "
                                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, item.getSeller().getUserID(), item.getTitle(), item.getDescription(), item.getStartPrice(), item.getTimeLeft(),
                                      item.getStartBiddingDate(), item.isBuyItNow(), item.isSold(), item.getBidIncrement());
    }

    @Override
    public void update(Item item) {
        String sql = "UPDATE Items "
                    + "SET Seller_ID = ?, Title = ?, Description = ?, Start_Price = ?, Time_Left = ?, Start_Bidding_Date = ?, Buy_It_Now = ?, Sold = ?, Bid_Increment = ? "
                    + "WHERE Item_ID = ?";
        getJdbcTemplate().update(sql, item.getSeller().getUserID(), item.getTitle(), item.getDescription(), item.getStartPrice(), item.getTimeLeft(),
                                      item.getStartBiddingDate(), item.isBuyItNow(), item.isSold(), item.getBidIncrement(), item.getItemID());
    }

    @Override
    public void sell(long id) {
        String sql = "UPDATE Items SET Sold = 1 WHERE Item_ID = ?";
        getJdbcTemplate().update(sql, id);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Items WHERE Item_ID = ?";
        getJdbcTemplate().update(sql, id);
    }
    
}
