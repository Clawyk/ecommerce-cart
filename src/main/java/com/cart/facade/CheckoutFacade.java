package com.cart.facade;

import com.cart.ShoppingCart;
import com.cart.decorator.EmailDecorator;
import com.cart.decorator.LoggingDecorator;
import com.cart.decorator.StockDecorator;

// FACADE — karmaşık checkout sürecini tek noktadan yönetir
// Dışarıdan bakan sadece checkout() çağırır, içerde ne döndüğünü bilmez
public class CheckoutFacade {

    private ShoppingCart cart;

    public CheckoutFacade(ShoppingCart cart) {
        this.cart = cart;
        // Decorator'ları buraya bağlıyoruz — dışarıdan uğraşmak gerekmez
        cart.addDecorator(new LoggingDecorator());
        cart.addDecorator(new EmailDecorator());
        cart.addDecorator(new StockDecorator());
    }

    public void checkout(String paymentMethod) {
        System.out.println("=== ÖDEME SÜRECİ BAŞLIYOR ===");
        cart.checkout(paymentMethod);
        System.out.println("=== ÖDEME SÜRECİ TAMAMLANDI ===");
    }
}
