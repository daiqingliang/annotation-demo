package com.daiql.annotationdemo.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint( validatedBy = NotZeroValid.NotZeroValidator.class)
public @interface NotZeroValid {

    String message() default "{javax.validation.constraints.Past.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class NotZeroValidator implements ConstraintValidator<NotZeroValid,Object> {

        @Override
        public void initialize(NotZeroValid constraintAnnotation) {

        }

        @Override
        public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
            try {
              if ((long) o == 0) {
                  return false;
              } else {
                  return true;
              }
            } catch (Exception e) {
                return false;
            }
        }
    }
}
