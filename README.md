# Reward Points API

A Spring Boot REST application that calculates reward points for customers based on their recorded transaction history.

## Reward Points Rules
* 2 points for every dollar spent over $100 in each transaction.
* 1 point for every dollar spent between $50 and $100 in each transaction.
* **Example:** A $120 purchase = 2 * $20 + 1 * $50 = 90 points.

## Getting Started

### Prerequisites
* Java 17+ (or your configured version)
* Gradle

### Running the Application
Navigate to the project root directory and run the following Gradle command:

```bash
./gradlew bootRun
```
The application will start on `http://localhost:8080`.

---

## API Endpoints (Postman)

You can use Postman (or your browser) to hit the endpoint once the application is running. 

### Calculate All Customer Rewards
Calculates and returns the reward points for all customers, broken down by month and showing the total points.

* **Method:** `GET`
* **URL:** `http://localhost:8080/api/rewards/calculate`

#### Example JSON Response
```json
[
    {
        "customerId": 1,
        "pointsPerMonth": {
            "JANUARY": 90,
            "FEBRUARY": 150
        },
        "totalPoints": 240
    }
]
```

---

## H2 Database Console

This application uses an in-memory H2 database. Data is wiped when the application stops, making it perfect for testing. You can view the raw `TRANSACTION` data directly in your browser.

1. Ensure the Spring Boot application is running.
2. Open your browser and navigate to: **http://localhost:8080/h2-console**
3. Log in with the following credentials (unless modified in your `application.properties`):
    * **JDBC URL:** `jdbc:h2:mem:testdb`
    * **User Name:** `sa`
    * **Password:** *(leave blank)*

Once connected, you can run SQL queries like `SELECT * FROM TRANSACTION;` to verify your data.