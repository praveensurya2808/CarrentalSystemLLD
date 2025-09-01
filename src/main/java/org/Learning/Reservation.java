package org.Learning;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

    private final String reservationId;
    private final Customer customer;
    private final Car car;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalCost;

    public Reservation(String reservationId, Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculatetotalcost();
    }

    private double calculatetotalcost(){
        long daysRented = ChronoUnit.DAYS.between(startDate, endDate);
        return car.getRentalPriceperday()*daysRented;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getReservationId() {
        return reservationId;
    }
}
