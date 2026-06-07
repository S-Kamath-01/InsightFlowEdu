# 🎓 InsightFlowEdu  
*A Data-Driven Academic Insight and Intervention Platform*

---

## 🌟 Overview
**InsightFlowEdu** is a mini-project developed as part of **Software Engineering** and **DBMS** coursework.  
It's an integrated academic analytics platform that helps faculty identify at-risk students early through data-driven insights on performance, attendance, and feedback sentiment.

The project demonstrates:
- **Software Engineering principles** — SRS, class & sequence diagrams, use cases, and modular design.
- **DBMS skills** — Oracle database design, ER modeling, PL/SQL procedures, triggers, and Java database connectivity.

## 🏗️ Architecture at a Glance
- **Frontend:** React + TypeScript UI with charts, forms, and mock-service support for offline development.
- **Backend:** Spring Boot REST APIs for authentication, dashboard analytics, student management, and interventions.
- **Database:** Oracle schema with sample data, PL/SQL logic, and BCrypt-backed password storage.
- **Flow:** UI -> REST API -> JDBC -> Oracle, with sample data powering the demo experience.

---

## 🧩 Key Features
- **Student Performance Tracking:** Records and analyzes GPA and attendance trends per semester.
- **Risk Assessment Engine:** PL/SQL procedure flags students below academic thresholds.
- **Feedback Sentiment Analysis:** Classifies student feedback as *positive*, *neutral*, or *negative* using a simple keyword-based PL/SQL function.
- **Intervention Logs:** Faculty can record and review intervention notes per student.
- **Dashboard UI:** Clean React-based interface for analytics, charts, and insights.

## 📸 Screenshots
Add a small screenshot gallery before publishing the portfolio version. Recommended captures:
- Login page
- Dashboard with analytics cards and charts
- Student detail view
- Risk summary panel
- Intervention form

If you want a compact portfolio presentation, place the images under `docs/screenshots/` and link them here.

---

## 📊 Language Composition
| Language | Percentage |
|----------|-----------|
| TypeScript | 60% |
| Java | 34.4% |
| PL/SQL | 3.3% |
| JavaScript | 2% |
| CSS | 0.2% |
| HTML | 0.1% |

---

## ⚙️ Tech Stack

### **Database Layer**
| Technology | Purpose |
|------------|---------|
| Oracle Database XE 21c | Core data management, PL/SQL procedures, triggers, JDBC connectivity |

### **Backend**
| Technology | Purpose |
|------------|---------|
| Spring Boot 3.5.6 | REST API framework and business logic |
| Java 21 | Backend language |
| Maven | Build and dependency management |
| Oracle JDBC (ojdbc11) | Database driver |
| Spring Security | Authentication and authorization |
| Lombok | Code generation and boilerplate reduction |
| JUnit 5 | Unit testing |

### **Frontend**
| Technology | Purpose |
|------------|---------|
| React 18 | UI library |
| TypeScript 5.3 | Type-safe JavaScript |
| Vite 5.0 | Fast build tool |
| React Router v6 | Client-side routing |
| TanStack Query v5 | Data fetching and caching |
| Axios | HTTP client |
| React Hook Form + Zod | Form validation |
| Recharts | Charts and data visualization |
| Tailwind CSS 3.3 | Utility-first styling |
| Headless UI | Accessible UI components |
| MSW (Mock Service Worker) | API mocking for frontend-only development |
| Framer Motion | Animation library |
| ESLint + Prettier | Code quality and formatting |
| Jest + React Testing Library | Unit and component testing |
| Husky + lint-staged | Git hooks for code quality |

### **Development Tools**
| Tool | Purpose |
|------|---------|
| IntelliJ IDEA | Java/Backend IDE |
| VS Code | Frontend editor |
| Oracle SQL Developer | Database development and administration |
| Git | Version control |

---

## 🧠 Database Design
- Fully normalized schema in **3NF**
- Includes ER diagram, relational tables, and integrity constraints
- Implements:
  - PL/SQL Procedure: `run_risk_engine`  
  - PL/SQL Function: `classify_sentiment`  
  - Trigger: `trg_interventions_timestamp`

---

## 🖥️ UI & Functionality
- Faculty Login  
- Dashboard with GPA and Attendance charts  
- Risk Analysis view  
- Feedback sentiment summary  
- Intervention management  

---

## 🧩 DBMS Deliverables
This project satisfies all the required components of the DBMS mini-project:
- Abstract, Problem Statement  
- ER Diagram and Normalized Tables  
- DDL Commands and Constraints  
- List of SQL Queries (Basic + Complex)  
- PL/SQL Procedures / Functions / Triggers  
- Java JDBC Connectivity  
- UI Design (screenshots included in `/docs` folder)  
- References  

---

## 🚀 Quick Start

### Frontend
```bash
cd frontend
npm install
npm run dev
```

### Backend
```bash
cd backend/insightflow-backend
# Configure Oracle database connection in application.properties
mvn spring-boot:run
```

The local demo accounts are provisioned by the database seed scripts. Keep the actual passwords out of the repository and use placeholders in any public-facing setup notes.

See detailed setup instructions in:
- [Frontend README](./frontend/README.md)
- [Backend README](./backend/insightflow-backend/README.md)

---

## 📁 Project Structure

```
InsightFlow-Edu/
├── frontend/                          # React TypeScript frontend
│   ├── src/
│   │   ├── api/                       # API client and endpoints
│   │   ├── components/                # Shared UI components
│   │   ├── features/                  # Feature modules
│   │   ├── mocks/                     # MSW handlers
│   │   └── utils/                     # Helper functions
│   └── package.json
├── backend/insightflow-backend/       # Spring Boot backend
│   ├── src/
│   │   ├── main/java/com/example/     # Controllers, services, DTOs
│   │   └── test/                      # Unit tests
│   ├── database/                      # SQL scripts
│   │   ├── ddl/                       # Table creation
│   │   ├── plsql/                     # Procedures and functions
│   │   └── sample_data/               # Test data
│   └── pom.xml
└── README.md
```

---

## 🔑 Local Demo Accounts

| Role | Username | Password |
|------|----------|----------|
| Faculty | faculty | YOUR_LOCAL_FACULTY_PASSWORD |
| Academic Head | admin | YOUR_LOCAL_ADMIN_PASSWORD |
| IT Admin | it | YOUR_LOCAL_IT_PASSWORD |

These accounts are useful for local setup and demonstration only. Do not publish real passwords in the repository.

## 💼 Portfolio Highlights
- End-to-end full-stack app with React, TypeScript, Spring Boot, and Oracle.
- Database-driven risk detection and sentiment analysis using PL/SQL.
- BCrypt password hashing and role-based user flows.
- UI mock layer with MSW for offline frontend development and testing.
- Automated frontend tests with Jest and React Testing Library.

---

## 📚 Documentation

- [Frontend Setup & Development](./frontend/README.md)
- [Backend API Documentation](./backend/insightflow-backend/README.md)
- [Database Schema & Scripts](./backend/insightflow-backend/database/)

---

**Status**: ✅ Demo-ready | Full-stack application with React frontend and Spring Boot backend

