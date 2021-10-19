package ru.goridko_igor.exception;

public class HtmlPageNotFoundException extends Exception {
    public HtmlPageNotFoundException() {
    }

    public HtmlPageNotFoundException(String message) {
        super(message);
    }

    public HtmlPageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HtmlPageNotFoundException(Throwable cause) {
        super(cause);
    }

    public HtmlPageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
