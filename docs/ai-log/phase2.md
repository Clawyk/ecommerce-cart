# Faz 2 — AI Kullanım Günlüğü

## Claude'a Sorduğum Prompt
"checkout() metodundaki e-posta ve stok güncelleme kodları
tekrar ediyordu. Decorator Pattern ile bunları nasıl ayırırım?
Facade ne zaman kullanılmalı, Decorator'dan farkı ne?"

## Claude'un Yanıtı (Özet)
- CartDecorator interface'i oluştur
- Her sorumluluk (log, email, stok) ayrı decorator sınıfı olsun
- ShoppingCart bir decorator listesi tutsun
- Facade ise bu karmaşık yapıyı dışarıdan gizlesin,
  tek bir checkout() çağrısıyla her şeyi başlatsın

## Ben Ne Uyguladım ve Neden?
Önerilen yapıyı uyguladım. onItemAdded() metodunu ekledim
çünkü loglama sadece ödeme değil ürün eklenirken de çalışmalıydı.
EmailDecorator'da onItemAdded()'ı boş bıraktım çünkü
e-posta sadece ödeme sonrası gönderilmeli.

## Decorator vs Facade Farkı (Öğrendiğim)
Decorator → var olan nesneye davranış ekler, üst üste katmanlanır.
Facade → karmaşık sistemi basit bir arayüzün arkasına saklar.
İkisi birlikte kullanılabilir, birbirini dışlamaz.