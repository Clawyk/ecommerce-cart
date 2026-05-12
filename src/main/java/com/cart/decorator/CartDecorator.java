package com.cart.decorator;

import com.cart.Product;

// Tüm decorator'ların uyacağı temel sözleşme
public interface CartDecorator {
    void onItemAdded(Product p);
    void onCheckout(double total);
}