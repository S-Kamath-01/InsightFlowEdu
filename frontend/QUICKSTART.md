# 🚀 InsightFlow EDU - Quick Start Guide

**⚡ Get up and running quickly.**

---

## ✅ Prerequisites Check

Before starting, ensure you have:
- ✅ Node.js 18+ installed (`node --version`)
- ✅ npm installed (`npm --version`)
- ✅ A modern web browser (Chrome, Firefox, Edge)

---

## 🎯 Installation

```bash
cd frontend
npm install
npx msw init public/ --save
```

---

## 🏃 Running the Application

```bash
npm run dev
```

The app will automatically open at: **http://localhost:5173**

---

## 🔐 Demo Credentials

Use the locally provisioned demo accounts from the database setup. Do not hardcode real credentials in the repository.

| **Role**        | **Username** | **Password** |
|-----------------|--------------|--------------|
| Faculty         | `faculty`    | `YOUR_LOCAL_FACULTY_PASSWORD` |
| Academic Head   | `admin`      | `YOUR_LOCAL_ADMIN_PASSWORD`   |
| IT Admin        | `it`         | `YOUR_LOCAL_IT_PASSWORD`      |

---

## 🎬 5-Minute Demo Flow

### 1️⃣ Login (30 seconds)
- Open `http://localhost:5173`
- Click **Faculty** quick login button (or enter the local demo username/password from setup)
- You'll be redirected to the Dashboard

### 2️⃣ Dashboard Tour (60 seconds)
- **Top KPI Cards**: Total Students, Flagged Students, Avg GPA, Avg Attendance
- **GPA Trend Chart**: Line chart showing semester-wise GPA progression
- **Attendance Trend Chart**: Bar chart showing monthly attendance
- **Risk Summary**: Breakdown of risk reasons (Low GPA, Poor Attendance, Both)
- Try changing **Semester** or **Department** filters

### 3️⃣ Students List (60 seconds)
- Click **Students** in the left sidebar
- Browse 20 mock students in a paginated table
- Use the **search bar** to find students by name or roll number
- Click on **CS2021002 (Priya Patel)** - an at-risk student

### 4️⃣ Student Profile (60 seconds)
- View student details: GPA (2.45), Attendance (68%), Risk status
- Scroll to **Interventions** section - see faculty notes
- Scroll to **Feedback** section - view sentiment analysis results

### 5️⃣ Risk Panel (60 seconds)
- Click **Risk Panel** in the sidebar
- See 3 flagged students with reasons
- Adjust **GPA Threshold** (e.g., 3.0) and **Attendance Threshold** (e.g., 80%)
- Click **Run Risk Detection** button
- Confirm success message and updated list

### 6️⃣ Feedback Analyzer (30 seconds)
- Click **Feedback** in the sidebar
- Paste: `"The course is great and the professor explains concepts very clearly!"`
- Click **Analyze Sentiment**
- See result: **Positive** (Score: ~0.85)
- View **Sentiment Distribution** chart (Positive/Neutral/Negative counts)

### 7️⃣ Admin Panel (30 seconds)
- Logout and login with the local academic-head demo account from setup
- Click **Admin** in the sidebar (visible only to Academic Head/IT)
- View **Courses** table (5 courses)
- View **Faculty** list (3 faculty members)
- See **CSV Import** section

---

## 📊 What You're Seeing (Mock Data)

### Students (20 total)
- 3 **at-risk** students (low GPA and/or poor attendance)
- 17 **good-standing** students
- All from Computer Science department
- Enrolled in Semester 5 (2021 batch)

### Courses (5 total)
- CS301: Database Management Systems
- CS302: Software Engineering
- CS303: Computer Networks
- CS304: Operating Systems
- CS305: Machine Learning

### Risk Flags (3 total)
- **Priya Patel** (CS2021002): GPA 2.45, Attendance 68%
- **Ananya Singh** (CS2021004): GPA 2.15, Attendance 58.5%
- **Kabir Mehta** (CS2021009): GPA 2.35, Attendance 65%

