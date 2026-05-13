package com.cart;

import com.cart.observer.CartObserver;
import com.cart.strategy.PricingStrategy;
import com.cart.strategy.StandardPricing;

import java.util.ArrayList;
import java.util.List;

/**
 * Alışveriş sepeti ana sınıfı.
 *
 * Başlangıçta tüm sorumluluklar bu sınıftaydı:
 * indirim hesaplama, ödeme alma, bildirim gönderme...
 * Refactoring sonrası her sorumluluk kendi yerine taşındı.
 *
 * Şu an bu sınıf sadece ürün listesini yönetiyor,
 * geri kalanını Strategy ve Observer hallediyor.
 */
public class ShoppingCart {

    private List<Product> items = new ArrayList<>();

    /**
     * Strategy Pattern: Fiyatlandırma algoritması dışarıdan verilir.
     * Varsayılan olarak StandardPricing kullanılır.
     * Runtime'da setPricingStrategy() ile değiştirilebilir.
     */
    private PricingStrategy pricingStrategy = new StandardPricing();

    /**
     * Observer Pattern: Sepeti dinleyen sistemlerin listesi.
     * ShoppingCart bu sistemleri tanımıyor, sadece haber veriyor.
     * Yeni sistem eklemek için sadece addObserver() çağrılır.
     */
    private List<CartObserver> observers = new ArrayList<>();

    public void addObserver(CartObserver observer) {
        observers.add(observer);
    }

    /**
     * Fiyatlandırma stratejisini değiştirir.
     * Bu metodun gücü: uygulama çalışırken bile strateji değişebilir.
     * if-else yazmak zorunda kalmıyoruz — Open/Closed Principle sağlandı.
     */
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void addItem(Product p) {
        items.add(p);
        System.out.println(p.name + " eklendi.");
        // Tüm observer'ları haberdar et — stok rezerve edilsin, analitik yazsın vs.
        for (CartObserver o : observers) {
            o.onItemAdded(p);
        }
    }

    public void removeItem(String name) {
        items.removeIf(p -> p.name.equals(name));
        System.out.println(name + " çıkarıldı.");
        for (CartObserver o : observers) {
            o.onItemRemoved(name);
        }
    }

    /**
     * Toplam fiyatı aktif stratejiye göre hesaplar.
     * Hangi algoritmanın kullanıldığını ShoppingCart bilmiyor,
     * bu karar PricingStrategy'ye bırakıldı.
     * Ödeme sonrası tüm observer'ları bilgilendirme işlemi yapılacak
     */
    public double calculateTotal() {
        double total = pricingStrategy.calculate(items);
        System.out.println(pricingStrategy.getDescription());
        return total;
    }

    public List<Product> getItems() {
        return items;
    }

    public void checkout(String paymentMethod) {
        double total = calculateTotal();
        System.out.println(paymentMethod + " ile " + total + " TL ödendi.");

        for (CartObserver o : observers) {
            o.onCheckoutCompleted(total);
        }
        items.clear();
    }

    public void printCart() {
        System.out.println("SEPET");
        for (Product p : items) {
            System.out.println(p.name + " x" + p.quantity
                    + " = " + (p.price * p.quantity) + " TL");
        }
        System.out.println("Toplam: " + calculateTotal() + " TL");
    }
}