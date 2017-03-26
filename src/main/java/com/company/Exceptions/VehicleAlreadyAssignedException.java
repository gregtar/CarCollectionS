package com.company.Exceptions;

/**
 * Created by GregTar on 18.03.2017.
 */
public class VehicleAlreadyAssignedException extends Exception {
    public VehicleAlreadyAssignedException(String message) {
        super(message);
    }
}