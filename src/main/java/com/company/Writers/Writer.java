package com.company.Writers;

import com.company.Customer.Customer;

import java.util.List;

/**
 * Created by gregtar on 27.03.17.
 */
public interface Writer {
    public static void writeToFile(List<Customer> customer, String filename){};
}