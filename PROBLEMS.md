# Başlangıç Kodundaki Sorunlar

## Sorun 1 — God Class (Tanrı Sınıfı)
`ShoppingCart` sınıfı çok fazla sorumluluk üstleniyor:
ürün yönetimi, indirim hesaplama, ödeme alma, bildirim gönderme
hepsi tek sınıfta. Bu Single Responsibility Principle (SRP) ihlali.

## Sorun 2 — if-else İndirim Zinciri
Yeni bir indirim tipi eklemek için `calculateTotal()` metodunu
açıp değiştirmek gerekiyor. Bu Open/Closed Principle (OCP) ihlali.
Her değişiklik mevcut çalışan kodu bozabilir.

## Sorun 3 — Ödeme Mantığının Tekrarlanması
`checkout()` metodunda her ödeme tipi için aynı kodlar
(e-posta gönder, stok güncelle) tekrar tekrar yazılmış. DRY ihlali.

## Sorun 4 — Nesne Yaratma Kontrolsüz
`Product` nesneleri doğrudan `new Product(...)` ile oluşturuluyor.
Farklı ürün tipleri (dijital ürün, fiziksel ürün) eklemek
mevcut kodu değiştirmeyi gerektiriyor.

## Sorun 5 — Bağımlılıklar Sabit Kodlanmış
Ödeme yöntemi ve indirim tipi String olarak hardcode edilmiş.
Runtime'da dinamik olarak değiştirilemez, test edilmesi zor.

## Sorun 6 — Sepet Değişikliklerinde Bildirim Yok
Ürün eklendiğinde veya çıkarıldığında başka sistemleri
(loglama, stok takibi) otomatik haberdar eden bir mekanizma yok.

---

## 🤖 AI (Claude) ile Karşılaştırma

**Claude'a sorduğum prompt:**
"Bu kodda hangi tasarım sorunlarını görüyorsun? Hangi tasarım örüntüleri
bu sorunları çözebilir? Her sorun için kısa bir açıklama yaz."

**Claude'un tespit ettikleri:**
- God Class → Factory Method + Facade ile çözülebilir
- if-else zinciri → Strategy Pattern ile çözülebilir
- Tekrar eden ödeme kodu → Template Method veya Facade
- Kontrolsüz nesne yaratma → Factory Method
- Observer eksikliği → Observer Pattern

**Kendi tespitlerimle fark:**
Sorun 5 (hardcode String bağımlılıkları) Claude'un listesinde
ayrıca vurgulanmamıştı. Bunu ben fark ettim.
Claude ek olarak "test edilebilirlik" sorununu belirtti, ben atlamıştım.
