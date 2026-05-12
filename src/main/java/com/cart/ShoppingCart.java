package com.cart;

import com.cart.observer.CartObserver;
import com.cart.strategy.PricingStrategy;
import com.cart.strategy.StandardPricing;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> items = new ArrayList<>();

    // STRATEGY — runtime'da değiştirilebilir fiyatlandırma
    private PricingStrategy pricingStrategy = new StandardPricing();

    // OBSERVER — sepeti dinleyen sistemler
    private List<CartObserver> observers = new ArrayList<>();

    // Observer ekle
    public void addObserver(CartObserver observer) {
        observers.add(observer);
    }

    // Strategy değiştir — runtime'da bile çalışır
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void addItem(Product p) {
        items.add(p);
        System.out.println(p.name + " eklendi.");
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
        System.out.println("=== SEPET ===");
        for (Product p : items) {
            System.out.println(p.name + " x" + p.quantity
                    + " = " + (p.price * p.quantity) + " TL");
        }
        System.out.println("Toplam: " + calculateTotal() + " TL");
    }
}