package com.epam.mentoring.springboot.health;

import com.epam.mentoring.springboot.dao.BidDAO;
import com.epam.mentoring.springboot.dao.ItemDAO;
import com.epam.mentoring.springboot.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private BidDAO bidDAO;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        long userCount = userDAO.count();
        long itemCount = itemDAO.count();
        long bidCount = bidDAO.count();

        builder.up()
            .withDetail("Users", userCount)
            .withDetail("Items", itemCount)
            .withDetail("Bids", bidCount);
    }
}
