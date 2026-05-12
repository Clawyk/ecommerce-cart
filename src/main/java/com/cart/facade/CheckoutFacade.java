package com.cart.facade;

import com.cart.ShoppingCart;
import com.cart.observer.AnalyticsObserver;
import com.cart.observer.InventoryObserver;
import com.cart.observer.NotificationObserver;

public class CheckoutFacade {

    private ShoppingCart cart;

    public CheckoutFacade(ShoppingCart cart) {
        this.cart = cart;
        // Observer'ları bağla
        cart.addObserver(new InventoryObserver());
        cart.addObserver(new NotificationObserver());
        cart.addObserver(new AnalyticsObserver());
    }

    public void checkout(String paymentMethod) {
        System.out.println("=== ÖDEME SÜRECİ BAŞLIYOR ===");
        cart.checkout(paymentMethod);
        System.out.println("=== ÖDEME SÜRECİ TAMAMLANDI ===");
    }
}