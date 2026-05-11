package com.cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    // Tüm ürünler burada
    public List<Product> items = new ArrayList<>();

    // İndirim tipi: "PERCENTAGE", "FIXED", "BUYXGETY", "NONE"
    public String discountType = "NONE";
    public double discountValue = 0;

    // Ürün ekle
    public void addItem(Product p) {
        items.add(p);
        System.out.println(p.name + " eklendi.");
    }

    // Ürün çıkar
    public void removeItem(String name) {
        items.removeIf(p -> p.name.equals(name));
        System.out.println(name + " çıkarıldı.");
    }

    // İndirim hesapla — tüm mantık burada, iç içe if-else
    public double calculateTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.price * p.quantity;
        }

        // İNDİRİM HESAPLAMA — if-else zinciri (KÖTÜ TASARIM)
        if (discountType.equals("PERCENTAGE")) {
            total = total - (total * discountValue / 100);
            System.out.println("Yüzde indirim uygulandı: %" + discountValue);

        } else if (discountType.equals("FIXED")) {
            total = total - discountValue;
            System.out.println("Sabit indirim uygulandı: " + discountValue + " TL");

        } else if (discountType.equals("BUYXGETY")) {
            // 3 alana 1 bedava mantığı (sadece ilk ürün için)
            if (!items.isEmpty()) {
                int qty = items.get(0).quantity;
                int freeItems = qty / 3;
                total = total - (freeItems * items.get(0).price);
                System.out.println("3 alana 1 bedava uygulandı.");
            }
        } else {
            System.out.println("İndirim yok.");
        }

        return total;
    }

    // Ödeme al — burada da her şey iç içe (KÖTÜ TASARIM)
    public void checkout(String paymentMethod) {
        double total = calculateTotal();

        if (paymentMethod.equals("CREDIT_CARD")) {
            System.out.println("Kredi kartı ile " + total + " TL ödendi.");
            // e-posta gönder
            System.out.println("E-posta bildirimi gönderildi.");
            // stok güncelle
            for (Product p : items) {
                System.out.println(p.name + " stoğu güncellendi.");
            }

        } else if (paymentMethod.equals("CASH")) {
            System.out.println("Nakit ile " + total + " TL ödendi.");
            System.out.println("E-posta bildirimi gönderildi.");
            for (Product p : items) {
                System.out.println(p.name + " stoğu güncellendi.");
            }

        } else if (paymentMethod.equals("PAYPAL")) {
            System.out.println("PayPal ile " + total + " TL ödendi.");
            System.out.println("E-posta bildirimi gönderildi.");
            for (Product p : items) {
                System.out.println(p.name + " stoğu güncellendi.");
            }
        }

        // Sepeti temizle
        items.clear();
        discountType = "NONE";
    }

    // Sepeti yazdır
    public void printCart() {
        System.out.println("=== SEPET ===");
        for (Product p : items) {
            System.out.println(p.name + " x" + p.quantity + " = " + (p.price * p.quantity) + " TL");
        }
        System.out.println("Toplam: " + calculateTotal() + " TL");
    }
}