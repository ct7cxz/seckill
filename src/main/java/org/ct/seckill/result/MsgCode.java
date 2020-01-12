package org.ct.seckill.result;

/**
 * 所有返回给前端页面的信息封装
 */
public enum  MsgCode {

    SUCCESS(200,"success"),

    //服务器访问异常,用户登录模块500 000
    ERROR_USER(500000,"用户名或密码错误!"),
    ERROR_MOBILE(500100,"输入的手机号有误！"),
    ERROR_MOBILE_NULL(500101,"输入的手机号不存在!"),
    ERROR_PASSWORD(500200,"密码不能为空!"),
    ERROR_PASSWORD_FAIL(500201,"密码错误!"),
    //订单模块 500 100
    ERROR_ORDER(500100,"订单信息错误")
    ;


    private int code;

    private String msg;

    MsgCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
