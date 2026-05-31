package com.adpl.t04.kelompok5.tokoonline;

import java.time.LocalDateTime;
import java.util.List;

import com.adpl.t04.kelompok5.tokoonline.Decorator.ItemComponent;
import com.adpl.t04.kelompok5.tokoonline.Strategy.DeliveryContext;
import com.adpl.t04.kelompok5.tokoonline.Strategy.PaymentContext;

public class Transaction {
    private List<ItemComponent> items;
    private DeliveryContext deliveryStrategy;
    private PaymentContext paymentStrategy;

    private double subtotal;
    private double deliveryFee;
    private double total;

    private LocalDateTime transactionDate;

    public Transaction(List<ItemComponent> items, DeliveryContext deliveryStrategy, PaymentContext paymentStrategy, double subtotal, double deliveryFee, double total) {
        this.items = items;
        this.deliveryStrategy = deliveryStrategy;
        this.paymentStrategy = paymentStrategy;
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.total = total;

        this.transactionDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("============== INVOICE ==============\n");

        for(ItemComponent item : items) {
            sb.append(item.getDetails() + "\n");
            sb.append("Rp " + item.getPrice() + "\n");
            sb.append("\n");
        }

        sb.append("Subtotal : Rp " + subtotal + "\n");
        sb.append("Delivery (" + deliveryStrategy.getDeliveryMethod() + ") : Rp " + deliveryFee + "\n");
        sb.append("Payment : " + paymentStrategy.getPaymentMethod() + "\n");
        sb.append("--------------------------------\n");
        sb.append("TOTAL : Rp " + total + "\n");

        sb.append("Tanggal : " + transactionDate);

        return sb.toString();
    }
}