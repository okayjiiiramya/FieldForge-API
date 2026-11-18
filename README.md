# 🧱 FieldForge API

**Define your own fields, then store anything.** FieldForge is a Spring Boot REST API for building flexible catalogs: create a category, define its custom attributes (for example *Brand* and *RAM* for "Laptops"), then add items whose values are tied to those attributes. No schema changes needed when your data model changes.

Built with **Spring Boot, Spring Data JPA, Bean Validation, ModelMapper and H2**.

---

## ✨ Features

- 🗂️ **Dynamic categories:** create categories with their own custom attributes in one request
- 🏷️ **Flexible item metadata:** each item stores a value for each attribute (entity-attribute-value style model)
- ✏️ **Create, list and update items** within a category
- ✅ **Request validation:** required fields are checked and returned as readable error messages
- 🚨 **Consistent error responses:** one JSON error format with status, timestamp and messages
- 🔗 **REST conventions:** `201 Created` with a `Location` header on creation
- ⚡ **No N+1 queries:** entity graphs load attributes and values efficiently
- 🧪 **Zero-setup database:** in-memory H2 works out of the box

---

## 🧩 Data Model

```
Category (name is unique)
 └── Attribute (name)                 e.g. "Brand", "RAM"
Item (belongs to a Category)
 └── Value (links an Item to an Attribute, stores the value as text)
                                       e.g. Brand = "Dell", RAM = "16GB"
```

---

## 🛠️ Tech Stack

| Area | Technology |
|---|---|
| Language | Java 8 |
| Framework | Spring Boot 2.4.4 (Web, Data JPA, Validation) |
| Database | H2 (in-memory, runtime) |
| Mapping | ModelMapper 2.4.0 (entity ↔ DTO) |
| Build | Maven |
| Testing | JUnit 5, Spring Boot Test |

---

## 📂 Project Structure

```
src/main/java/io/labforward/
├── LabforwardChallengeApplication.java      # App entry point
├── LabforwardChallengeConfiguration.java    # ModelMapper and ObjectMapper beans
├── web/
│   ├── controller/
│   │   ├── CategoryController.java          # REST endpoints
│   │   └── RestExceptionHandler.java        # Global error handling
│   └── dto/                                 # CategoryJson, ItemJson, AttributeJson, ValueJson, ExceptionJson
└── categories/
    ├── entity/                              # Category, Attribute, Item, Value
    ├── repository/                          # CategoryRepository, ItemRepository
    ├── service/                             # CategoryService and implementation
    └── exception/                           # ResourceNotFoundException
```

---

## 🚀 Getting Started

### Prerequisites

- JDK 8 or later
- Maven 3.6+


## 🔌 API Reference

| Method | Endpoint | Description | Success |
|---|---|---|---|
| POST | `/categories` | Create a category with its attributes | `201 Created` |
| POST | `/categories/{id}/items` | Add an item to a category | `201 Created` |
| GET | `/categories/{id}/items` | List all items in a category | `200 OK` |
| PUT | `/categories/{categoryId}/items/{itemId}` | Update an item | `200 OK` |

### Example walkthrough

**1. Create a category**

```bash
curl -X POST http://localhost:8080/categories \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "attributes": [
      { "name": "Brand" },
      { "name": "RAM" }
    ]
  }'
```

The response contains the generated IDs for the category and each attribute.

**2. Add an item** (use the attribute IDs from step 1)

```bash
curl -X POST http://localhost:8080/categories/1/items \
  -H "Content-Type: application/json" \
  -d '{
    "values": [
      { "attribute": { "id": 1 }, "value": "Dell" },
      { "attribute": { "id": 2 }, "value": "16GB" }
    ]
  }'
```

**3. List the items**

```bash
curl http://localhost:8080/categories/1/items
```

### Error format

Every error returns the same shape:

```json
{
  "status": "BAD_REQUEST",
  "timestamp": "2026-11-18 19:45:00",
  "messages": ["Name is required"]
}
```

| Case | Status |
|---|---|
| Validation failure (missing name or attributes) | `400 Bad Request` |
| Category not found | `404 Not Found` |
| Unexpected error | `500 Internal Server Error` |

---

## 🗄️ Using MySQL Instead of H2

Add the MySQL driver to `pom.xml` and configure `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fieldforge
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## 🔮 Future Improvements

- List and fetch categories (`GET /categories`, `GET /categories/{id}`)
- Delete endpoints for categories and items
- Validate that item values use attributes from the same category
- Typed attributes (number, date, boolean) instead of text only
- Pagination and filtering by attribute value
- Swagger / OpenAPI documentation
- More unit and integration tests

---

## 👩‍💻 Author

**Ramya Gupta**: [@okayjiiiramya](https://github.com/okayjiiiramya)

If you like this project, give it a ⭐!
