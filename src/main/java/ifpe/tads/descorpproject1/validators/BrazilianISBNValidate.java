package ifpe.tads.descorpproject1.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BrazilianISBNValidator.class)
@Documented
public @interface BrazilianISBNValidate {
    String message() default "ISBN invalido. O ISBN deve ser brasileiro, com o codigo 65 ou 85.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

