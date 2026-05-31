package com.adpl.t04.kelompok5.tokoonline.Strategy;

public class ExpressDelivery implements DeliveryStrategy{
    @Override
    public void deliver() {
        System.out.println("Pesanan akan dikirim dengan pengiriman express...");
    }

    @Override
    public double calculateFee() {
        return 100_000;
    }

    @Override
    public String getDeliveryType() {
        return "Express Delivery";
    }
}
