package com.lyra.mail.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class ValueListConstrain implements ConstraintValidator<ValueList, Integer> {
    private final Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(ValueList constraintAnnotation) {
        for (int value : constraintAnnotation.values()) {
            set.add(value);
        }
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}
