package com.feedeo.geckoboard.exception;

public class UnableToPushToWidgetException extends Exception {

    public UnableToPushToWidgetException(String message) {
        super(message);
    }

    public UnableToPushToWidgetException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
