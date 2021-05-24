package ru.itis.javalab.exception;

public class UserIsBannedException extends Exception {
    public UserIsBannedException(String message) {
        super(message);
    }
}
