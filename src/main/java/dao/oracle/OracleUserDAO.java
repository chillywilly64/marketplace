package dao.oracle;

import dao.UserDAO;
import entity.User;
import mapper.UserMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;

public class OracleUserDAO extends JdbcDaoSupport implements UserDAO{
    
    @Override
    public List<User> getAll(){
        String sql = "SELECT * FROM Users";
        return getJdbcTemplate().query(sql, new UserMapper());
    }
    
    @Override
    public User getByID(long id){
        String sql = "SELECT * FROM Marketplace.Users WHERE User_ID = ?";
        return (User) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new UserMapper());
    }
    
    @Override
    public User getByLogin(String login){
        String sql = "SELECT * FROM Users WHERE Login = ?";
        try{
            return (User) getJdbcTemplate().queryForObject(sql, new Object[]{login}, new UserMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    
    @Override
    public void insert(User user){
        String sql = "INSERT INTO Users(Full_Name, Billing_address, Login, Password) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, user.getName(), user.getBillingaddress(), user.getLogin(), user.getPassword());
    }
    
    @Override
    public void update(User user){
        String sql = "UPDATE Users SET Full_Name = ?, Billing_address = ?, Login = ?, Password = ? WHERE User_ID = ?";
        getJdbcTemplate().update(sql, user.getName(), user.getBillingaddress(), user.getLogin(), user.getPassword(), user.getUserID());
    }
    
    @Override
    public void delete(long id){
        String sql = "DELETE FROM Users WHERE User_ID = ?";
        getJdbcTemplate().update(sql, id);
    }
}
