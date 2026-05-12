package com.cart;

import com.cart.facade.CheckoutFacade;
import com.cart.strategy.BulkPricing;
import com.cart.strategy.DiscountedPricing;

public class Main {
    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();
        CheckoutFacade checkout = new CheckoutFacade(cart);

        // Ürünleri ekle
        cart.addItem(new Product("Laptop", 15000, 1));
        cart.addItem(new Product("Mouse", 500, 2));
        cart.addItem(new Product("Klavye", 800, 1));

        // Standart fiyatla göster
        System.out.println("\n--- Standart Fiyat ---");
        cart.printCart();

        // Runtime'da strategy değiştir — %10 indirimli
        System.out.println("\n--- %10 İndirimli Fiyat ---");
        cart.setPricingStrategy(new DiscountedPricing(10));
        cart.printCart();

        // Runtime'da tekrar değiştir — toplu alım
        System.out.println("\n--- Toplu Alım Fiyatı ---");
        cart.setPricingStrategy(new BulkPricing());
        cart.printCart();

        // Ödeme al
        System.out.println();
        checkout.checkout("CREDIT_CARD");
    }
}