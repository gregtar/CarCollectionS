package com.company.Exceptions;

/**
 * Created by gregtar on 25.03.17.
 */
public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
