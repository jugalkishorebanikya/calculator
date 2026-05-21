# Spring Boot Calculator

A clean REST API calculator built with **Spring Boot 3**, Java 21, and Swagger UI.

## Features

| Operation    | Endpoint              | Method    |
|--------------|-----------------------|-----------|
| Add          | `/api/v1/calculator/add`      | GET / POST |
| Subtract     | `/api/v1/calculator/subtract` | GET / POST |
| Multiply     | `/api/v1/calculator/multiply` | GET / POST |
| Divide       | `/api/v1/calculator/divide`   | GET / POST |
| Modulo       | `/api/v1/calculator/modulo`   | GET / POST |
| Power        | `/api/v1/calculator/power`    | GET / POST |
| Square Root  | `/api/v1/calculator/sqrt`     | GET        |

## Project Structure

```
calculator/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/calculator/
    │   │   ├── CalculatorApplication.java      # Entry point
    │   │   ├── controller/
    │   │   │   └── CalculatorController.java   # REST endpoints
    │   │   ├── service/
    │   │   │   └── CalculatorService.java      # Business logic
    │   │   ├── model/
    │   │   │   ├── CalculationRequest.java     # Input DTO
    │   │   │   └── CalculationResponse.java    # Output DTO
    │   │   └── exception/
    │   │       ├── CalculatorException.java    # Domain exception
    │   │       └── GlobalExceptionHandler.java # Error handling
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/example/calculator/
            ├── CalculatorServiceTest.java              # Unit tests
            └── CalculatorControllerIntegrationTest.java
```

## Prerequisites

- Java 21+
- Maven 3.8+

## Run

```bash
mvn spring-boot:run
```

The server starts on **http://localhost:8080**

## Swagger UI

Open **http://localhost:8080/swagger-ui.html** in your browser to explore and test all endpoints interactively.

## Example Requests

### GET (quick test in browser or curl)

```bash
# Add
curl "http://localhost:8080/api/v1/calculator/add?a=10&b=5"

# Square root
curl "http://localhost:8080/api/v1/calculator/sqrt?a=81"
```

### POST (JSON body)

```bash
curl -X POST http://localhost:8080/api/v1/calculator/divide \
     -H "Content-Type: application/json" \
     -d '{"a": 22, "b": 7}'
```

### Example Response

```json
{
  "a": 22.0,
  "b": 7.0,
  "operation": "divide",
  "result": 3.142857142857143,
  "expression": "22 ÷ 7 = 3.142857142857143"
}
```

### Error Response (division by zero)

```json
{
  "errorCode": "DIVISION_BY_ZERO",
  "message": "Cannot divide by zero.",
  "timestamp": "2025-01-01T12:00:00"
}
```

## Run Tests

```bash
mvn test
```

## Health Check

```bash
curl http://localhost:8080/actuator/health
```
