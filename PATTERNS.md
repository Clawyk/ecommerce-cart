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