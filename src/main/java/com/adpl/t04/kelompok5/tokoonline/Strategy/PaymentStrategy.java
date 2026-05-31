package com.adpl.t04.kelompok5.tokoonline.Strategy;

public interface PaymentStrategy {
    void pay(double amount);
    String getPaymentMethod();
}
