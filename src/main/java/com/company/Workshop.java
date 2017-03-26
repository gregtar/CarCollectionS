package com.company;

import java.util.HashMap;

import com.company.Customer.Customer;
import com.company.Exceptions.*;


public class Workshop {
    private HashMap< String, Customer> customers;

    public Workshop() {
        customers = new HashMap<>();
    }

    public void addCustomer(Customer newCustomer) throws IllegalCustomerException, CustomerAlreadyExistsException {
        if (newCustomer == null)
            throw new IllegalCustomerException("Invalid customer provided");

        if (customers.containsKey(newCustomer.getPhone()))
            throw new CustomerAlreadyExistsException("Customer with phone number " + newCustomer.getPhone() +
                    " already is registered");

        customers.put(newCustomer.getPhone(), newCustomer);
    }

    public void removeCustomerByPhoneNumber(String customerPhoneNumber) throws CustomerNotFoundException,
            InvalidPhoneNumberException {
        if ((customerPhoneNumber == null) || customerPhoneNumber.isEmpty())
            throw new InvalidPhoneNumberException("Invalid phone number provided");

        if (!customers.containsKey(customerPhoneNumber))
            throw new CustomerNotFoundException("Customer with phone number " + customerPhoneNumber + " not found");

        customers.remove(customerPhoneNumber);
    }

    public Customer findCustomerByPhoneNumber(String customerPhoneNumber) throws InvalidPhoneNumberException,
            CustomerNotFoundException {
        if ((customerPhoneNumber == null) || customerPhoneNumber.isEmpty())
            throw new InvalidPhoneNumberException("Invalid phone number provided");

        if (!customers.containsKey(customerPhoneNumber))
            throw new CustomerNotFoundException("Customer with phone number " + customerPhoneNumber + " not found");

        return customers.get(customerPhoneNumber);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("=== Workshop ===\n");
        for (java.util.Map.Entry<String, Customer> customerEntry : customers.entrySet()) {
            builder.append("\n").append(customerEntry.getValue().toString());
        }
        builder.append("=== End ===\n");

        return builder.toString();
    }
}