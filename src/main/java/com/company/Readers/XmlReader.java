package com.company.Readers;

/**
 * Created by gregtar on 25.03.17.
 */
import com.company.Customer.Customer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class XmlReader {
    public static Customer readCustomerFromFile(String filename) {

        try {
            File file = new File(filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Customer customer = (Customer) unmarshaller.unmarshal(file);

            return customer;

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}

