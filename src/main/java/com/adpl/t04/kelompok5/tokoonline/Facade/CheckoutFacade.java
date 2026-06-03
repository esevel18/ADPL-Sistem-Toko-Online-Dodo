package com.adpl.t04.kelompok5.tokoonline.Facade;

import java.util.List;
import java.util.ArrayList;

import com.adpl.t04.kelompok5.tokoonline.Builder.Transaction;
import com.adpl.t04.kelompok5.tokoonline.Command.Cart;
import com.adpl.t04.kelompok5.tokoonline.Decorator.*;
import com.adpl.t04.kelompok5.tokoonline.Strategy.*;

public class CheckoutFacade {
    private List<ItemComponent> finalItems;
    private DeliveryContext deliveryContextStrategy;
    private PaymentContext paymentContextStrategy;

    public CheckoutFacade() {
        this.finalItems = new ArrayList<>();
        this.deliveryContextStrategy = new DeliveryContext();
        this.paymentContextStrategy = new PaymentContext();
    }

    public void addAddons(Cart cart, String kado, String guarantee) {
        // tambahkan decorator
        for (TransactionItem item : cart.getItems()) {
            ItemComponent decoratedItem = item;
            if (kado.equals("y")) {
                decoratedItem = new GiftWrap(decoratedItem);
            }
            if (guarantee.equals("y")) {
                decoratedItem = new FragileGuarantee(decoratedItem);
            }
            finalItems.add(decoratedItem);
        }
    }

    public double calculateSubTotalPrice() {
        double subtotal = 0;
        for (ItemComponent item : finalItems) {
            subtotal += item.getPrice();
        }
        return subtotal;
    }

    public void addShippingMethod(String deliveryMethod) {
        if (deliveryMethod.equals("1")) {
            deliveryContextStrategy.setDeliveryStrategy(new RegularDelivery());
        } else {
            deliveryContextStrategy.setDeliveryStrategy(new ExpressDelivery());
        }
    }

    public double getShippingFee() {
        return deliveryContextStrategy.calculateFee();
    }

    public double getTotalPrices() {
        return this.calculateSubTotalPrice() + this.getShippingFee();
    }

    public void addPaymentMethod(String paymentMethod) {
        if (paymentMethod.equals("1")) {
            paymentContextStrategy.setPayementStrategy(new VirtualAccount());
        } else {
            paymentContextStrategy.setPayementStrategy(new Gopay());
        }
    }

    public Transaction getTransaction() {
        return new Transaction(this.finalItems,
                this.deliveryContextStrategy,
                this.paymentContextStrategy,
                this.calculateSubTotalPrice(),
                this.getShippingFee(),
                this.getTotalPrices());
    }

    public void pay(){
        paymentContextStrategy.executeStrategy(this.getTotalPrices());
    }
}
