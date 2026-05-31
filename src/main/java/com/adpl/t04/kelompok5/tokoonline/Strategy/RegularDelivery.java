package com.adpl.t04.kelompok5.tokoonline.Strategy;

public class RegularDelivery implements DeliveryStrategy{
    @Override
    public void deliver() {
        System.out.println("Pesanan akan dikirim dengan pengiriman regular...");
    }

    @Override
    public double calculateFee() {
        return 50_000;
    }

    @Override
    public String getDeliveryType() {
        return "Regular Delivery";
    }
}