### Features Demonstrated
✅ Dashboard with real-time analytics  
✅ Paginated student list with search  
✅ Detailed student profiles  
✅ Risk detection engine (PL/SQL integration point)  
✅ Sentiment analysis on feedback  
✅ Role-based access control  
✅ Responsive Tailwind CSS design  

---

## 🧪 Running Tests

```bash
npm test
```

Expected output: **2 test suites pass** (LoginPage, Dashboard)

---

## 🎨 Code Quality Checks

### Lint Check
```bash
npm run lint
```

### Format Code
```bash
npm run format
```

---

## 🛠️ Troubleshooting

### Issue: Port 3000 already in use
**Solution**: Kill the process or change the port in `vite.config.ts`:
```typescript
server: {
  port: 3001,  // Change to any available port
}
```

### Issue: White screen after login
**Cause**: MSW not initialized  
**Solution**: Run `npx msw init public/ --save`

### Issue: Mock data not loading
**Cause**: `VITE_MOCK_MODE` not set  
**Solution**: Ensure `.env` has `VITE_MOCK_MODE=true`

### Issue: TypeScript errors in VS Code
**Solution**: Restart VS Code or run:
```bash
npx tsc --noEmit
```

---

## 🔗 Connecting to Real Backend

1. **Stop the dev server** (Ctrl+C)

2. **Edit `.env`**:
   ```env
   VITE_API_BASE_URL=http://localhost:8080/api
   VITE_MOCK_MODE=false
   ```

3. **Restart**: `npm run dev`

4. **Ensure backend is running** at `http://localhost:8080`

---

## 📁 Project Structure Overview

```
frontend/
├── src/
│   ├── api/              # API client & types
│   ├── app/              # App root & routes
│   ├── components/       # Shared components
│   ├── features/         # Feature modules
│   │   ├── auth/         # Login & AuthProvider
│   │   ├── dashboard/    # Dashboard & charts
│   │   ├── students/     # Student list & profile
│   │   ├── risk/         # Risk detection
│   │   ├── interventions/# Interventions
│   │   ├── feedback/     # Sentiment analyzer
│   │   └── admin/        # Admin panel
│   ├── mocks/            # MSW handlers
│   ├── tests/            # Unit tests
│   └── utils/            # Helper functions
├── public/               # Static assets
├── .env                  # Environment config
├── .env.example          # Environment template
├── vite.config.ts        # Vite dev/build config
└── README.md             # Full documentation
```

---

## 🎯 Key Features Checklist

| Feature | Status | Try It |
|---------|--------|--------|
| Role-based Login | ✅ | Login with faculty/admin/it |
| Dashboard KPIs | ✅ | View 4 metrics at top |
| GPA Trend Chart | ✅ | See semester progression |
| Attendance Chart | ✅ | Monthly attendance bars |
| Student Search | ✅ | Search "Priya" in Students |
| Risk Detection | ✅ | Click "Run Risk Detection" |
| Sentiment Analysis | ✅ | Analyze feedback text |
| Interventions Log | ✅ | View in student profile |
| CSV Import UI | ✅ | Admin → CSV Import section |
| Role-based Access | ✅ | Admin menu hidden for faculty |

---

## 📞 Need Help?

**Check the full documentation**: `README.md` in the frontend folder  
**API contract**: See `src/api/endpoints.ts` for backend endpoints  

---

## ✅ Success Checklist

Before demo, ensure:
- [ ] `npm install` completed without errors
- [ ] `npm run dev` starts server on port 3000
- [ ] Login page loads (no white screen)
- [ ] Can login with demo credentials
- [ ] Dashboard shows charts with data
- [ ] Can navigate all pages via sidebar
- [ ] No console errors (except expected warnings)
- [ ] Search works in Students list
- [ ] Run Risk Detection shows success message
- [ ] Feedback sentiment analysis returns result

---

**🎉 You're all set! Enjoy exploring InsightFlow EDU!**

*Generated as part of the InsightFlow EDU DBMS + Software Engineering Mini Project*  
*Frontend: React 18 + TypeScript + Vite | Backend: Spring Boot + Oracle DB*
