package com.adpl.t04.kelompok5.tokoonline.Strategy;

public class PaymentContext{
    private PaymentStrategy payementStrategy;
    
    public void setPayementStrategy(PaymentStrategy payementInterface) {
        this.payementStrategy = payementInterface;
    }

    public void executeStrategy(double amount){
        payementStrategy.pay(amount);
    }

    public String getPaymentMethod(){
        return payementStrategy.getPaymentMethod();
    }
}
