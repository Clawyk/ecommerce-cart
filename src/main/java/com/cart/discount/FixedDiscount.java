package com.cart.discount;

public class FixedDiscount implements Discount {
    private double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double apply(double total) {
        return total - amount;
    }

    @Override
    public String getDescription() {
        return amount + " TL sabit indirim uygulandı";
    }
}