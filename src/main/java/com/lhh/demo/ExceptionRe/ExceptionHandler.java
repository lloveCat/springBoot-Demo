package com.lhh.demo.ExceptionRe;

import com.lhh.demo.util.ResultInfo;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionHandler {

    public static final org.slf4j.Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo Handler(Exception e) {
        if (e instanceof BasicException) {
            if (e instanceof TimeOutException) {
                return ResultInfo.sessionOut();
            } else if (e instanceof NullCodeException) {
                return ResultInfo.error(e.getMessage());
            } else {
                return null;
            }
        } else {
            log.error("exception : " + e.getMessage(), e);
            return ResultInfo.error("服务器异常");
        }
    }
}
