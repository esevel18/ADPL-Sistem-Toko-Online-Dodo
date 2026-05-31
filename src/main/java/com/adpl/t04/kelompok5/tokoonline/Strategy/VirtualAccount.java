package com.adpl.t04.kelompok5.tokoonline.Strategy;

public class VirtualAccount implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Pembayaran sebesar Rp" + amount + " menggunakan VA berhasil!!");
    }

    @Override
    public String getPaymentMethod() {
        return "Virutal Account";
    }
}
