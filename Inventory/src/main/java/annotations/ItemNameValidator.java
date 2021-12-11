package annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import test.service.BrandService;

public class ItemNameValidator implements ConstraintValidator<ItemName, String>{
	

	
	private int min;
	private int max;
	
	@Override
	public void initialize(ItemName constraintAnnotation) {
		 this.min =constraintAnnotation.min();
		 this.max =constraintAnnotation.max();
		 
	}
	
	@Override
	public boolean isValid(String obj, ConstraintValidatorContext context) {
		if(obj == null) {
			 customMessageForValidation(context, "Item Name Is Mandatory");
			return false;
		}

		if(obj.length() > 255) {
			customMessageForValidation(context, "Maximum Length is 255");
			return false;
		}
		if(obj.matches("^[a-zA-Z0-9- _]*$")) {
			return true;
		}
		else {
		  return false;
		}
	}
	
	private void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
        
        constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
		

}
