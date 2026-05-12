package com.cart.strategy;

import com.cart.Product;
import java.util.List;

// Yüzdelik indirimli fiyat
public class DiscountedPricing implements PricingStrategy {

    private double percent;

    public DiscountedPricing(double percent) {
        this.percent = percent;
    }

    @Override
    public double calculate(List<Product> items) {
        double total = 0;
        for (Product p : items) {
            total += p.price * p.quantity;
        }
        return total - (total * percent / 100);
    }

    @Override
    public String getDescription() {
        return "%" + percent + " indirimli fiyatlandırma uygulandı";
    }
}
