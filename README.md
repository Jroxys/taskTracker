Task Tracker API – Spring Boot

Bu proje, görev yönetimi amacıyla oluşturulmuş bir backend API sistemidir. Kullanıcılar JWT ile giriş yapar, görev oluşturur, günceller, siler ve listeler. Her kullanıcı sadece kendi görevlerine erişebilir. Projede rol tabanlı yetkilendirme, istisna yönetimi ve veri doğrulama uygulanmıştır.

-Kullanılan Teknolojiler

Java 17
Spring Boot
Spring Security + JWT
Hibernate & JPA
PostgreSQL
Lombok
MapStruct
Model Mapper
Validation (javax & custom)
Global Exception Handling
Swagger / OpenAPI (varsa)

Özellikler

JWT tabanlı kullanıcı doğrulama ve rol yönetimi (Gelecek)
CRUD işlemleri: Görev ekleme, silme, güncelleme, listeleme
Validasyon: Hem backend hem DTO seviyesinde
Exception Handling: Detaylı hata mesajları ve özel Exception sınıfları
Katmanlı mimari (Controller - Service - Repository - Entity - DTO)