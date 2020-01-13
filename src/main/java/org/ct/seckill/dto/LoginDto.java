package org.ct.seckill.dto;

import lombok.Data;
import org.ct.seckill.validate.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 登录信息传递的信息流
 */
@Data
public class LoginDto {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 6)
    private String password;

    @Override
    public String toString() {
        return "LoginDto{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
