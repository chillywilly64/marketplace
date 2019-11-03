package com.epam.mentoring.springboot.service;

import com.epam.mentoring.springboot.dao.BidDAO;
import com.epam.mentoring.springboot.dao.ItemDAO;
import com.epam.mentoring.springboot.dao.UserDAO;
import com.epam.mentoring.springboot.dto.BidDTO;
import com.epam.mentoring.springboot.dto.ItemDTO;
import com.epam.mentoring.springboot.entity.Bid;
import com.epam.mentoring.springboot.entity.Item;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    @Autowired
    private ItemDAO iDao;
    
    @Autowired
    private UserDAO uDao;
    
    @Autowired
    private BidDAO bDao;
    
    private final static long HOURS_TO_MILLIS = 3600000;
    
    public ItemDTO getItem(long id){
        Item item = iDao.findById(id).get();
        return itemToDTO(item);
    }
    
    public List<ItemDTO> getItems(){
        Iterable<Item> items = iDao.findAll();
        List<ItemDTO> dtos = new ArrayList<>();
        for(Item item: items){
            dtos.add(itemToDTO(item));
        }
        return dtos;
    }
    
    public List<ItemDTO> getUsersItems(String user){
        List<Item> items = iDao.findBySellerLogin(user);
        List<ItemDTO> dtos = new ArrayList<>();
        for(Item item: items){
            dtos.add(itemToDTO(item));
        }
        return dtos;
    }
    
    public void addItem(ItemDTO dto){
        Item item = new Item();
        item.setSeller(uDao.findByLogin(dto.getLogin()));
        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setStartPrice(dto.getStartPrice());
        item.setStartBiddingDate(new Timestamp(System.currentTimeMillis()));
        item.setSold(false);
        if(!dto.isBuyItNow()){
            item.setTimeLeft(dto.getTimeLeft());
            item.setBidIncrement(dto.getBidInc());
        }
        item.setBuyItNow(dto.isBuyItNow());
        iDao.save(item);
    }
    
    public void editItem(ItemDTO dto){
        Item item = iDao.findById(dto.getId()).get();
        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        if (bDao.findFirstBidByItemOrderByBidDesc(item) == null) {
            item.setStartPrice(dto.getStartPrice());
            if (!dto.isBuyItNow()) {
                item.setTimeLeft(dto.getTimeLeft());
                item.setBidIncrement(dto.getBidInc());
            }
            item.setBuyItNow(dto.isBuyItNow()); 
        }
        iDao.save(item);
    }
    
    public void deleteItem(long id){
        iDao.deleteById(id);
    }
    
    public ItemDTO sellItem(long id){
        Optional<Item> itemOpt = iDao.findById(id);
        Item item = itemOpt.get();
        item.setSold(true);
        item = iDao.save(item);
        return itemToDTO(item);
    }
    
    public ItemDTO bidItem(double bidValue, long itemID, String bidder) throws IllegalArgumentException{
        Item item = iDao.findById(itemID).get();
        Bid bid = bDao.findFirstBidByItemOrderByBidDesc(item);
        if((bidValue - item.getStartPrice())% item.getBidIncrement() != 0){
            throw new IllegalArgumentException("Bid must be multiple of bid increment");
        } else if (bid != null && bid.getBid() >= bidValue){
            throw new IllegalArgumentException("Bid must be bigger than best offer now");
        } else {
            bid = new Bid();
            bid.setBid(bidValue);
            bid.setBidder(uDao.findByLogin(bidder));
            bid.setItem(iDao.findById(itemID).get());
            bDao.save(bid);
            return itemToDTO(iDao.findById(itemID).get());
        }
    }
    
    public List<BidDTO> getBidsByItem(long id){
        Item item = iDao.findById(id).get();
        List<Bid> bids = bDao.findByItem(item);
        List<BidDTO> dtos = new ArrayList<>();
        for(Bid bid: bids){
            dtos.add(bidToDTO(bid));
        }
        return dtos;
    }
    
    
    private BidDTO bidToDTO(Bid bid){
        BidDTO dto = new BidDTO();
        dto.setItem(bid.getItem().getTitle());
        dto.setBidder(bid.getBidder().getName());
        dto.setBid(bid.getBid());
        return dto;
    }
    
    private ItemDTO itemToDTO(Item item){
        Bid bid = bDao.findFirstBidByItemOrderByBidDesc(item);
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setDescription(item.getDescription());
        dto.setSeller(item.getSeller().getName());
        dto.setLogin(item.getSeller().getLogin());
        dto.setStartPrice(item.getStartPrice());
        dto.setBidInc(item.getBidIncrement());
        if(bid != null){
            dto.setBestOffer(bid.getBid());
            dto.setBidder(bid.getBidder().getName());
        }
        long temp = item.getStartBiddingDate().getTime();
        temp += item.getTimeLeft()*HOURS_TO_MILLIS;

        if(!item.isBuyItNow() && temp <= System.currentTimeMillis()){
            item.setSold(true);
            iDao.save(item);
        }
        dto.setSold(item.isSold());
        dto.setTimeLeft(item.getTimeLeft());
        dto.setStopDate(new SimpleDateFormat("yyyy-MM-dd hh-mm").format(temp));
        dto.setBuyItNow(item.isBuyItNow());
        return dto;
    }
}
