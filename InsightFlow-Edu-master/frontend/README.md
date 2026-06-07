# InsightFlow EDU - Frontend

A React TypeScript application for student performance analytics, risk detection, and faculty intervention tracking.

## 🚀 Features

- **Student Performance Dashboard** - Real-time KPIs, GPA/attendance trends, risk summaries
- **Risk Detection Panel** - Identify at-risk students with configurable thresholds
- **Faculty Intervention Tracker** - Log and manage interventions
- **Feedback Sentiment Analyzer** - Rule-based sentiment analysis on student feedback
- **Role-based Access Control** - Faculty, Academic Head, and IT roles
- **CSV Import/Export** - Bulk data management
- **Responsive Design** - Mobile-first Tailwind CSS UI
- **Mock Backend** - MSW for frontend-only development

## 📋 Prerequisites

- Node.js 18+ and npm
- Modern web browser

## 🛠️ Installation

1. **Clone and navigate to frontend directory:**
   ```bash
   cd frontend
   ```

2. **Install dependencies:**
   ```powershell
   npm install
   ```

3. **Configure environment variables:**
   - Copy `.env.example` to `.env`:
     ```powershell
     Copy-Item .env.example .env
     ```
   - Edit `.env` and set:
     ```
     VITE_API_BASE_URL=http://localhost:8080/api
     VITE_MOCK_MODE=true
     ```

## 🎯 Running the Application

### Development Mode (with MSW mocks)

```powershell
npm run dev
```

The app will start at `http://localhost:5173` with Mock Service Worker enabled.

### Production Build

```powershell
npm run build
npm run preview
```

### Switching Between Mock and Real Backend

- **Mock mode** (default): Set `VITE_MOCK_MODE=true` in `.env`
- **Real backend**: Set `VITE_MOCK_MODE=false` and ensure backend is running at `VITE_API_BASE_URL`

## 🔐 Demo Credentials

Use the locally provisioned demo accounts from the database setup when running in mock mode.

| Role          | Username | Password   |
|---------------|----------|------------|
| Faculty       | faculty  | YOUR_LOCAL_FACULTY_PASSWORD |
| Academic Head | admin    | YOUR_LOCAL_ADMIN_PASSWORD   |
| IT Admin      | it       | YOUR_LOCAL_IT_PASSWORD      |

## 📖 Demo Script (5 minutes)

1. **Login** with the local faculty demo account from setup
2. **Dashboard** - View KPIs, GPA trends, attendance charts
3. **Risk Panel** - Click "Run Risk Detection" with default thresholds
4. **Students List** - Browse paginated list, click a student
5. **Student Profile** - View details, interventions, feedback
6. **Feedback** - Submit feedback text and see sentiment analysis
7. **Logout** and login with the local academic-head demo account from setup
8. **Admin Panel** - View courses and faculty lists

## 🧪 Testing

```powershell
# Run unit tests
npm test

# Run tests in watch mode
npm run test:watch

# Generate coverage report
npm run test:coverage
```

## 🎨 Code Quality

```powershell
# Lint code
npm run lint

# Fix linting issues
npm run lint:fix

# Format code
npm run format
```

## 📁 Project Structure

```
frontend/
├── src/
│   ├── api/              # API client, types, endpoints
│   ├── app/              # App.tsx, routes, global CSS
│   ├── components/       # Shared components (Header, Sidebar, etc.)
│   ├── features/         # Feature modules
│   │   ├── auth/         # Login, AuthProvider
│   │   ├── dashboard/    # Dashboard page, charts
│   │   ├── students/     # Students list, profile
│   │   ├── risk/         # Risk panel, admin rules
│   │   ├── interventions/# Interventions management
│   │   ├── feedback/     # Feedback analyzer
│   │   └── admin/        # Admin panel, CSV import
│   ├── mocks/            # MSW handlers and mock data
│   ├── tests/            # Unit tests
│   └── utils/            # Helper functions
├── public/               # Static assets
├── .env.example          # Environment variable template
├── package.json          # Dependencies and scripts
├── tsconfig.json         # TypeScript configuration
├── tailwind.config.cjs   # Tailwind CSS configuration
└── vite.config.ts        # Vite build configuration
```

## 🔧 Technology Stack

- **React 18** - UI library
- **TypeScript** - Type safety
- **Vite** - Build tool
- **React Router v6** - Routing
- **TanStack Query** - Data fetching & caching
- **Axios** - HTTP client
- **React Hook Form + Zod** - Form validation
- **Recharts** - Charts
- **Tailwind CSS** - Styling
- **Headless UI** - Accessible components
- **MSW** - API mocking
- **Jest + React Testing Library** - Testing

## 🔗 Backend Integration

To connect to a real backend:

1. Set `VITE_MOCK_MODE=false` in `.env`
2. Configure `VITE_API_BASE_URL` to your backend URL
3. Ensure backend implements the API contract (see `src/api/endpoints.ts`)

### API Endpoints Expected

- `POST /api/auth/login` - Authentication
- `GET /api/students` - List students (paginated, searchable)
- `GET /api/students/:id` - Student details
- `GET /api/courses` - List courses
- `GET /api/risk-flags` - Risk flags
- `POST /api/run-risk` - Trigger risk detection (calls PL/SQL `run_risk_detection`)
- `POST /api/interventions` - Create intervention
- `POST /api/feedback` - Submit feedback
- `POST /api/analyze` - Sentiment analysis
- `GET /api/dashboard/stats` - Dashboard KPIs
- `GET /api/dashboard/gpa-trends` - GPA trends
- `GET /api/dashboard/attendance-trends` - Attendance trends

## 🗄️ Database Integration Points

The frontend expects these PL/SQL procedures/functions via REST endpoints:

- `run_risk_detection(p_gpa_threshold, p_att_threshold)` - Called by `/api/run-risk`
- `classify_sentiment(p_feedback_text)` - Called by `/api/feedback`
- `log_intervention(p_student_id, p_faculty_id, p_notes)` - Called by `/api/interventions`
- `get_student_analytics(p_student_id)` - Called by `/api/students/:id`

## 🚧 CI/CD

GitHub Actions workflow (`.github/workflows/ci.yml`) runs on every push:

1. Install dependencies
2. Run ESLint
3. Run tests
4. Build production bundle
5. Upload build artifacts

## 🐛 Troubleshooting

### Port 3000 already in use

```powershell
# Change port in vite.config.ts or set env variable
$env:PORT=3001; npm run dev
```

### MSW not starting

Ensure `public/mockServiceWorker.js` exists. Regenerate:
```powershell
npx msw init public/ --save
```

### TypeScript errors

```powershell
# Clear cache and reinstall
Remove-Item node_modules -Recurse -Force
Remove-Item package-lock.json
npm install
```

## 📝 Contributing

1. Create feature branch: `git checkout -b feature/your-feature`
2. Commit changes: `git commit -m "Add feature"`
3. Push: `git push origin feature/your-feature`
4. Open Pull Request

## 📄 License

DBMS + Software Engineering Mini Project © 2025

## 👥 Team

- **Frontend**: React TypeScript with Vite
- **Backend**: Spring Boot
- **Database**: Oracle DB with PL/SQL stored procedures
- **Sentiment Analysis**: Rule-based analysis exposed through backend APIs

## 📞 Support

For issues or questions, refer to the SRS document or contact the development team.

---

**Status**: ✅ Demo-ready | Mock backend support available
