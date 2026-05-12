package com.cart;

import com.cart.decorator.CartDecorator;
import com.cart.discount.Discount;
import com.cart.discount.DiscountFactory;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> items = new ArrayList<>();
    private Discount discount = DiscountFactory.create("NONE", 0);

    // Decorator listesi — her katman sırayla çalışır
    private List<CartDecorator> decorators = new ArrayList<>();

    public void addDecorator(CartDecorator decorator) {
        decorators.add(decorator);
    }

    public void addItem(Product p) {
        items.add(p);
        System.out.println(p.name + " eklendi.");
        // Tüm decorator'ları bilgilendir
        for (CartDecorator d : decorators) {
            d.onItemAdded(p);
        }
    }

    public void removeItem(String name) {
        items.removeIf(p -> p.name.equals(name));
        System.out.println(name + " çıkarıldı.");
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.price * p.quantity;
        }
        double discounted = discount.apply(total);
        System.out.println(discount.getDescription());
        return discounted;
    }

    public void setDiscount(String type, double value) {
        this.discount = DiscountFactory.create(type, value);
    }

    public void checkout(String paymentMethod) {
        double total = calculateTotal();
        System.out.println(paymentMethod + " ile " + total + " TL ödendi.");
        // Tüm decorator'ları bilgilendir
        for (CartDecorator d : decorators) {
            d.onCheckout(total);
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