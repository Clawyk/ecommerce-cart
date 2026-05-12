package com.cart.strategy;

import com.cart.Product;
import java.util.List;

// Toplu alım — 3 veya daha fazla ürün varsa %15 indirim
public class BulkPricing implements PricingStrategy {

    @Override
    public double calculate(List<Product> items) {
        double total = 0;
        int totalQty = 0;
        for (Product p : items) {
            total += p.price * p.quantity;
            totalQty += p.quantity;
        }
        if (totalQty >= 3) {
            return total * 0.85; // %15 indirim
        }
        return total;
    }

    @Override
    public String getDescription() {
        return "Toplu alım fiyatlandırması uygulandı (3+ ürün = %15 indirim)";
    }
}
