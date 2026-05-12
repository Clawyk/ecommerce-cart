package com.cart.observer;

import com.cart.Product;

// Analitik sistemi — her hareketi kaydeder
public class AnalyticsObserver implements CartObserver {

    @Override
    public void onItemAdded(Product p) {
        System.out.println("[ANALİTİK] Ürün görüntüleme kaydedildi: " + p.name);
    }

    @Override
    public void onItemRemoved(String productName) {
        System.out.println("[ANALİTİK] Sepet terk verisi kaydedildi: " + productName);
    }

    @Override
    public void onCheckoutCompleted(double total) {
        System.out.println("[ANALİTİK] Satış verisi kaydedildi: " + total + " TL");
    }
}