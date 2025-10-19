# Product Service - Infrastructure, Application ve Web Katmanları Ödevi

## 🎯 Hedef

Product Service'in domain katmanı hazır durumda. Bu ödevde **Infrastructure**, **Application** ve **Web** katmanlarını Onion Architecture prensiplerine uygun şekilde kodlayacaksınız.

## 📋 Ödev Gereksinimleri

### 1. CRUD İşlemleri

Aşağıdaki 5 temel CRUD işlemini implement edeceksiniz:

- **GET** `/api/v1/products` - Tüm ürünleri listele
- **GET** `/api/v1/products/{id}` - ID'ye göre ürün getir
- **POST** `/api/v1/products` - Yeni ürün oluştur
- **PUT** `/api/v1/products/{id}` - Ürün güncelle
- **DELETE** `/api/v1/products/{id}` - Ürün sil

### 2. Onion Architecture Katmanları

#### 🏗️ Infrastructure Katmanı

**Konum:** `src/main/java/com/turkcell/product_service/infrastructure/`

**Görevler:**

1. **Repository Implementation**

   - `ProductRepositoryImpl` sınıfı oluşturun
   - `ProductRepository` interface'ini implement edin
   - In-memory veri saklama (Map kullanarak)
   - Tüm repository metodlarını implement edin

2. **Database Entities (JPA)**

   - `ProductEntity` sınıfı oluşturun
   - Domain entity'den JPA entity'ye dönüşüm
   - `@Entity`, `@Table`, `@Id` anotasyonları kullanın

3. **Mappers**
   - `ProductMapper` sınıfı oluşturun
   - Domain ↔ Infrastructure dönüşümleri
   - Static metodlar kullanın

#### 🎯 Application Katmanı

**Konum:** `src/main/java/com/turkcell/product_service/application/`

**Görevler:**

1. **Use Cases / Services**

   - `CreateProductUseCase`
   - `GetProductByIdUseCase`
   - `GetAllProductsUseCase`
   - `UpdateProductUseCase`
   - `DeleteProductUseCase`

2. **DTOs**

   - `CreateProductRequest`
   - `UpdateProductRequest`
   - `ProductResponse`
   - `ProductListResponse`

3. **Ports (Interfaces)**
   - `ProductServicePort` (Application Service Interface)
   - `ProductRepositoryPort` (Repository Interface - Domain'deki interface'i kullanabilirsiniz)

#### 🌐 Web Katmanı

**Konum:** `src/main/java/com/turkcell/product_service/web/`

**Görevler:**

1. **Controllers**

   - `ProductController` sınıfını güncelleyin
   - RESTful API endpoint'leri
   - Request/Response DTO'ları kullanın
   - Exception handling ekleyin

2. **DTOs**

   - Request DTO'ları
   - Response DTO'ları
   - Validation anotasyonları

3. **Exception Handlers**
   - `GlobalExceptionHandler`
   - Custom exception'lar
   - HTTP status kodları

## 📁 Klasör Yapısı

```
src/main/java/com/turkcell/product_service/
├── domain/ (✅ Hazır)
│   ├── entities/
│   ├── valueobjects/
│   └── repositories/
├── application/
│   ├── usecases/
│   ├── dtos/
│   └── ports/
├── infrastructure/
│   ├── repositories/
│   ├── entities/
│   └── mappers/
└── web/
    ├── controllers/
    ├── dtos/
    └── exceptions/
```

## 🔧 Teknik Gereksinimler

### Dependencies (pom.xml'e ekleyin)

```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Spring Boot Data JPA (opsiyonel - gelecekte database için) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
```

### Validation Kuralları

- Ürün adı: Boş olamaz, minimum 2 karakter
- Açıklama: Boş olamaz, minimum 10 karakter
- Fiyat: Pozitif olmalı
- Stok: Negatif olamaz
- Para birimi: Desteklenen para birimlerinden biri olmalı

## 📝 Örnek API Kullanımı

### 1. Ürün Oluşturma

```http
POST /api/v1/products
Content-Type: application/json

{
    "name": "iPhone 15",
    "description": "Apple'ın en yeni akıllı telefonu",
    "price": {
        "amount": 45000.00,
        "currency": "TRY"
    },
    "stock": {
        "quantity": 100
    }
}
```

### 2. Ürün Getirme

```http
GET /api/v1/products/{id}
```

### 3. Tüm Ürünleri Listeleme

```http
GET /api/v1/products
```

### 4. Ürün Güncelleme

```http
PUT /api/v1/products/{id}
Content-Type: application/json

{
    "name": "iPhone 15 Pro",
    "description": "Güncellenmiş açıklama"
}
```

### 5. Ürün Silme

```http
DELETE /api/v1/products/{id}
```

## ✅ Başarı Kriterleri

1. **Onion Architecture** prensiplerine uygun katman yapısı
2. **Dependency Inversion** - Katmanlar arası bağımlılık yönü doğru
3. **Clean Code** - Okunabilir, anlaşılır kod
4. **Error Handling** - Uygun exception handling
5. **Validation** - Input validation'ları
6. **RESTful API** - HTTP standartlarına uygun endpoint'ler
7. **DTO Pattern** - Request/Response DTO'ları kullanımı

## 🚀 Test Senaryoları

Ödevi tamamladıktan sonra aşağıdaki test senaryolarını çalıştırın:

1. ✅ Yeni ürün oluşturma
2. ✅ Oluşturulan ürünü ID ile getirme
3. ✅ Tüm ürünleri listeleme
4. ✅ Ürün bilgilerini güncelleme
5. ✅ Ürün silme
6. ✅ Geçersiz veri ile ürün oluşturma (validation test)
7. ✅ Var olmayan ürün ID'si ile işlem yapma (error handling test)

## 📚 Faydalı Kaynaklar

- [Onion Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Spring Boot REST API](https://spring.io/guides/gs/rest-service/)
- [Bean Validation](https://spring.io/guides/gs/validating-form-input/)
- [Exception Handling](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)

**Başarılar! 🎉**
