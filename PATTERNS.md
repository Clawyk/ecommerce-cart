# Kullanılan Tasarım Örüntüleri

## FAZ 1 — Factory Method

### Nerede Uygulandı?
`com.cart.discount.DiscountFactory` sınıfı

### Neden Bu Örüntü?
ShoppingCart içinde if-else ile indirim nesnesi oluşturuluyordu.
Yeni indirim tipi eklemek mevcut kodu kırıyordu (OCP ihlali).

### Ne Kazandık?
- Yeni indirim tipi eklemek için ShoppingCart'a dokunmuyoruz
- Her indirim kendi sınıfında, test edilebilir
- Nesne yaratma sorumluluğu tek bir yerde toplandı


## FAZ 2 — Decorator Pattern

### Nerede Uygulandı?
`com.cart.decorator` paketi — LoggingDecorator, EmailDecorator, StockDecorator

### Neden Bu Örüntü?
checkout() içinde bildirimler hardcode yazılıydı, tekrar ediyordu.
Yeni bildirim eklemek mevcut kodu değiştirmeyi gerektiriyordu.

### Ne Kazandık?
- Her sorumluluk kendi sınıfında
- Yeni decorator eklemek tek satır: addDecorator(new XxxDecorator())
- Mevcut kod hiç değişmedi

## FAZ 2 — Facade Pattern

### Nerede Uygulandı?
`com.cart.facade.CheckoutFacade`

### Neden Bu Örüntü?
Checkout süreci dışarıdan karmaşık görünüyordu.
Decorator bağlantıları, ödeme çağrısı hep ayrı ayrı yapılıyordu.

### Ne Kazandık?
- Dışarıdan tek satır: checkout.checkout("CREDIT_CARD")
- İç karmaşıklık gizlendi
- Decorator'lar merkezi olarak Facade içinde bağlandı

## FAZ 3 — Strategy Pattern

### Nerede Uygulandı?
`com.cart.strategy` — StandardPricing, DiscountedPricing, BulkPricing

### Neden Bu Örüntü?
Fiyatlandırma algoritması runtime'da değişmesi gerekiyordu.
if-else ile yapılsaydı her yeni algoritma mevcut kodu bozardı.

### Ne Kazandık?
- Runtime'da algoritma değişimi: setPricingStrategy()
- Her algoritma kendi sınıfında, test edilebilir
- OCP sağlandı: yeni algoritma için mevcut koda dokunulmadı

## FAZ 3 — Observer Pattern

### Nerede Uygulandı?
`com.cart.observer` — InventoryObserver, NotificationObserver, AnalyticsObserver

### Neden Bu Örüntü?
Sepet değişikliklerinde stok, bildirim, analitik sistemleri
haberdar edilmesi gerekiyordu. ShoppingCart bu sistemleri
doğrudan çağırsaydı sıkı bağımlılık oluşurdu.

### Ne Kazandık?
- ShoppingCart observer'ları tanımıyor, sadece bildirim yapıyor
- Yeni sistem eklemek: addObserver(new XxxObserver())
- Sistemler birbirinden bağımsız