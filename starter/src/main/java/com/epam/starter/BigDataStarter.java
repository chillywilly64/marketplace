package com.epam.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Configuration
@AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
@ConditionalOnBean(name = "dataSource")
public class BigDataStarter {
    private Logger log = LoggerFactory.getLogger(BigDataStarter.class);

    private static final BigInteger MAX_ROW_COUNT = BigInteger.valueOf(10);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ApplicationContext context;

    @Bean
    public void test(){
        BigInteger totalRowCount = BigInteger.ZERO;
        List<Map<String,Object>> tablesMap = jdbcTemplate.queryForList("show tables");
        for (Map<String,Object> map: tablesMap) {
            BigInteger rowCount = jdbcTemplate.queryForObject("select count(*) from " + map.get("TABLE_NAME"), BigInteger.class);
            totalRowCount = totalRowCount.add(rowCount);
        }
        if (MAX_ROW_COUNT.compareTo(totalRowCount) == -1) {
            log.info("It’s BigData project");
            SpringApplication.exit(context);
        }
    }
}
