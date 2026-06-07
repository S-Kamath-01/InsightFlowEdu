# Contributing

Thanks for helping improve InsightFlow EDU.

## Before You Start
- Review `README.md`, `SECURITY.md`, and the backend/frontend setup docs.
- Use the existing demo flow and avoid large architectural changes unless discussed first.

## Local Setup
```bash
cd frontend
npm install
npm run build
npm test
```

```bash
cd backend/insightflow-backend
mvn test
```

## Suggested Workflow
- Keep changes small and focused.
- Prefer documentation, cleanup, and correctness fixes over large redesigns.
- Do not commit secrets, build output, or local environment files.

## Pull Requests
Include:
- what changed
- why it changed
- how you verified it
- screenshots if the UI changed
