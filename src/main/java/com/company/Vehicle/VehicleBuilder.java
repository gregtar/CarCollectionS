package com.company.Vehicle;

public class VehicleBuilder{
   private String vehMark;
   private String vehModel;
   private String vehNumber;
   private Color vehColor;

   public VehicleBuilder setMark(String mark){
       this.vehMark = mark;
       return this;
   }


   public VehicleBuilder setModel(String model){
       this.vehModel = model;
       return this;
   }
   public VehicleBuilder setNumberPlate(String numberPlate){
       this.vehNumber = numberPlate;
       return this;
   }
   public VehicleBuilder setColor(Color color){
       this.vehColor = color;
       return this;
   }


    public Vehicle build(){
        return new Vehicle(vehMark,vehModel,vehNumber,vehColor);

    }
}
