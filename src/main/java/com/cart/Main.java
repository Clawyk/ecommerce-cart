package com.cart;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Product("Laptop", 15000, 1));
        cart.addItem(new Product("Mouse", 500, 2));
        cart.addItem(new Product("Klavye", 800, 1));

        // Artık discountType string atamak yok, Factory hallediyor
        cart.setDiscount("PERCENTAGE", 10);

        cart.printCart();
        cart.checkout("CREDIT_CARD");
    }
}