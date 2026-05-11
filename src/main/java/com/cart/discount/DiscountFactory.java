package com.cart.discount;

// FACTORY — hangi indirim nesnesinin üretileceğine o karar verir
// ShoppingCart artık new PercentageDiscount() demek zorunda değil
public class DiscountFactory {

    public static Discount create(String type, double value) {
        switch (type) {
            case "PERCENTAGE":
                return new PercentageDiscount(value);
            case "FIXED":
                return new FixedDiscount(value);
            default:
                return new NoDiscount();
        }
    }
}