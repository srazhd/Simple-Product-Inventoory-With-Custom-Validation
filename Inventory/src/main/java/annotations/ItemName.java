package annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Past.List;

@Documented
@Constraint(validatedBy = ItemNameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface ItemName {
	String message() default "Not a valid Item Name";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	int min() default 0;	
	int max() default Integer.MAX_VALUE;
}
