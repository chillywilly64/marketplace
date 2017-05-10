package service;

import dao.UserDAO;
import dto.UserDTO;
import entity.User;
import hash.HashPassword;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserDAO uDao;
    
    public User getByLogin(String login){
        return uDao.getByLogin(login);
    }
    
    public void reqistration(UserDTO userForm){
        User user = new User();
        user.setName(userForm.getName());
        user.setBillingaddress(userForm.getaddress());
        user.setLogin(userForm.getLogin());
        user.setPassword(HashPassword.hashPassword(userForm.getPassword()));
        uDao.insert(user);
    }

    public boolean isAuthenticate(String login, String password){
        User user = uDao.getByLogin(login);
        if (user == null){
            return false;
        } else { 
            return HashPassword.checkPassword(password, user.getPassword());
        }
    }
    
}
