package com.cart.observer;

import com.cart.Product;

// Stok takip sistemi — sepet değişince stoku günceller
public class InventoryObserver implements CartObserver {

    @Override
    public void onItemAdded(Product p) {
        System.out.println("[STOK] " + p.name + " sepete eklendi, stok rezerve edildi.");
    }

    @Override
    public void onItemRemoved(String productName) {
        System.out.println("[STOK] " + productName + " sepetten çıkarıldı, rezerv iade edildi.");
    }

    @Override
    public void onCheckoutCompleted(double total) {
        System.out.println("[STOK] Satış tamamlandı, stok kalıcı olarak düşüldü.");
    }
}