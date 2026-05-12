package com.cart;

import com.cart.facade.CheckoutFacade;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Facade tüm decorator'ları otomatik bağlıyor
        CheckoutFacade checkout = new CheckoutFacade(cart);

        cart.addItem(new Product("Laptop", 15000, 1));
        cart.addItem(new Product("Mouse", 500, 2));
        cart.addItem(new Product("Klavye", 800, 1));

        cart.setDiscount("PERCENTAGE", 10);

        cart.printCart();

        // Tek satırla tüm süreci çalıştırıyoruz
        checkout.checkout("CREDIT_CARD");
    }
}