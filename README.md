# PetStore REST API Automation

An API test automation framework for the public **Swagger Petstore** REST API (`https://petstore.swagger.io/v2`), built with **Java + REST Assured + TestNG**.

It automates full **CRUD** testing of the `/user` endpoint — Create, Read, Update, Delete — using clean, reusable request methods, a POJO payload, JavaFaker for random test data, and an Excel-based data-driven approach. Results are captured with ExtentReports and Log4j2 logging.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [API Under Test](#api-under-test)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [How to Run the Tests](#how-to-run-the-tests)
- [Test Classes](#test-classes)
- [Reports & Logs](#reports--logs)
- [Author](#author)

---

## Features

- **Full CRUD coverage** of the Petstore `/user` endpoint (POST, GET, PUT, DELETE).
- **REST Assured** for fluent, readable API requests and response validation.
- **POJO / Serialization** — the `User` payload is a Java object automatically converted to JSON.
- **Random test data** generated with **JavaFaker**, so each run uses fresh, realistic values.
- **Data-Driven Testing (DDT)** — user data is read from an Excel file (`Apache POI`) and fed into tests via TestNG `@DataProvider`.
- **Endpoint & route separation** — URLs are kept in a `routes.properties` file and mapped through a `Routes` class, so nothing is hard-coded in the tests.
- **Status-code and response assertions** with TestNG.
- **ExtentReports** HTML reporting and **Log4j2** logging.

---

## Tech Stack

| Purpose | Tool / Library |
|---|---|
| Language | Java |
| API testing | REST Assured |
| Test runner | TestNG |
| Build & dependencies | Maven |
| Random data | JavaFaker |
| Excel (data-driven) | Apache POI |
| Reporting | ExtentReports |
| Logging | Log4j2 |

---

## Project Structure

```
PetStore-REST-API-Automation/
├── src/test/
│   ├── java/
│   │   ├── api_endpoints/
│   │   │   ├── Routes.java              # Loads endpoint URLs from properties
│   │   │   ├── UserEndpoints.java       # REST Assured methods: create/get/update/delete
│   │   │   └── UserEndpoints2.java      # Alternate endpoint implementation
│   │   ├── api_payload/
│   │   │   └── User.java                # POJO for the user request/response body
│   │   ├── api_test/
│   │   │   ├── UserTest.java            # CRUD flow with Faker-generated data
│   │   │   ├── UserTest2.java           # Variation of the CRUD test flow
│   │   │   └── DDTTest.java             # Data-driven test using Excel input
│   │   └── api_utilities/
│   │       ├── DataProviders.java        # TestNG @DataProvider methods
│   │       ├── ExcelUtility.java         # Reads .xlsx test data
│   │       └── ExtentReportManager.java  # TestNG listener for reports
│   └── resources/
│       ├── routes.properties           # Base + endpoint URLs
│       └── log4j2.xml                   # Logging configuration
├── TestData/
│   └── APIDDT.xlsx                      # Data-driven test input
├── master.xml                          # TestNG suite file
├── pom.xml                             # Maven configuration
├── logs/                               # Log4j2 output
└── reports/                            # ExtentReports HTML output
```

---

## API Under Test

The framework targets the public **Swagger Petstore** demo API. Endpoints are defined in `src/test/resources/routes.properties`:

```properties
base_url=https://petstore.swagger.io/v2
post_url=https://petstore.swagger.io/v2/user
get_url=https://petstore.swagger.io/v2/user/{username}
update_url=https://petstore.swagger.io/v2/user/{username}
delete_url=https://petstore.swagger.io/v2/user/{username}
```

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Create user | `POST` | `/user` |
| Get user | `GET` | `/user/{username}` |
| Update user | `PUT` | `/user/{username}` |
| Delete user | `DELETE` | `/user/{username}` |

Because it uses a public API, **no local server setup is required** — you just need internet access.

---

## Prerequisites

- **Java JDK** (8 or higher) installed with `JAVA_HOME` set
- **Maven** installed and on your `PATH`
- **Internet access** (tests call the live Petstore API)

---

## Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/Praveen12122k/PetStore-REST-API-Automation.git
   cd PetStore-REST-API-Automation
   ```

2. **Install dependencies**

   ```bash
   mvn clean install -DskipTests
   ```

That's it — the endpoints are already configured in `routes.properties`, and test data lives in `TestData/APIDDT.xlsx`.

---

## How to Run the Tests

**Run the full suite via Maven:**

```bash
mvn test
```

**Run the TestNG suite file directly:**

```bash
mvn test -DsuiteXmlFile=master.xml
```

**Run a single test class (example):**

```bash
mvn test -Dtest=UserTest
```

You can also run any test class or `master.xml` directly from an IDE (IntelliJ IDEA / Eclipse) with the TestNG plugin.

---

## Test Classes

| Class | What it verifies |
|---|---|
| **UserTest** | End-to-end CRUD flow — creates a user with Faker-generated data, then reads, updates, and deletes it, asserting `200` at each step. |
| **UserTest2** | An alternate version of the CRUD flow (same operations, different structure). |
| **DDTTest** | Data-driven test — creates and deletes users using multiple rows of data read from `APIDDT.xlsx` via `@DataProvider`. |

Each test logs the full request/response and asserts the expected HTTP status code.

---

## Reports & Logs

- **ExtentReports** — a styled HTML report is generated in the `reports/` folder after each run (e.g. `Test-Report-<timestamp>.html`). Open it in any browser to see pass/fail results.
- **Logs** — detailed execution logs are written to the `logs/` folder via Log4j2 (INFO level), so you can trace exactly what each test did.

---

## Author

**Praveen** — [github.com/Praveen12122k](https://github.com/Praveen12122k)

> This project is a learning/portfolio framework demonstrating industry-standard REST API test automation with REST Assured, TestNG, POJO serialization, and data-driven testing.
