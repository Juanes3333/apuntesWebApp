package com.example.demo.model;

public class Vehicle {
    private Integer id;
    private String name;
    private String plateCode;
    private String vehicleType;
    private String driver;
    private String registrationDate;
    private String status;

    public Vehicle() {
    }

    public Vehicle(Integer id, String name, String plateCode, String vehicleType, String driver, String registrationDate, String status) {
        this.id = id;
        this.name = name;
        this.plateCode = plateCode;
        this.vehicleType = vehicleType;
        this.driver = driver;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
