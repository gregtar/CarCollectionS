package com.company.Comparators;

import java.util.Comparator;
import com.company.Customer.Customer;

/**
 * Created by gregtar on 25.03.17.
 */
public class ReSortByName implements Comparator<Customer> {
    public int compare (Customer u1, Customer u2) {
        return -1*(u1.getName().compareToIgnoreCase(u2.getName()));
    }
}
