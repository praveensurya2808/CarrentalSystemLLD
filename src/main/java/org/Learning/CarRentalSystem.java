package org.Learning;

import org.Learning.Payment.PaymentProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalSystem {
    private static CarRentalSystem instance ;
    private Map<String,Car> cars;
    private Map<String,Customer>customers;
    private Map<String,Reservation> reservations;

    private CarRentalSystem() {
        cars = new ConcurrentHashMap<>();
        customers = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
    }

    public static synchronized  CarRentalSystem getInstance() {
        if(instance == null) {
            instance = new CarRentalSystem();
        }
        return instance;
    }

    public void addCar(Car car){
        cars.put(car.getLicensePlate(),car);
    }

    public void removeCar(Car car){
        cars.remove(car.getLicensePlate());
    }

    public List<Car> searchCras(String model , String make , boolean isAvailable){
        List<Car>availableCars = new ArrayList<>();
        for(Car car : cars.values()){
            if(car.getModel().equalsIgnoreCase(model) && car.getMake().equalsIgnoreCase(make) && car.isAvailable() == isAvailable){
                availableCars.add(car);
            }
        }
        return availableCars;

    }

    private boolean isCarAvailable(Car car , LocalDate startDate , LocalDate endDate) {
        for(Reservation reservation : reservations.values()){
            if(reservation.getCar().equals(car)){
                if(startDate.isBefore(reservation.getStartDate()) && endDate.isAfter(reservation.getEndDate())){
                    return false;
                }
            }
        }
        return true;
    }

    public synchronized Reservation addReservation(Customer customer , Car car , LocalDate startDate , LocalDate endDate) {
        if(isCarAvailable(car,startDate,endDate)){
            String reservationid = genereteReservationId();
            Reservation reservation = new Reservation(reservationid,customer,car,startDate,endDate);
            reservations.put(reservationid,reservation);
            car.setAvailable(false);
            return reservation;
        }
        return null;
    }

    public  synchronized  Reservation cancelReservation(String reservationId) {
        Reservation reservation = reservations.remove(reservationId);
        if(reservation != null){
            reservation.getCar().setAvailable(true);
        }
        return reservation;
    }

    public boolean processPayment(Reservation reservation , PaymentProcessor paymentProcessor) {
        return paymentProcessor.processPayment(reservation.getTotalCost());
    }

    private String genereteReservationId() {
        return "RES" + System.currentTimeMillis();
    }
}
