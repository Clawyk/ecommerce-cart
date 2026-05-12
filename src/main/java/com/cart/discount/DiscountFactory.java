package com.cart.discount;

/**
 * Factory Method Pattern uygulaması.
 *
 * Problem: ShoppingCart içinde if-else ile indirim nesnesi üretiliyordu.
 * Yeni indirim tipi eklemek için ShoppingCart'ı açmak gerekiyordu.
 * Bu Open/Closed Principle'ı ihlal ediyordu.
 *
 * Çözüm: Nesne üretme sorumluluğu bu Factory'e verildi.
 * Yeni indirim tipi eklemek için sadece bu sınıfa 1 satır eklenir,
 * ShoppingCart'a hiç dokunulmaz.
 */
public class DiscountFactory {

    public static Discount create(String type, double value) {
        switch (type) {
            case "PERCENTAGE":
                return new PercentageDiscount(value);
            case "FIXED":
                return new FixedDiscount(value);
            default:
                // Null yerine NoDiscount döndürüyoruz — NullPointerException riski yok
                return new NoDiscount();
        }
    }
}