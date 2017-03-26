package com.company.Comparators;

import com.company.Customer.Customer;
import java.util.Comparator;

/**
 * Created by gregtar on 26.03.17.
 */
public class SortByPhone implements Comparator<Customer> {

    public int compare1(Customer u1, Customer u2) {
        return u1.getPhone().compareToIgnoreCase(u2.getPhone());
    }

    @Override
    public int compare(Customer o1, Customer o2) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }


}
