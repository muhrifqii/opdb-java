package com.muhrifqii.scrapper.errors;

public class PageSourceInvalidException extends RuntimeException {
    public PageSourceInvalidException() {
        super();
    }

    public PageSourceInvalidException(String message) {
        super(message);
    }
}
