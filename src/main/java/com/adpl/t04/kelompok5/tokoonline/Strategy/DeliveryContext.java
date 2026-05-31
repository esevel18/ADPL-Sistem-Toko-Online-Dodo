package com.adpl.t04.kelompok5.tokoonline.Strategy;

public class DeliveryContext {
    private DeliveryStrategy deliveryStrategy;

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public void deliver(){
        this.deliveryStrategy.deliver();
    }

    public double calculateFee(){
        return this.deliveryStrategy.calculateFee();
    }

    public String getDeliveryMethod(){
        return this.deliveryStrategy.getDeliveryType();
    }
}
