package com.cart.strategy;

import com.cart.Product;
import java.util.List;

// Fiyat hesaplama algoritmasının sözleşmesi
public interface PricingStrategy {
    double calculate(List<Product> items);
    String getDescription();
}
