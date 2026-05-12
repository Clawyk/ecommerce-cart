# Faz 3 — AI Kullanım Günlüğü

## Claude'a Sorduğum Prompt
"Fiyatlandırma algoritmasını runtime'da değiştirmek istiyorum.
Strategy ve Observer pattern'i birlikte nasıl kullanabilirim?"

## Claude'un Yanıtı (Özet)
- PricingStrategy interface'i oluştur, her algoritma ayrı sınıf olsun
- ShoppingCart strategy'yi dışarıdan alabilmeli (setPricingStrategy)
- Observer için CartObserver interface'i, ShoppingCart observer listesi tutsun
- Her olay (addItem, removeItem, checkout) observer'ları tetiklesin

## Ben Ne Uyguladım?
Önerilen yapıyı uyguladım. BulkPricing sınıfını ekledim,
Claude önermemişti ama toplu alım senaryosu gerçekçi göründü.
AnalyticsObserver'ı ekledim, gerçek e-ticaret sistemlerinde
analitik takibi kritik olduğu için.

## Strategy vs Decorator Farkı (Öğrendiğim)
Strategy → algoritmayı tamamen değiştirir (ya bu ya o)
Decorator → mevcut davranışa bir şey ekler (üst üste katman)