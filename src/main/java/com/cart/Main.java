package com.cart;

import com.cart.facade.CheckoutFacade;
import com.cart.strategy.BulkPricing;
import com.cart.strategy.DiscountedPricing;
import com.cart.strategy.StandardPricing;

/**
 * Uygulamanın giriş noktası.
 *
 * Burada 3 farklı fiyatlandırma stratejisini gösteriyoruz:
 * 1. Standart fiyat
 * 2. Yüzde indirimli fiyat
 * 3. Toplu alım fiyatı
 *
 * Strategy Pattern'in gücü: aynı sepet nesnesi üzerinde
 * algoritma runtime'da değiştiriliyor, if-else yok.
 */
public class Main {
    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        // Facade tüm observer'ları otomatik bağlıyor
        CheckoutFacade checkout = new CheckoutFacade(cart);

        // Ürünleri sepete ekle — her addItem Observer'ları tetikliyor
        cart.addItem(new Product("Laptop", 15000, 1));
        cart.addItem(new Product("Mouse", 500, 2));
        cart.addItem(new Product("Klavye", 800, 1));

        // Strateji 1: Standart fiyat (varsayılan)
        System.out.println("\n--- Standart Fiyat ---");
        cart.printCart();

        // Strateji 2: Runtime'da %10 indirime geçiyoruz
        // if-else yazmadık, sadece stratejiyi değiştirdik
        System.out.println("\n--- %10 İndirimli Fiyat ---");
        cart.setPricingStrategy(new DiscountedPricing(10));
        cart.printCart();

        // Strateji 3: Toplu alım — 3+ ürün varsa %15 indirim
        System.out.println("\n--- Toplu Alım Fiyatı ---");
        cart.setPricingStrategy(new BulkPricing());
        cart.printCart();

        // Ödeme — Facade tüm süreci yönetiyor
        System.out.println();
        checkout.checkout("CREDIT_CARD");
    }
}