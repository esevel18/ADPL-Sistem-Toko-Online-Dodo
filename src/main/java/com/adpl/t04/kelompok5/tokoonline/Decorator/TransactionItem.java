package com.adpl.t04.kelompok5.tokoonline.Decorator;

import com.adpl.t04.kelompok5.tokoonline.FactoryMethod.Product;

// concreate component
public class TransactionItem implements ItemComponent {
    private Product product;
    private int quantitiy;
    private double totalPrices;

    public TransactionItem(Product product, int quantitiy){
        this.product = product;
        this.quantitiy = quantitiy;
        this.totalPrices = quantitiy * product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantitiy() {
        return quantitiy;
    }

    public double getTotalPrices() {
        return totalPrices;
    }

    @Override
    public double getPrice() {
        return this.product.getPrice() * this.quantitiy;
    }

    @Override
    public String getDetails() {
        return this.product.getName() + " x " + this.quantitiy;
    }

    @Override
    public String toString() {
        return String.format(
            "%s | Qty: %d | Harga: Rp.%.0f | Subtotal: Rp.%.0f",
            product.getName(),
            quantitiy,
            product.getPrice(),
            totalPrices
        );
    }

}
