package com.epam.mentoring.springboot.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Item implements Serializable{
    private long itemID;
    private User seller;
    private String title;
    private String description;
    private double startPrice;
    private double timeLeft;
    private Timestamp startBiddingDate;
    private boolean buyItNow;
    private boolean sold;
    private double bidIncrement;

    /**
     * @return the itemID
     */
    public long getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the seller
     */
    public User getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(User seller) {
        this.seller = seller;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the startPrice
     */
    public double getStartPrice() {
        return startPrice;
    }

    /**
     * @param startPrice the startPrice to set
     */
    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * @return the timeLeft
     */
    public double getTimeLeft() {
        return timeLeft;
    }

    /**
     * @param timeLeft the timeLeft to set
     */
    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * @return the startBiddingDate
     */
    public Timestamp getStartBiddingDate() {
        return startBiddingDate;
    }

    /**
     * @param startBiddingDate the startBiddingDate to set
     */
    public void setStartBiddingDate(Timestamp startBiddingDate) {
        this.startBiddingDate = startBiddingDate;
    }

    /**
     * @return the buyItNow
     */
    public boolean isBuyItNow() {
        return buyItNow;
    }

   /**
    * @param buyItNow the buyItNow to set
    */
   public void setBuyItNow(boolean buyItNow) {
       this.buyItNow = buyItNow;
   }

   /**
    * @return the sold
    */
   public boolean isSold() {
       return sold;
   }

   /**
     * @param sold the buyItNow to set
     */
   public void setSold(boolean sold) {
       this.sold = sold;
   }

    /**
     * @return the bidIncrement
     */
    public double getBidIncrement() {
        return bidIncrement;
    }

    /**
     * @param bidIncrement the bidIncrement to set
     */
    public void setBidIncrement(double bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemID != item.itemID) return false;
        if (Double.compare(item.startPrice, startPrice) != 0) return false;
        if (Double.compare(item.timeLeft, timeLeft) != 0) return false;
        if (buyItNow != item.buyItNow) return false;
        if (sold != item.sold) return false;
        if (Double.compare(item.bidIncrement, bidIncrement) != 0) return false;
        if (!seller.equals(item.seller)) return false;
        if (!title.equals(item.title)) return false;
        if (!description.equals(item.description)) return false;
        return startBiddingDate.equals(item.startBiddingDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (itemID ^ (itemID >>> 32));
        result = 31 * result + seller.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        temp = Double.doubleToLongBits(startPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(timeLeft);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + startBiddingDate.hashCode();
        result = 31 * result + (buyItNow ? 1 : 0);
        result = 31 * result + (sold ? 1 : 0);
        temp = Double.doubleToLongBits(bidIncrement);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Item{" + "itemID=" + itemID + ", seller=" + seller + ", title=" + title + 
                ", description=" + description + ", startPrice=" + startPrice + 
                ", timeLeft=" + timeLeft + ", startBiddingDate=" + startBiddingDate + 
                ", buyItNow=" + buyItNow + ", sold=" + sold + ", bidIncrement=" + bidIncrement + '}';
    }
    
    
}
