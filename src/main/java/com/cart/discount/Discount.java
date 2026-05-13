package com.cart.discount;

//interface
public interface Discount {
    double apply(double total);
    String getDescription();
}