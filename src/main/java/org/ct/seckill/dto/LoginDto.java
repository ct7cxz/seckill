package org.ct.seckill.dto;

import lombok.Data;

/**
 * 登录信息传递的信息流
 */
@Data
public class LoginDto {
    private String mobile;
    private String password;

    @Override
    public String toString() {
        return "LoginDto{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
