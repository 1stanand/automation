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

## ✨ Features

- **User Management:** Create, and manage users through the admin panel.
- **Dashboard Widgets:** Verify the visibility and functionality of dashboard widgets.
- **Leave Management:** Navigate and verify leave management sections.

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
    │   │   └── dashboard   # Dashboard-related page objects
    │   └── tests           # Test classes
    │       └── dashboardTests # Dashboard-related test classes
    └── resources
        ├── config.properties # Environment configuration
        ├── testng.xml      # TestNG suite configuration
        └── testdata        # JSON-based test data
```

---

## 🧩 Key Design Principles

- **Page Object Model:** Each page in the application has a corresponding page object.
- **Page Navigation:** Page objects are responsible for navigation and return other page objects.
- **Separation of Concerns:** Test logic is kept separate from page objects and utility classes.
- **Configuration-Driven:** Execution is controlled through a central configuration file.
- **Reusable Utilities:** Common actions and waits are encapsulated in utility classes.
- **Data-Driven:** Test data is externalized in JSON files, separating data from test logic.

---

## ⚙️ Configuration

Application configuration is managed via `config.properties`, including:

- Application URL
- Timeouts
- Screenshot settings

---

## 🧪 Test Data Strategy

- JSON files are used to define test data for different scenarios.
- This approach ensures that tests are deterministic and repeatable.

---

## 📸 Screenshots

- Screenshots are automatically captured for failed tests and key actions.
- They are stored locally and excluded from version control.

---

## ▶️ Run Tests

**Run Default Suite (Functional Modules):**

```bash
mvn clean test
```

---

## 🔮 Planned Enhancements

- **Parallel Execution:** Implement parallel test execution to reduce run time.
- **Reporting:** Integrate with a reporting tool like Allure or ExtentReports.
- **CI/CD Integration:** Set up a continuous integration and deployment pipeline.
- **Cross-Browser Testing:** Add support for running tests on different browsers.

---

## 👤 Author

**Anand**  
Senior Automation Test Engineer / SDET II

LinkedIn : www.linkedin.com/in/1stanand
