package com.example.demo.model;

public class DeliveryRecord {
    private Integer id;
    private String trackingCode;
    private String packageDescription;
    private String deliveryDate;
    private String destination;
    private Integer priorityLevel;
    private Integer vehicleId;

    public DeliveryRecord() {
}

    public DeliveryRecord(Integer id, String trackingCode, String packageDescription, String deliveryDate, String destination, Integer priorityLevel, Integer vehicleId) {
        this.id = id;
        this.trackingCode = trackingCode;
        this.packageDescription = packageDescription;
        this.deliveryDate = deliveryDate;
        this.destination = destination;
        this.priorityLevel = priorityLevel;
        this.vehicleId = vehicleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
}
