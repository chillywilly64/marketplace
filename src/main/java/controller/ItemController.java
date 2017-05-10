package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.BidDTO;
import dto.ItemDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.ItemService;
import validator.ItemValidator;

@Controller
@SessionAttributes("user")
public class ItemController {
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private ItemValidator itemValidator;

    @RequestMapping(value = "/")
    public String showItems(){
        return "items";
    }
    
    @RequestMapping(value = "/all-items", method = RequestMethod.GET)
    public @ResponseBody List<ItemDTO> getItems(){
        return itemService.getItems();
    }
    
    @RequestMapping(value = "/my-items", method = RequestMethod.GET)
    public @ResponseBody List<ItemDTO> getUsersItems(ModelMap model){
        return itemService.getUsersItems((String) model.get("user"));
    }
    
    @RequestMapping(value = "/add-item", method = RequestMethod.GET)
    public String addItem(Model model){
        model.addAttribute("itemForm", new ItemDTO());
        return "edit-item";
    }
    
    @RequestMapping(value = "/edit-item", method = RequestMethod.GET)
    public String editItem(@RequestParam long id, Model model){
        model.addAttribute("itemForm", itemService.getItem(id));
        return "edit-item";
    }
    
    @RequestMapping(value = {"/edit-item", "/add-item"}, method = RequestMethod.POST)
    public String addOrEdit(@ModelAttribute("itemForm") ItemDTO itemForm, @ModelAttribute("user") String login, 
                                BindingResult bindingResult, Model model) {
        itemValidator.validate(itemForm, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "edit-item";
        }
        
        if(itemForm.getId() == 0){
            itemForm.setLogin(login);
            itemService.addItem(itemForm);
        } else {
            itemService.editItem(itemForm);
        }
        
        return "items";
    }
    
    @RequestMapping(value = "/delete-item", method = RequestMethod.POST)
    public void deleteItem(@RequestParam("id") long id, @ModelAttribute("user") String login){
        if(itemService.getItem(id).getLogin().equals(login)){
            itemService.deleteItem(id);
        }
    }
    
    @RequestMapping(value = "/buy-item", method = RequestMethod.GET)
    public @ResponseBody ItemDTO buyItem(@RequestParam("id") long id, @ModelAttribute("user") String login){       
        return itemService.sellItem(id);        
    }
    
    @RequestMapping(value = "/bid", method = RequestMethod.GET)
    public @ResponseBody ItemDTO bidItem(@RequestParam("id") long id, @RequestParam("bid") double bid, @ModelAttribute("user") String login){
        return itemService.bidItem(bid, id, login); 
    }
    
    @RequestMapping(value = "/bids", method = RequestMethod.GET)
    public @ResponseBody List<BidDTO> getBids(@RequestParam("id") long id){
        return itemService.getBidsByItem(id);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBidException(Exception e, HttpServletResponse response) throws IOException{
        response.setStatus(500);
        response.getWriter().print(e.getMessage());
    }
}
