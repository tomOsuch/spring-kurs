package pl.springkurs.shop.commons.money;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MoneyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Money {

    String message() default "Invalid money";
    Class<?>[] group() default {};
    Class<? extends Payload>[] payload() default {};
    double minValue() default 1;
}
