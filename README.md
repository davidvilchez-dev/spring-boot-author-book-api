# Spring Boot - Author & Book REST API

REST API built with **Spring Boot 4** that manages authors and books with a **One-to-Many** relationship. Includes full CRUD operations and global exception handling.

## Tech Stack

- **Java 25**
- **Spring Boot 4.0.2**
- **Spring Data JPA** (Hibernate)
- **MySQL** as the database
- **Maven** as the build tool

## Project Structure

```
src/main/java/com/david/springboot/springboot_author_book_api/
├── controller/
│   ├── AuthorController.java
│   └── BookController.java
├── entity/
│   ├── Author.java          # Entity with @OneToMany relationship to Book
│   └── Book.java             # Entity with @ManyToOne relationship to Author
├── exception/
│   ├── ErrorResponse.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository/
│   ├── AuthorRepository.java
│   └── BookRepository.java
├── service/
│   ├── AuthorService.java
│   ├── AuthorServiceImpl.java
│   ├── BookService.java
│   └── BookServiceImpl.java
└── SpringbootAuthorBookApiApplication.java
```

## Data Model

### Author

| Field     | Type   | Description            |
| --------- | ------ | ---------------------- |
| id        | Long   | Unique identifier (PK) |
| name      | String | Author's name          |
| email     | String | Email address          |
| biography | String | Biography              |
| books     | List   | Associated books       |

### Book

| Field     | Type   | Description            |
| --------- | ------ | ---------------------- |
| id        | Long   | Unique identifier (PK) |
| title     | String | Book title             |
| gender    | String | Literary genre         |
| published | Date   | Publication date       |
| author    | Author | Associated author (FK) |

## Endpoints

### Authors — `/api/v1/authors`

| Method   | Endpoint                                    | Description                  |
| -------- | ------------------------------------------- | ---------------------------- |
| `GET`    | `/api/v1/authors`                           | Get all authors              |
| `GET`    | `/api/v1/authors/{id}`                      | Get an author by ID          |
| `POST`   | `/api/v1/authors`                           | Create a new author          |
| `PUT`    | `/api/v1/authors/{id}`                      | Update an author             |
| `DELETE` | `/api/v1/authors/{id}`                      | Delete an author             |
| `GET`    | `/api/v1/authors/{authorId}/books`          | Get all books by an author   |
| `POST`   | `/api/v1/authors/{authorId}/books`          | Add a book to an author      |
| `DELETE` | `/api/v1/authors/{authorId}/books/{bookId}` | Remove a book from an author |

### Books — `/api/v1/books`

| Method   | Endpoint                          | Description                 |
| -------- | --------------------------------- | --------------------------- |
| `GET`    | `/api/v1/books`                   | Get all books               |
| `GET`    | `/api/v1/books/{id}`              | Get a book by ID            |
| `POST`   | `/api/v1/books/author/{authorId}` | Create a book for an author |
| `PUT`    | `/api/v1/books/{id}`              | Update a book               |
| `DELETE` | `/api/v1/books/{id}`              | Delete a book               |

## Exception Handling

The API handles errors globally using `@RestControllerAdvice`:

| Code | Exception                             | Description                                                |
| ---- | ------------------------------------- | ---------------------------------------------------------- |
| 404  | `ResourceNotFoundException`           | The requested resource does not exist                      |
| 400  | `MethodArgumentTypeMismatchException` | Invalid parameter type (e.g., letters instead of a number) |
| 400  | `ConstraintViolationException`        | Validation failed                                          |
| 500  | `Exception`                           | Unexpected internal error                                  |

Error response example:

```json
{
  "message": "Author not found with id: 99",
  "status": 404,
  "detail": "The requested resource does not exist in the database"
}
```

## Setup

### 1. Create the MySQL database

```sql
CREATE DATABASE curso_api_rest;
```

### 2. Configure credentials

Copy the example file and edit it with your credentials:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edit `application.properties` with your MySQL username and password.

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Usage Examples

### Create an author

```bash
curl -X POST http://localhost:8080/api/v1/authors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Gabriel García Márquez",
    "email": "gabo@example.com",
    "biography": "Colombian writer, Nobel Prize in Literature"
  }'
```

### Create a book for an author

```bash
curl -X POST http://localhost:8080/api/v1/books/author/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "One Hundred Years of Solitude",
    "gender": "Magical realism",
    "published": "1967-06-05"
  }'
```

### Get all books by an author

```bash
curl http://localhost:8080/api/v1/authors/1/books
```
