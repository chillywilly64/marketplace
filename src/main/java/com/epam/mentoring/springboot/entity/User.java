package com.epam.mentoring.springboot.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
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
