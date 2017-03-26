package com.company.Comparators;

/**
 * Created by gregtar on 25.03.17.
 */
import java.util.Comparator;
import com.company.Customer.Customer;



public class SortByName implements Comparator<Customer>{
    public int compare1(Customer u1, Customer u2) {
        return u1.getName().compareToIgnoreCase(u2.getName());
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
