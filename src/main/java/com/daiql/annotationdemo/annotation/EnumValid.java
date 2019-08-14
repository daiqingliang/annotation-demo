package com.daiql.annotationdemo.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Author daiql
 * Date 2019/8/14 14:24
 * Description 验证枚举的注解
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValid.Validator.class)
public @interface EnumValid {

    String message() default "{custom.value.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

    String enumMethod();

    class Validator implements ConstraintValidator<EnumValid, Object> {

        private Class<? extends Enum<?>> enumClass;
        private String enumMethod;

        @Override
        public void initialize(EnumValid constraintAnnotation) {
            enumMethod = constraintAnnotation.enumMethod();
            enumClass = constraintAnnotation.enumClass();
        }

        @Override
        public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {

            if (object == null) {
                return Boolean.TRUE;
            }

            if (enumClass == null || enumMethod == null) {
                return Boolean.TRUE;
            }

            Class<?> valueClass = object.getClass();

            try {
                Method method = enumClass.getMethod(enumMethod, valueClass);
                if (!Boolean.TYPE.equals(method.getReturnType()) && !Boolean.class.equals(method.getReturnType())) {
                    throw new RuntimeException(String.format("%s method return is not boolean type in the %s class", enumMethod, enumClass));
                }

                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new RuntimeException(String.format("%s method is not static method in the %s class", enumMethod, enumClass));
                }

                Boolean result = (Boolean) method.invoke(null, object);
                return result == null ? false : result;

            } catch (NoSuchMethodException e) {
                throw new RuntimeException(String.format("This %s(%s) method does not exist in the %s", enumMethod, valueClass, enumClass), e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
