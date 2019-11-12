package com.epam.mentoring.springboot.entity;

import lombok.Data;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Users")
@NamedQuery(name = "User.findByLogin", query = "from User where login = :login")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Full_Name")
    private String name;
    @Column(name = "Billing_Address")
    private String billingaddress;
    @Column
    private String login;
    @Column
    private String password;
}
