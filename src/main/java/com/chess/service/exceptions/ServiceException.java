package com.chess.service.exceptions;

public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }
}
