package com.cart.facade;

import com.cart.ShoppingCart;
import com.cart.observer.AnalyticsObserver;
import com.cart.observer.InventoryObserver;
import com.cart.observer.NotificationObserver;

/**
 * Facade Pattern uygulaması.
 *
 * Problem: Checkout süreci karmaşıktı. Observer bağlantıları,
 * ödeme çağrısı, bildirimler hepsi dışarıdan tek tek yapılıyordu.
 *
 * Çözüm: Bu Facade tüm karmaşıklığı içine aldı.
 * Dışarıdan bakan sadece checkout() çağırır, içerde ne döndüğünü bilmez.
 *
 * Facade vs Observer farkı:
 * Observer → "bir şey olunca haber ver"
 * Facade  → "karmaşık süreci basit arayüzün arkasına sakla"
 * Observer'ları merkezi olarak checkoutFacade de  bağlayacağız
 * Böylece her yerde tek tek addObserver() yazmak zorunda kalmayacağız
 */
public class CheckoutFacade {

    private ShoppingCart cart;

    public CheckoutFacade(ShoppingCart cart) {
        this.cart = cart;

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