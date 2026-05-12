package com.cart.decorator;

import com.cart.Product;

// E-posta katmanı — sadece ödeme sonrası bildirim gönderir
public class EmailDecorator implements CartDecorator {

    @Override
    public void onItemAdded(Product p) {
        // E-posta ürün eklenince gönderilmez, bir şey yapmıyoruz
    }

    @Override
    public void onCheckout(double total) {
        System.out.println("[EMAIL] Sipariş onayı gönderildi. Tutar: " + total + " TL");
    }
}