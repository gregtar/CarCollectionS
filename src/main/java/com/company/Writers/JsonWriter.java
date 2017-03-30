package com.company.Writers;

/**
 * Created by gregtar on 25.03.17.
 */

import com.company.Customer.Customer;
import com.company.Exceptions.IllegalVehicleException;
import com.company.Exceptions.VehicleAlreadyAssignedException;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;


public class JsonWriter implements Writer{
    public static void writeCustomerToFile(Customer customer, String fileName) throws VehicleAlreadyAssignedException, IllegalVehicleException {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(fileName)) {

            gson.toJson(customer, writer);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        gson.toJson(customer, System.out);
    }
}