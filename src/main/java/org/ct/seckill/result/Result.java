package org.ct.seckill.result;

import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public Result(){}

    public Result(T data) {
        this.code = MsgCode.SUCCESS.getCode();
        this.msg = MsgCode.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(MsgCode msgCode) {
        this.code = msgCode.getCode();
        this.msg = msgCode.getMsg();
    }

    public <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public <T> Result<T> error(MsgCode msgCode) {
        return new Result<T>(msgCode);
    }

}
