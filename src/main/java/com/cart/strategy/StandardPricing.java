package com.cart.strategy;

import com.cart.Product;
import java.util.List;

// Normal fiyat — indirim yok
public class StandardPricing implements PricingStrategy {

    @Override
    public double calculate(List<Product> items) {
        double total = 0;
        for (Product p : items) {
            total += p.price * p.quantity;
        }
        return total;
    }

    @Override
    public String getDescription() {
        return "Standart fiyatlandırma uygulandı";
    }
}
