package com.cart.discount;

public class PercentageDiscount implements Discount {
    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double apply(double total) {
        return total - (total * percent / 100);
    }

    @Override
    public String getDescription() {
        return "%" + percent + " indirim uygulandı";
    }
}