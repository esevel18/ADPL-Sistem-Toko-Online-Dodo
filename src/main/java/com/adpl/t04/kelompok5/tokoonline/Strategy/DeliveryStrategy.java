package com.adpl.t04.kelompok5.tokoonline.Strategy;

public interface DeliveryStrategy {
    double calculateFee();
    void deliver();
    String getDeliveryType();
}
