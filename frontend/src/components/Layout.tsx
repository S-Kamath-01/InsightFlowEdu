import React from 'react';
import { Header } from './Header';
import { Sidebar } from './Sidebar';
import { Footer } from './Footer';
import { ToastProvider } from './ToastProvider';
import { useAuth } from '@/features/auth/AuthProvider';

type LayoutProps = {
  children?: React.ReactNode;
};

const Layout: React.FC<LayoutProps> = ({ children }) => {
  const { isAuthenticated } = useAuth();

  return (
    <ToastProvider>
      <div className="min-h-screen flex flex-col bg-slate-50 text-slate-900 transition-colors duration-200 dark:bg-slate-950 dark:text-slate-100">
        <Header />
        <div className="flex flex-1">
          {isAuthenticated ? <Sidebar /> : null}
          <main className="flex-1">
            <div className="mx-auto w-full max-w-7xl px-6 pb-12 pt-10 sm:px-8 lg:px-12">{children}</div>
          </main>
        </div>
        <Footer />
      </div>
    </ToastProvider>
  );
};

export default Layout;
