package annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String>{
	private int min;
	private int max;
	
	@Override
	public void initialize(Phone constraintAnnotation) {
		 this.min =constraintAnnotation.min();
		 this.max =constraintAnnotation.max();
		 
	}
	
	@Override
	public boolean isValid(String phoneNo, ConstraintValidatorContext context) {
		if(phoneNo == null) {
			 customMessageForValidation(context, "date is not valid");
			return false;
		}
		if(phoneNo.matches("\\d{10}")) {
			return true;
		}
		else {
		  return false;
		}
	}
	
	private void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
        // build new violation message and add it
        constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
		

}
