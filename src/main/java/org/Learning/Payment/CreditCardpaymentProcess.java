package org.Learning.Payment;

public class CreditCardpaymentProcess implements  PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using credit card.");
        return true;
    }
}
