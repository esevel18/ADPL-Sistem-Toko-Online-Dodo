package com.adpl.t04.kelompok5.tokoonline.Strategy;

public class Gopay implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Pembayaran sebesar Rp" + amount + " dengan menggunakan gopay berhasil!!");
    }

    @Override
    public String getPaymentMethod() {
        return "Gopay";
    }
}
