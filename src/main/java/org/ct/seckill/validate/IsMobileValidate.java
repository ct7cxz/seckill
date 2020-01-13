package org.ct.seckill.validate;

import org.ct.seckill.util.ValidationUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidate implements ConstraintValidator<IsMobile, String> {

    private boolean required = true;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        this.required = constraintAnnotation.requried();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required) {
            if (StringUtils.isEmpty(value)) {
                return true;
            }
        }
        if (ValidationUtil.isMobile(value)) {
            return true;
        } else {
            return false;
        }

    }
}
