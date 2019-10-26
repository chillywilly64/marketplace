package com.epam.mentoring.springboot.validator;

import com.epam.mentoring.springboot.dto.ItemDTO;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return ItemDTO.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemDTO item = (ItemDTO) o;
        //TODO: Fix validation title error
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        
        if(item.getStartPrice() <= 0){
           errors.rejectValue("startPrice", "PositiveValue");
        }
        
        if(item.isBuyItNow()){
            if(item.getTimeLeft() <= 0){
                errors.rejectValue("timeLeft", "PositiveValue");
            }
            
            if(item.getTimeLeft() <= 0){
                errors.rejectValue("bidInc", "PositiveValue");
            }
        }
    }
}
