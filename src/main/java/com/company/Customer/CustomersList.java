package com.company.Customer;

/**
 * Created by gregtar on 30.03.17.
 */
import com.company.Exceptions.CustomerAlreadyExistsException;
import com.company.Exceptions.IllegalCustomerException;

import java.util.ArrayList;
import java.util.List;

public class CustomersList {

    private List<Customer> customers;

    public CustomersList(){
        customers = new ArrayList<>();
    }

    public void addCustomerToList(Customer customer){
        customers.add(customer);
    }
    }

