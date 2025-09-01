package org.Learning;

public class Car {
    private final  String make ;
    private final String model;
    private final int year;
    private final String licensePlate;
    private final double rentalPriceperday;
    private boolean isAvailable;

    public Car(String make, String model, int year, String licensePlate, double rentalPriceperday) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.rentalPriceperday = rentalPriceperday;
        this.isAvailable = true;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getRentalPriceperday() {
        return rentalPriceperday;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
