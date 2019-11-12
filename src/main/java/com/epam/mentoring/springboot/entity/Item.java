package com.epam.mentoring.springboot.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn
    private User seller;
    @Column
    private String title;
    @Column
    private String description;
    @Column(name = "Start_Price")
    private double startPrice;
    @Column(name = "Time_Left")
    private double timeLeft;
    @Column(name = "Start_Bidding_Date")
    private Timestamp startBiddingDate;
    @Column(name = "Buy_It_Now")
    private boolean buyItNow;
    @Column
    private boolean sold;
    @Column(name = "Bid_Increment")
    private double bidIncrement;
}
