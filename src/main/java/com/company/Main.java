
package com.company;

import com.company.Customer.Customer;
import com.company.Exceptions.*;
import com.company.Vehicle.Color;
import com.company.Vehicle.Vehicle;
import com.company.Writers.JsonWriter;
import com.company.Workshop;

import org.joda.time.LocalDate;

/**
 * Created by GregTar on 26.03.2017.
 */
public class Main {
    public static void main(String[] args) throws IllegalVehicleException, VehicleAlreadyAssignedException {
        Workshop workshop = createWorkshop();

        System.out.println(workshop);

        removeMercedesFromPetrov(workshop);

        System.out.println(workshop);
        //JsonWriter.writeCustomerToFile(createCustomerPetrov() , "E:\\customerSergeev.json");




    }

    private static void removeMercedesFromPetrov(Workshop workshop) {
        Customer customer = null;
        try {
            customer = workshop.findCustomerByPhoneNumber("+78005678930");
        } catch (InvalidPhoneNumberException e) {
            e.printStackTrace();
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
        }

        Vehicle vehicle = null;

        try {
            vehicle = customer.findVehicle("H174AK199"); // it will not be found
        } catch (VehicleNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidNumberPlateException e) {
            e.printStackTrace();
        }

        try {
            vehicle = customer.findVehicle("X199CB99");
        } catch (VehicleNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidNumberPlateException e) {
            e.printStackTrace();
        }

        try {
            customer.removeVehicle(vehicle);
        } catch (IllegalVehicleException e) {
            e.printStackTrace();
        } catch (VehicleNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Workshop createWorkshop() {
        Workshop workshop = new Workshop();

        Customer customerSergeev = createCustomerSergeev();

        Customer customerPetrov = createCustomerPetrov();

        Customer customerRymar = createCustomerRymar();

        try {
            workshop.addCustomer(customerPetrov);
            workshop.addCustomer(customerSergeev);
        } catch (IllegalCustomerException e) {
            e.printStackTrace();
        } catch (CustomerAlreadyExistsException e) {
            e.printStackTrace();
        }

        return workshop;
    }
    private static Customer createCustomerPetrov() {
        Customer customerPetrov = new Customer("Mark", "Petrov", "+38025678930", "20.0.1991");
        Vehicle vehiclePetrovAudi = new Vehicle("A102AP199", "Audi", "A6", (Color.BLUE));
        Vehicle vehiclePetrovMercedes = new Vehicle("X199CB99", "Mercedes", "SLR", (Color.GREY));
        Vehicle vehiclePetrovKia = new Vehicle("H011AA199", "Kia", "Cerato", (Color.WHITE));
        Vehicle vehiclePetrovFiat = new Vehicle("H299CX199", "Fiat", "Punto", (Color.BLACK));

        try {
            customerPetrov.addVehicle(vehiclePetrovAudi);
            customerPetrov.addVehicle(vehiclePetrovFiat);
            customerPetrov.addVehicle(vehiclePetrovKia);
            customerPetrov.addVehicle(vehiclePetrovMercedes);
        } catch (VehicleAlreadyAssignedException e) {
            e.printStackTrace();
        } catch (IllegalVehicleException e) {
            e.printStackTrace();
        }
        return customerPetrov;
    }

    private static Customer createCustomerSergeev() {
        Customer customerSergeev = new Customer("Ivan", "Sergeev", "+38095234567", new LocalDate("1991-03-10"));
        Vehicle vehicleSergeevRenault = new Vehicle("H174AK199", "Renault", "Logan", (Color.RED));
        Vehicle vehicleSergeevMercedes = new Vehicle("B099CC199", "Mercedes", "C200", (Color.BLACK));

        try {
            customerSergeev.addVehicle(vehicleSergeevMercedes);
            customerSergeev.addVehicle(vehicleSergeevRenault);
        } catch (VehicleAlreadyAssignedException e) {e.printStackTrace();
        } catch (IllegalVehicleException e) {
            e.printStackTrace();
        }
        return customerSergeev;
    }

    private static Customer createCustomerRymar() {
        Customer customerRymar = new Customer("Bogdan", "Rymar", "+380951453661", LocalDate.parse("1996-12-20"));
        Vehicle vehicleRymarMercedess = new Vehicle("CB 9080 BC", "Mercedess", "w210", (Color.BLUE));
        Vehicle vehicleRymarRenault = new Vehicle("AC 8239 CD", "Renault", "Duster", (Color.GREY));

        try {
            customerRymar.addVehicle(vehicleRymarMercedess);
            customerRymar.addVehicle(vehicleRymarRenault);
        } catch (VehicleAlreadyAssignedException e) {
            e.printStackTrace();
        } catch (IllegalVehicleException e) {
            e.printStackTrace();
        }
        return customerRymar;

    }



}