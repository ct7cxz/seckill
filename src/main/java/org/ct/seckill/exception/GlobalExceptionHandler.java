package org.ct.seckill.exception;

import org.ct.seckill.result.MsgCode;
import org.ct.seckill.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler<T> {

    @ExceptionHandler(value = Exception.class)
    public Result<T> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            MsgCode mc = ex.getMc();
            return new Result<>().error(mc);
        }
        return new Result<>().error(MsgCode.ERROR_UNKOWN);
    }
}
