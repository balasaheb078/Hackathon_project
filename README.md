
#  District Automation Testing Framework — Hackathon Training Project

This project is a **training-based automation framework** developed as part of a QA Hackathon initiative. 
It simulates end-to-end testing of the [District.in](https://www.district.in/) web application using modern automation tools and best practices. 
The framework is designed to be scalable, maintainable, and production-ready, while serving as a hands-on learning experience for participants.


##  Objectives

- Apply real-world automation techniques using industry-standard tools
- Build a modular and reusable test framework
- cross-browser execution
- Demonstrate understanding of BDD, data-driven testing, and reporting


##  Framework Components

| Feature                         | Description                                                                 |
|----------------------------------|-----------------------------------------------------------------------------|
| **BDD with Cucumber**            | Write readable test scenarios using Gherkin syntax                         |
| **Selenium + Java + TestNG**     | Core automation stack for UI testing                                       |
| **Maven**                        | Build and dependency management                                            |
| **Multiple Browser Execution**   | Supports Chrome, Edge via configuration                                    |
| **Page Object Model (POM)**      | Clean separation of UI logic using Page Factory                            |
| **Data-Driven Framework**        | Test data from Excel and XML files                                         |
| **Reusable Utility Classes**     | Screenshot, Excel, Property, Waits, Retry, Logging                         |
| **Allure Reporting**             | Rich visual test reports with step-level insights                          |
| **Exception Handling**           | Graceful error capture and logging                                         |
| **Selenium Grid**                | Distributed test execution across nodes                                    |
| **Git/GitHub/GitLab**            | CI/CD pipeline integration for automated builds                            |
| **Log4j Logging**                | Step-level logging for debugging and traceability                          |
| **Screenshots**                  | Captured on failure or key checkpoints                                     |
| **Retry Listeners**              | Re-execute failed tests automatically                                      |
| **Explicit and Fluent Waits**    | Robust synchronization using Selenium waits                                |

---

##  Project Structure

```
C:.
├── pom.xml                     # Maven build file
├── testng.xml                  # TestNG suite configuration
├── screenshots/                # Captured screenshots
├── allure-results/            # Allure report output
├── src/
│   └── test/
│       ├── java/
│       │   └── com/test/
│       │       ├── data/               # Excel data sheet handler
│       │       ├── factory/            # BaseClass for WebDriver and logger
│       │       ├── objectRepository/   # Page Object Model classes
│       │       ├── resources/          # Data models (Activity, Dining, Movie)
│       │       ├── utilities/          # Excel, XML, Screenshot, Retry, etc.
│       │       ├── stepDefinitions/    # Cucumber step definitions
│       │       └── testRunner/         # Test runner class
│       └── resources/
│           ├── features/               # Cucumber feature files
│           ├── testdata/              # Excel and property files
│           └── config.properties       # Global configuration
└── test-output/              # TestNG HTML reports
```

---

##  How to Run the Project

### Prerequisites

- Java 11+
- Maven
- ChromeDriver / EdgeDriver 
- Allure CLI

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/J0seph-Mart1n/ProTester-Hackathon-Project.git
   cd ProTester-Hackathon-Project
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run tests**
   ```bash
   mvn test
   ```

4. **View Allure Report**
   ```bash
   allure serve allure-results
   ```



---

##  Modules Covered

| Module         | Description                                                  |
|----------------|--------------------------------------------------------------|
| Dining         | Search cafés and export details to Excel                     |
| Activities     | Fetch weekend activities and sort by price                   |
| Events         | Load and sort events, export to Excel                        |
| Movies         | Retrieve movie languages and validate seat selection         |
| Contact Page   | Form submission with XML-based data                          |
| Sign-In/Sign-Up| Validation of login flows and error handling                 |
| Social Links   | Verify external social media links and titles                |

---

##  Configuration

- Excel File: `src/test/resources/testdata/ExcelData.xlsx`
- Property Files:  
  - `logIn.properties`  
  - `movieSeat.properties`  
  - `config.properties`
- XML Files:  
  - `ContactValidForm.xml`  
  - `ContactInvalidForm.xml`

---

##  Reporting & Screenshots

- Screenshots: `/screenshots/`
- Logs: Generated via Log4j
- Allure Reports: `/allure-results/`
- TestNG Reports: `/test-output/`

---

##  How to Add a New Test

1. Create a `.feature` file in `src/test/resources/features/`
2. Write step definitions in `stepDefinitions/`
3. Add page objects in `objectRepository/`
4. Update utilities or data models if needed

---

## 📄 License

This project is for educational use only and is not intended for commercial deployment.

---
