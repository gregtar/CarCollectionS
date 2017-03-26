package com.company.Readers;

/**
 * Created by gregtar on 25.03.17.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by GregTar on 19.03.2017.
 */

import com.company.Customer.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonReader {
    public static Customer readCustomerFromFile(String filename){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {  FileReader fileReader = new FileReader(filename);
            Customer customer = gson.fromJson(fileReader, Customer.class);
            return customer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
