/**
 * Application entry point
 * Initializes MSW and renders the App
 */

import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './app/App';
import './app/index.css';

const MOCK_MODE = import.meta.env.VITE_MOCK_MODE === 'true';

async function enableMocking() {
  console.log('[app] VITE_MOCK_MODE =', import.meta.env.VITE_MOCK_MODE);

  if (!MOCK_MODE) {
    console.log('[app] Mock mode disabled');
    return;
  }

  try {
    const { worker } = await import('./mocks/browser');
    await worker.start({ onUnhandledRequest: 'bypass' });
    console.log('[app] MSW worker started (mock mode)');
  } catch (err) {
    console.error('[app] Failed to start MSW worker', err);
  }
}

enableMocking().then(() => {
  ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
      <App />
    </React.StrictMode>
  );
});
