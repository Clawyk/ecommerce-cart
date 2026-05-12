package com.cart.decorator;

import com.cart.Product;

// Loglama katmanı — sepete ürün eklenince ve ödeme yapılınca loglar
public class LoggingDecorator implements CartDecorator {

    @Override
    public void onItemAdded(Product p) {
        System.out.println("[LOG] Ürün eklendi: " + p.name + " | Fiyat: " + p.price + " TL");
    }

    @Override
    public void onCheckout(double total) {
        System.out.println("[LOG] Ödeme tamamlandı. Toplam: " + total + " TL");
    }
}