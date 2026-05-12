# Faz 1 — AI Kullanım Günlüğü

## Claude'a Sorduğum Prompt
"Bu kodda Factory Method pattern uygulamak istiyorum.
ShoppingCart içindeki if-else indirim zincirini nasıl
ayrıştırabilirim? Hangi sınıfları oluşturmalıyım?"

## Claude'un Yanıtı (Özet)
- Discount adında bir interface oluştur
- Her indirim tipi bu interface'i implemente etsin
- DiscountFactory sınıfı hangi nesnenin üretileceğine karar versin
- ShoppingCart sadece Discount interface'ini kullansın, somut sınıfları bilmesin

## Ben Ne Uyguladım ve Neden?
Claude'un önerdiği yapıyı uyguladım ancak BuyXGetY indirimini
şimdilik eklemedim çünkü sepet mantığına erişmesi gerekiyor,
bunu Faz 2'de Decorator ile çözeceğim.
NoDiscount sınıfını ekledim — Claude bunu önermemişti ama
null kontrolü yazmamak için Null Object pattern mantığıyla ekledim.