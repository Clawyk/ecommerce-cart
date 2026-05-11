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