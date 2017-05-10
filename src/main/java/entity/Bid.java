package entity;

import java.io.Serializable;
import java.util.Objects;

public class Bid implements Serializable{
    private long bidID;
    private User bidder;
    private Item item;
    private double bid;

    /**
     * @return the bidID
     */
    public long getBidID() {
        return bidID;
    }

    /**
     * @param bidID the bidID to set
     */
    public void setBidID(long bidID) {
        this.bidID = bidID;
    }

    /**
     * @return the bidder
     */
    public User getBidder() {
        return bidder;
    }

    /**
     * @param bidder the bidder to set
     */
    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the bid
     */
    public double getBid() {
        return bid;
    }

    /**
     * @param bid the bid to set
     */
    public void setBid(double bid) {
        this.bid = bid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.bidID ^ (this.bidID >>> 32));
        hash = 79 * hash + Objects.hashCode(this.bidder);
        hash = 79 * hash + Objects.hashCode(this.item);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.bid) ^ (Double.doubleToLongBits(this.bid) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bid other = (Bid) obj;
        if (this.bidID != other.bidID) {
            return false;
        }
        if (Double.doubleToLongBits(this.bid) != Double.doubleToLongBits(other.bid)) {
            return false;
        }
        if (!Objects.equals(this.bidder, other.bidder)) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return true;
    }
    
    
}
