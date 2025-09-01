package org.Learning.Payment;

public class UpiPaymentMethod implements  PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using UPI.");
        return true;
    }
}
