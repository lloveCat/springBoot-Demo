package com.lhh.demo.ExceptionRe;

public abstract class BasicException extends RuntimeException{
    private static final long serialVersionUID = 8037891447646609768L;

    public BasicException(){}

    public BasicException(String errorMsg) {
        super(errorMsg);
    }

    public BasicException(Throwable cause) {
        super(cause);
    }

    public BasicException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
