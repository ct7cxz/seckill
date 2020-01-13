package org.ct.seckill.exception;

import org.ct.seckill.result.MsgCode;

public class GlobalException extends RuntimeException {

    private MsgCode mc;

    public GlobalException(MsgCode msgCode) {
        super(msgCode.toString());
        this.mc = msgCode;
    }

    public MsgCode getMc() {
        return mc;
    }
}
