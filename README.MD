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

## REST API

Приложение предоставляет REST API для управления пользователями, рейсами и бронированиями авиабилетов. Обмен данными между клиентом и сервером осуществляется в формате JSON.

Базовый URL: `http://localhost:8080/api/v1`

### Документация API

Документация API автоматически генерируется с помощью Springdoc OpenAPI.

- [Swagger UI](http://localhost:8080/swagger-ui/index.html)
- [OpenAPI JSON](http://localhost:8080/v3/api-docs)
- [OpenAPI YAML](http://localhost:8080/v3/api-docs.yaml)

### Эндпоинты

#### Пользователи (Users)

| Метод | Эндпоинт | Описание |
|---|---|---|
| `GET` | `/api/v1/users` | Получение списка пользователей |
| `GET` | `/api/v1/users/{id}` | Получение пользователя по ID |
| `POST` | `/api/v1/users` | Создание пользователя |
| `PATCH` | `/api/v1/users/{id}` | Частичное обновление пользователя |
| `DELETE` | `/api/v1/users/{id}` | Удаление пользователя |

#### Рейсы (Flights)

| Метод | Эндпоинт | Описание |
|---|---|---|
| `GET` | `/api/v1/flights` | Получение списка рейсов |
| `GET` | `/api/v1/flights/{id}` | Получение рейса по ID |
| `POST` | `/api/v1/flights` | Создание рейса |
| `PATCH` | `/api/v1/flights/{id}` | Частичное обновление рейса |
| `DELETE` | `/api/v1/flights/{id}` | Удаление рейса |

#### Бронирования (Bookings)

| Метод | Эндпоинт | Описание |
|---|---|---|
| `GET` | `/api/v1/bookings` | Получение списка бронирований |
| `GET` | `/api/v1/bookings/{id}` | Получение бронирования по ID |
| `POST` | `/api/v1/bookings` | Создание бронирования |
| `PATCH` | `/api/v1/bookings/{id}` | Частичное обновление бронирования |
| `DELETE` | `/api/v1/bookings/{id}` | Удаление бронирования |

### HTTP-статусы

| Статус | Описание |
|---|---|
| `200 OK` | Успешное получение или обновление ресурса |
| `201 Created` | Ресурс успешно создан |
| `204 No Content` | Ресурс успешно удалён |
| `400 Bad Request` | Некорректные входные данные |
| `404 Not Found` | Запрашиваемый ресурс не найден |
| `409 Conflict` | Нарушение бизнес-правила |

При создании ресурса сервер возвращает статус `201 Created`, тело созданного ресурса и заголовок `Location`, содержащий URI созданного ресурса.

При успешном удалении сервер возвращает статус `204 No Content` без тела ответа.

### DTO и валидация

Для передачи данных между клиентом и сервером используются DTO (Data Transfer Object), реализованные с помощью Java `record`.

Для каждой сущности предусмотрены три типа DTO:

- `CreateRequest` - создание ресурса.
- `UpdateRequest` - частичное обновление ресурса.
- `Response` - представление ресурса в ответе сервера.

Преобразование доменных сущностей в DTO и обратно выполняется мапперами. Доменные сущности не передаются напрямую через REST API.

Валидация входных данных осуществляется с помощью Jakarta Bean Validation и аннотации `@Valid` в контроллерах.

| Поле | Ограничение |
|---|---|
| `fullName` | Непустая строка, не более 255 символов |
| `email` | Корректный формат email, не более 255 символов |
| `password` | От 8 до 128 символов |
| `flightNumber` | Непустая строка, не более 10 символов |
| `departureICAO` | Четыре заглавные латинские буквы |
| `arrivalICAO` | Четыре заглавные латинские буквы |
| `capacity` | Положительное целое число |
| `userId` | Положительное целое число |
| `flightId` | Положительное целое число |
| `seatNumber` | Непустая строка, не более 5 символов |
| `price` | Положительное число |

При нарушении ограничений валидации сервер возвращает статус `400 Bad Request`.

### Перечисления

| Перечисление | Допустимые значения |
|---|---|
| `UserRole` | `PASSENGER`, `ADMIN` |
| `ServiceClass` | `ECONOMY`, `BUSINESS`, `FIRST` |
| `BookingStatus` | `PENDING`, `CONFIRMED`, `CANCELLED` |

### Бизнес-правила

Бизнес-логика реализуется на уровне сервисного слоя.

1. **Наличие свободных мест.** Бронирование допускается только при наличии свободных мест на рейсе. При отсутствии свободных мест выбрасывается исключение `NotEnoughSeats`.
2. **Временное ограничение бронирования.** Бронирование доступно до момента отправления рейса (`departureTime`). Начиная с момента отправления создание бронирования запрещено. При нарушении данного правила выбрасывается исключение `TooLateBooking`.

При нарушении бизнес-правил сервер возвращает статус `409 Conflict` и описание ошибки в формате `ProblemDetail`.

| Исключение | Условие возникновения | HTTP-статус |
|---|---|---|
| `NotFoundException` | Запрашиваемая сущность не существует | 404 |
| `NotEnoughSeats` | На рейсе отсутствуют свободные места | 409 |
| `TooLateBooking` | Время отправления рейса наступило или уже прошло | 409 |


### Обработка ошибок

Для централизованной обработки исключений используется класс `GlobalExceptionHandler` с аннотацией `@RestControllerAdvice`.

Ошибки представляются в стандартизированном формате Problem Details (RFC 9457) с использованием класса `ProblemDetail`.

| Исключение | HTTP-статус | Заголовок ошибки |
|---|---|---|
| `NotFoundException` | `404 Not Found` | Not Found |
| `NotEnoughSeats` | `409 Conflict` | Not Enough Seats |
| `TooLateBooking` | `409 Conflict` | Too Late to Book |

Сервисный слой не зависит от HTTP-статусов и формата HTTP-ответов. Преобразование исключений в HTTP-ответы осуществляется глобальным обработчиком.

Пример ответа при обращении к несуществующему ресурсу:

```json
{
    "type": "about:blank",
    "title": "Not Found",
    "status": 404,
    "detail": "User 42 not found",
    "instance": "/api/v1/users/42"
}
```

Пример ответа при отсутствии свободных мест на рейсе:

```json
{
    "type": "about:blank",
    "title": "Not Enough Seats",
    "status": 409,
    "detail": "Not enough seats on the flight",
    "instance": "/api/v1/bookings"
}
```

### Примеры запросов

#### Создание пользователя

`POST /api/v1/users`

```json
{
    "fullName": "Иван Иванов",
    "email": "ivan@example.com",
    "password": "SecurePassword123"
}
```

Пример ответа:

```http
HTTP/1.1 201 Created
Location: /api/v1/users/1
Content-Type: application/json
```

```json
{
    "id": 1,
    "fullName": "Иван Иванов",
    "email": "ivan@example.com",
    "role": "PASSENGER"
}
```

#### Обновление пользователя

`PATCH /api/v1/users/1`

```json
{
    "email": "new@example.com"
}
```

#### Создание рейса

`POST /api/v1/flights`

```json
{
    "flightNumber": "SU1701",
    "departureICAO": "UHWW",
    "arrivalICAO": "UUEE",
    "departureTime": "2027-06-01T10:00:00Z",
    "arrivalTime": "2027-06-01T18:30:00Z",
    "capacity": 180
}
```

#### Обновление рейса

`PATCH /api/v1/flights/1`

```json
{
    "capacity": 200
}
```

#### Создание бронирования

`POST /api/v1/bookings`

```json
{
    "userId": 1,
    "flightId": 1,
    "seatNumber": "12A",
    "serviceClass": "ECONOMY",
    "price": 25000.00
}
```

Пример ответа:

```json
{
    "id": 1,
    "userId": 1,
    "flightId": 1,
    "seatNumber": "12A",
    "serviceClass": "ECONOMY",
    "price": 25000.00,
    "status": "PENDING",
    "bookedAt": "2026-11-26T10:00:00Z"
}
```

#### Подтверждение бронирования

`PATCH /api/v1/bookings/1`

```json
{
    "status": "CONFIRMED"
}
```

#### Отмена бронирования

`PATCH /api/v1/bookings/1`

```json
{
    "status": "CANCELLED"
}
```

#### Удаление бронирования

`DELETE /api/v1/bookings/1`

Ответ:

```http
HTTP/1.1 204 No Content
```

### Особенности реализации

- API реализован в соответствии с принципами REST.
- Имена ресурсов представлены существительными во множественном числе.
- Версия API указывается в URL (`/api/v1`).
- Частичное обновление ресурсов осуществляется методом `PATCH`.
- Контроллеры отвечают за взаимодействие по HTTP, сервисы - за бизнес-логику, репозитории - за хранение данных.
- Для переиспользования общих операций используются абстрактные классы и Java Generics.
- Зависимости внедряются через конструкторы.
- Пароли хешируются на стороне сервера. Пароли и их хеши не возвращаются в ответах API.
- Аэропорты обозначаются кодами ICAO.
- Дата и время представлены типом `Instant` и передаются в формате ISO 8601.
- Стоимость билетов представлена типом `BigDecimal`.
- Начальный статус бронирования и время его создания устанавливаются доменной сущностью.
- Хранение данных осуществляется в оперативной памяти с использованием `ConcurrentHashMap`.
- Генерация идентификаторов осуществляется с использованием `AtomicLong`.
- Конфигурация приложения хранится в `application.yml`.
