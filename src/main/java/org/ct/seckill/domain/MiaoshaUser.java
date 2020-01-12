package org.ct.seckill.domain;

import lombok.Data;

import java.util.Date;

/**
 * 存储用户信息
 */
@Data
public class MiaoshaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
