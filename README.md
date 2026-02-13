# Selenium Test Automation Framework

UI automation framework for OrangeHRM using Java, Selenium, TestNG, Maven, and Allure.

## Tech Stack

- Java 21
- Selenium 4
- TestNG 7
- Maven
- Allure Report

## Key Capabilities

- Page Object Model design (`pages/*`)
- Reusable base layer for driver/test lifecycle (`core/base`, `core/driver`)
- Config-driven execution via `src/test/resources/config.properties`
- JSON-driven test data (`src/test/resources/testdata`)
- Smoke and regression suite support through TestNG XML + Maven profiles
- Automatic screenshots during action steps and on failure
- Allure integration with run history preservation across report generations

## Project Structure

```text
src
|- test
|  |- java
|  |  |- core
|  |  |  |- base
|  |  |  |- config
|  |  |  |- context
|  |  |  |- driver
|  |  |  `- utils
|  |  |- pages
|  |  `- tests
|  `- resources
|     |- config.properties
|     |- testng.xml
|     |- testng-smoke.xml
|     |- testng-regression.xml
|     `- testdata
```

## Prerequisites

- JDK 21+
- Maven 3.9+
- Chrome or Edge installed (based on `browser` in config)

## Configuration

Update `src/test/resources/config.properties`:

- `browser=chrome|edge`
- `headless=true|false`
- `url=...`
- `screenshot=true|false`
- timeout values

## Run Tests

Default suite:

```bash
mvn clean test
```

Smoke suite:

```bash
mvn clean test -Psmoke
```

Regression suite:

```bash
mvn clean test -Pregression
```

## Allure Reporting

Generate report:

```bash
mvn allure:report
```

Serve report locally:

```bash
mvn allure:serve
```

Current behavior:

- Allure results are written to `target/allure-results`
- Step/failure screenshots are attached directly in Allure
- Old raw result files are cleared before suite start to avoid false retries
- Previous `history` is copied into new results when available, so trends/history are retained

## Artifacts

- TestNG reports: `target/surefire-reports`
- Allure results: `target/allure-results`
- Allure generated site: `target/site/allure-maven-plugin`
- Local screenshots: `target/screenshots`

## Author

Anand  
LinkedIn: https://www.linkedin.com/in/1stanand
