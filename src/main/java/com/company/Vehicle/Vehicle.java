package com.company.Vehicle;
        import java.util.UUID;

public  class Vehicle {
    private String id;
    private String numberPlate;
    private String mark;
    private String model;
    private Color color;

    public String getId() {
        return id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public Color getColor() {
        return color;
    }

    public Vehicle(String numberPlate, String mark, String model, Color color) {
        this.id = UUID.randomUUID().toString();
        this.numberPlate = numberPlate;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehicle : " + id + "\n" +
                "   numberPlate = '" + numberPlate + "'\n" +
                "   mark        = '" + mark + "'\n" +
                "   model       = '" + model + "'\n" +
                "   color       = '" + color + "'\n";
    }
}