package com.company.Writers;

/**
 * Created by gregtar on 25.03.17.
 */
import com.company.Customer.Customer;
import com.company.Exceptions.IllegalVehicleException;
import com.company.Exceptions.VehicleAlreadyAssignedException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;



/**
 * Created by GregTar on 20.03.2017.
 */
public class XmlWriter {
    public static Customer writeCustomerToFile(Customer Customer, File file) throws VehicleAlreadyAssignedException, IllegalVehicleException, JAXBException {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(Customer, file);



        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return Customer;
    }
}

