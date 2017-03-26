package com.company.Customer;



import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.company.Vehicle.Vehicle;
import org.joda.time.LocalDate;

import com.company.Exceptions.IllegalVehicleException;
import com.company.Exceptions.InvalidNumberPlateException;
import com.company.Exceptions.VehicleAlreadyAssignedException;
import com.company.Exceptions.VehicleNotFoundException;




public class Customer {
    private String id;
    private String name;
    private String surname;
    private String phone;
    private LocalDate birthDate;

    private HashMap<String, Vehicle> registeredVehicles;

    public Customer(String id, String name, String surname, String phone, LocalDate birthDate) {
        this.id = UUID.randomUUID().toString();
        this.name = this.name;
        this.surname = this.surname;
        this.phone = this.phone;
        this.birthDate = this.birthDate;
        this.registeredVehicles = new HashMap<>();
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void addVehicle(Vehicle newVehicle) throws VehicleAlreadyAssignedException, IllegalVehicleException {
        if (newVehicle == null)
            throw new IllegalVehicleException("Provided vehicle is invalid");

  //     if (registeredVehicles.containsKey(newVehicle.getNumberPlate()))
    //        throw new VehicleAlreadyAssignedException("Vehicle with number plate " + newVehicle.getNumberPlate() +
    //                " is already assigned for " + name + " " + surname);

        registeredVehicles.put(newVehicle.getNumberPlate(), newVehicle);
    }

    public void removeVehicle(Vehicle vehicle) throws IllegalVehicleException, VehicleNotFoundException {
        if (vehicle == null)
            throw new IllegalVehicleException("Provided vehicle is invalid");

        checkVehicleAssigned(vehicle.getNumberPlate());

        registeredVehicles.remove(vehicle.getNumberPlate());
    }

    public Vehicle findVehicle(String numberPlate) throws VehicleNotFoundException, InvalidNumberPlateException {
        if ((numberPlate == null) || numberPlate.isEmpty())
            throw new InvalidNumberPlateException("Number plate is invalid");

        checkVehicleAssigned(numberPlate);

        return registeredVehicles.get(numberPlate);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("--- User info ---\n");
        builder.append(primaryFieldsToString());

        for (Map.Entry<String, Vehicle> vehicleEntry : registeredVehicles.entrySet()) {
            builder.append("\n").append(vehicleEntry.getValue().toString());
        }
        builder.append("--- End ---\n");

        return builder.toString();
    }

    private String primaryFieldsToString() {
        return "Customer : " + id + "\n" +
                "  name : " + name + "\n" +
                "  surname : " + surname + "\n" +
                "  phone : " + phone + "\n" +
                "  birthDate: " + birthDate;
    }

    private void checkVehicleAssigned(String numberPlate) throws VehicleNotFoundException {
        if (!registeredVehicles.containsKey(numberPlate))
            throw new VehicleNotFoundException("Vehicle with number plate " + numberPlate +
                    " is not assigned for " + name + " " + surname);
    }

 public Customer(String name, String surname,  String phone, LocalDate birthDate){

 }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}