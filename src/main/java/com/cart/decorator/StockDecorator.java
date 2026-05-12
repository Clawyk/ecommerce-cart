package com.cart.decorator;

import com.cart.Product;

// Stok katmanı — ödeme sonrası stok günceller
public class StockDecorator implements CartDecorator {

    @Override
    public void onItemAdded(Product p) {
        // Stok ürün eklenince değil, satın alınınca düşer
    }

    @Override
    public void onCheckout(double total) {
        System.out.println("[STOK] Stok güncellendi. Toplam satış: " + total + " TL");
    }
}