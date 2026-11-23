# Airlines

Учебный REST API для управления авиарейсами и бронированиями авиабилетов, разработанный на **Java 25, Spring Boot 4.x и Gradle**.

## Тема проекта

Система управления авиарейсами, пользователями и бронированиями авиабилетов.

## Сущности

### User — пользователь

| Поле | Тип Java | Тип SQL | Описание |
|---|---|---|---|
| id | Long | BIGINT | Первичный ключ |
| fullName | String | VARCHAR(255) | ФИО пользователя |
| email | String | VARCHAR(255) | Уникальный email |
| passwordHash | String | VARCHAR(255) | Хеш пароля |
| role | UserRole | ENUM | PASSENGER / ADMIN |

### Flight — рейс

| Поле | Тип Java | Тип SQL | Описание |
|---|---|---|---|
| id | Long | BIGINT | Первичный ключ |
| flightNumber | String | VARCHAR(10) | Номер рейса |
| departureIcao | String | CHAR(4) | ICAO аэропорта вылета |
| arrivalIcao | String | CHAR(4) | ICAO аэропорта прибытия |
| departureTime | Instant | TIMESTAMPTZ | Дата и время вылета (UTC) |
| arrivalTime | Instant | TIMESTAMPTZ | Дата и время прибытия (UTC) |
| capacity | Integer | INTEGER | Количество мест |

### Booking — бронирование

| Поле | Тип Java | Тип SQL | Описание |
|---|---|---|---|
| id | Long | BIGINT | Первичный ключ |
| userId | Long | BIGINT | FK → User |
| flightId | Long | BIGINT | FK → Flight |
| seatNumber | String | VARCHAR(5) | Номер места |
| serviceClass | ServiceClass | ENUM | Класс обслуживания |
| price | BigDecimal | DECIMAL(10,2) | Стоимость билета |
| status | BookingStatus | ENUM | Статус бронирования |
| bookedAt | Instant | TIMESTAMPTZ | Дата бронирования (UTC) |

**Классы обслуживания:** `ECONOMY`, `PREMIUM_ECONOMY`, `BUSINESS`, `FIRST`.

**Статусы бронирования:** `PENDING`, `CONFIRMED`, `CANCELLED`.

Стоимость билета хранится в `Booking`, поскольку зависит от класса обслуживания и условий покупки на момент бронирования.

### Связи между сущностями

- **User → Booking (1:N)** — пользователь может иметь несколько бронирований.
- **Flight → Booking (1:N)** — на один рейс может быть оформлено несколько бронирований.
- **User ↔ Flight (M:N)** — связь реализована через `Booking`.

Типы SQL приведены для проектируемой базы данных PostgreSQL. В рамках ЛР1 данные хранятся в оперативной памяти с использованием `ConcurrentHashMap`, а идентификаторы генерируются через `AtomicLong`.

## Запуск проекта

**Требования:** JDK 25.

Для сборки используется Gradle Wrapper, поэтому отдельная установка Gradle не требуется.

### Windows

```powershell
.\gradlew.bat bootRun
```

### Linux / macOS

```bash
./gradlew bootRun
```

После запуска приложение доступно по адресу:

`http://localhost:8080`

### Проверка REST API

Получить список рейсов:

```http
GET http://localhost:8080/api/v1/flights
```

Получить рейс по идентификатору:

```http
GET http://localhost:8080/api/v1/flights/1
```

При запуске приложения автоматически создаются пять тестовых рейсов. Данные хранятся в оперативной памяти и сбрасываются при перезапуске.