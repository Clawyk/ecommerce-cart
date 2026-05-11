package com.cart.discount;

// Tüm indirim tiplerinin uyması gereken sözleşme (interface)
public interface Discount {
    double apply(double total);
    String getDescription();
}