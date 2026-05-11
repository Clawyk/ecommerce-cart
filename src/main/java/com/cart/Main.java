package com.cart;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Product("Laptop", 15000, 1));
        cart.addItem(new Product("Mouse", 500, 2));
        cart.addItem(new Product("Klavye", 800, 1));

        cart.discountType = "PERCENTAGE";
        cart.discountValue = 10;

        cart.printCart();
        cart.checkout("CREDIT_CARD");
    }
}