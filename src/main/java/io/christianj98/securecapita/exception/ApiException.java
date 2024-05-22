package io.christianj98.securecapita.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
