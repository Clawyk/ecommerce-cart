package com.cart.discount;

public class NoDiscount implements Discount {

    @Override
    public double apply(double total) {
        return total;
    }

    @Override
    public String getDescription() {
        return "İndirim yok";
    }
}