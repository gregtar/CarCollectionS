package com.company.Exceptions;

/**
 * Created by gregtar on 25.03.17.
 */
public class CustomerAlreadyExistsException extends Exception {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
