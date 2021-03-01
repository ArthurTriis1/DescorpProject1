package ifpe.tads.descorpproject1.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class BrazilianISBNValidator implements ConstraintValidator<BrazilianISBNValidate, String> {
  
    @Override
    public void initialize(BrazilianISBNValidate brazilianISBNValidate) { }

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        String localcode = isbn.split("-")[1];
        return (localcode.equals("85") || localcode.equals("65"));
    }
    
}

