# E-Ticaret Sepeti — Tasarım Örüntüleri Ödevi

## Proje Hakkında
Java ile geliştirilmiş e-ticaret sepeti uygulaması.
Yazılım Tasarım Örüntüleri dersi kapsamında 5 farklı
örüntü uygulanmıştır.

## Kullanılan Tasarım Örüntüleri

| Faz | Örüntü | Sınıf |
|-----|--------|-------|
| 1 | Factory Method | DiscountFactory |
| 2 | Decorator | LoggingDecorator, EmailDecorator, StockDecorator |
| 2 | Facade | CheckoutFacade |
| 3 | Strategy | StandardPricing, DiscountedPricing, BulkPricing |
| 3 | Observer | InventoryObserver, NotificationObserver, AnalyticsObserver |

## Proje Yapısı
src/main/java/com/cart/
├── Product.java
├── ShoppingCart.java
├── Main.java
├── discount/         → Factory Method
├── decorator/        → Decorator Pattern
├── facade/           → Facade Pattern
├── strategy/         → Strategy Pattern
└── observer/         → Observer Pattern

## Nasıl Çalıştırılır?
Main.java dosyasını IntelliJ IDEA ile çalıştırın.

## AI Araçlarının Kullanımı
Her faz için `docs/ai-log/` klasöründe detaylı kullanım günlüğü bulunmaktadır.

## Yapay Zeka Araçları
- **Claude (Anthropic):** Kod tasarımı ve örüntü seçimi
- **GitHub:** Versiyon kontrolü, issue takibi, pull request yönetimi
