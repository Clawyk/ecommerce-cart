package com.cart.observer;

import com.cart.Product;

// Bildirim sistemi — sadece ödeme tamamlanınca bildirim gönderir
public class NotificationObserver implements CartObserver {

    @Override
    public void onItemAdded(Product p) {
        // Bildirim gönderilmez
    }

    @Override
    public void onItemRemoved(String productName) {
        // Bildirim gönderilmez
    }

    @Override
    public void onCheckoutCompleted(double total) {
        System.out.println("[BİLDİRİM] Siparişiniz alındı! Tutar: " + total + " TL");
    }
}