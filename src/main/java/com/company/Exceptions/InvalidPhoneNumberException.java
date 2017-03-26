package com.company.Exceptions;

/**
 * Created by gregtar on 25.03.17.
 */
public class InvalidPhoneNumberException extends Exception {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
