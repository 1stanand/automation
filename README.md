# Selenium Automation Framework (Java)

A scalable and maintainable **Selenium automation framework** built using **Java and Maven**, designed with real-world SDET practices in mind.

This project focuses on **clean architecture, separation of concerns, and long-term maintainability**, rather than demo-only scripting.

---

## 🚀 Tech Stack
- Java
- Selenium WebDriver
- Maven
- TestNG
- Page Object Model (POM)

---

## 📁 Project Structure

```text
src
├── main
│   └── java
└── test
    ├── java
    │   ├── core
    │   │   ├── base        # Base test & page abstractions
    │   │   ├── config      # Configuration management
    │   │   ├── driver      # WebDriver lifecycle handling
    │   │   └── utils       # Reusable utilities (waits, actions, etc.)
    │   ├── pages           # Page Objects
    │   └── tests           # Test classes
    └── resources
        └── testdata        # JSON-based test data
```

---

## 🧩 Key Design Principles
- Page Object Model for clean separation
- No test logic inside page classes
- Configuration-driven execution
- Reusable utility helpers
- Minimal external dependencies to reduce flakiness

---

## ⚙️ Configuration
Application configuration is managed via a properties file, including:
- Application URL
- Timeouts
- Execution flags (e.g. screenshots)

Environment configuration is intentionally kept separate from **test data**.

---

## 🧪 Test Data Strategy
- JSON files used for defining test scenarios
- Deterministic and repeatable execution
- Database or API usage intended only for test setup or verification, not for driving UI scenarios

---

## 📸 Screenshots
- Automatically captured based on configuration
- Stored locally
- Excluded from version control
- CI-friendly by design

---

## ▶️ Run Tests

```bash
mvn clean test
```

---

## 🔮 Planned Enhancements
- JSON utility enhancements
- Parallel execution support
- Reporting integration
- CI/CD pipeline integration
- Environment-based execution profiles

---

## 👤 Author
**Anand**  
Senior Automation / SDET  
(Add LinkedIn & GitHub links here)
