package com.cart.observer;

import com.cart.Product;

// Tüm observer'ların uyacağı sözleşme
public interface CartObserver {
    void onItemAdded(Product p);
    void onItemRemoved(String productName);
    void onCheckoutCompleted(double total);
}
