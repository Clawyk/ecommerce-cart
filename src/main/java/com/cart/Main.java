package com.cart;

import com.cart.facade.CheckoutFacade;
import com.cart.strategy.BulkPricing;
import com.cart.strategy.DiscountedPricing;
import com.cart.strategy.StandardPricing;

import java.util.Scanner;

/**
 * Kullanıcı girdili interaktif konsol uygulaması.
 * Kullanıcı ürün ekleyip çıkarabilir, strateji seçebilir ve ödeme yapabilir.
 */
public class Main {

    static ShoppingCart cart = new ShoppingCart();
    static CheckoutFacade checkout = new CheckoutFacade(cart);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("-----E-TİCARET SEPET SİSTEMİ-----");

        boolean devam = true;
        while (devam) {
            printMenu();
            String secim = scanner.nextLine().trim();

            switch (secim) {
                case "1":
                    urunEkle();
                    break;
                case "2":
                    urunCikar();
                    break;
                case "3":
                    cart.printCart();
                    break;
                case "4":
                    stratejiSec();
                    break;
                case "5":
                    odemeAl();
                    break;
                case "0":
                    System.out.println("Çıkılıyor...");
                    devam = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
        scanner.close();
    }

    static void printMenu() {
        System.out.println("  1. Ürün Ekle");
        System.out.println("  2. Ürün Çıkar");
        System.out.println("  3. Sepeti Görüntüle");
        System.out.println("  4. Fiyatlandırma Stratejisi Seç");
        System.out.println("  5. Ödeme Yap");
        System.out.println("  0. Çıkış");
        System.out.print("Seçiminiz: ");
    }

    static void urunEkle() {
        System.out.print("Ürün adı: ");
        String ad = scanner.nextLine().trim();

        System.out.print("Fiyat (TL): ");
        double fiyat;
        try {
            fiyat = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz fiyat!");
            return;
        }

        System.out.print("Adet: ");
        int adet;
        try {
            adet = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz adet!");
            return;
        }

        cart.addItem(new Product(ad, fiyat, adet));
    }

    static void urunCikar() {
        System.out.print("Çıkarılacak ürün adı: ");
        String ad = scanner.nextLine().trim();
        cart.removeItem(ad);
    }

    static void stratejiSec() {
        System.out.println("\nFiyatlandırma Stratejileri:");
        System.out.println("  1. Standart Fiyat");
        System.out.println("  2. Yüzde İndirim");
        System.out.println("  3. Toplu Alım (%15 indirim, 3+ ürün)");
        System.out.print("Seçiminiz: ");

        String secim = scanner.nextLine().trim();
        switch (secim) {
            case "1":
                cart.setPricingStrategy(new StandardPricing());
                System.out.println("Standart fiyatlandırma seçildi.");
                break;
            case "2":
                System.out.print("İndirim yüzdesi: %");
                try {
                    double yuzde = Double.parseDouble(scanner.nextLine().trim());
                    cart.setPricingStrategy(new DiscountedPricing(yuzde));
                    System.out.println("%" + yuzde + " indirimli fiyatlandırma seçildi.");
                } catch (NumberFormatException e) {
                    System.out.println("Geçersiz değer!");
                }
                break;
            case "3":
                cart.setPricingStrategy(new BulkPricing());
                System.out.println("Toplu alım fiyatlandırması seçildi.");
                break;
            default:
                System.out.println("Geçersiz seçim.");
        }
    }

    static void odemeAl() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Sepet boş, önce ürün ekleyin!");
            return;
        }
        System.out.println("\nÖdeme Yöntemleri:");
        System.out.println("  1. Kredi Kartı");
        System.out.println("  2. Nakit");
        System.out.println("  3. PayPal");
        System.out.print("Seçiminiz: ");

        String secim = scanner.nextLine().trim();
        String yontem;
        switch (secim) {
            case "1": yontem = "CREDIT_CARD"; break;
            case "2": yontem = "CASH"; break;
            case "3": yontem = "PAYPAL"; break;
            default:
                System.out.println("Geçersiz seçim.");
                return;
        }
        checkout.checkout(yontem);
    }
}