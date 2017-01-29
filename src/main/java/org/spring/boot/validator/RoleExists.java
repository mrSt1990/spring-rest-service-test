package org.spring.boot.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by Aleksey Stoyokha on 29.01.17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=RoleExistsConstraintValidator.class)
public @interface RoleExists {
    String message() default "One or more role doesn't exist";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
