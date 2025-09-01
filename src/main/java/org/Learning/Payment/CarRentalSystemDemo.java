package org.Learning.Payment;

import org.Learning.Car;
import org.Learning.CarRentalSystem;
import org.Learning.Customer;
import org.Learning.Reservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CarRentalSystemDemo {
    public static void run(){
        CarRentalSystem carRentalSystem = CarRentalSystem.getInstance();
        carRentalSystem.addCar(new Car("Toyota", "Camry", 2022, "ABC123", 50.0));
        carRentalSystem.addCar(new Car("Toyota", "Land cruiser", 2022, "ABC1w23", 450.0));
        carRentalSystem.addCar(new Car("BMW", "320Li", 2025, "ABC1w23", 550.0));


        Customer customer = new Customer("John Doe", "john@example.com", "DL1234");

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);

        List<Car>searchResults = carRentalSystem.searchCras("Camry","Toyota",true);
        if(!searchResults.isEmpty()){
            Car car = searchResults.getFirst();


            Reservation reservation = carRentalSystem.addReservation(customer,car,startDate,endDate);
            if(reservation!= null){
                boolean paymentSuccess = carRentalSystem.processPayment(reservation,new CreditCardpaymentProcess());
                if(paymentSuccess){
                    System.out.println("Payment successful");
                }else {
                    System.out.println("Payment failed");
                    carRentalSystem.cancelReservation(reservation.getReservationId());
                }
            }else {
                System.out.println("selected car is not available in the given dates");

                }
            }
        else {
            System.out.println("No cars available on the given dates");
        }
    }


}
