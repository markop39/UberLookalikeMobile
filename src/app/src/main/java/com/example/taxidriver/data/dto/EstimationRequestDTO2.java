package com.example.taxidriver.data.dto;

public class EstimationRequestDTO2 {

    private String startAddress;
    private String endAddress;
    private boolean babyTransport;
    private boolean petTransport;
    private String vehicleType;
    public EstimationRequestDTO2() {

    }


    public EstimationRequestDTO2(String startAddress, String endAddress, boolean babyTransport, boolean petTransport, String vehicleType) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.vehicleType = vehicleType;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
