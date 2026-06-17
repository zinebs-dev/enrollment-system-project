import React, { useState } from 'react';
import EnrollForm from '../components/EnrollForm';
import DashboardPage from '../pages/DashboardPage';

function HomePage() {
  const [activeTab, setActiveTab] = useState('enroll');

  return (
    <div className="min-h-screen bg-gray-50">
      
      <header className="bg-white border-b border-gray-200 shadow-sm">
        <div className="max-w-4xl mx-auto px-4 py-4 flex items-center justify-between">
          <h1 className="text-xl font-bold text-blue-600">
            Système d'Inscription
          </h1>
          <span className="text-sm text-gray-400">ENSAH</span>
        </div>
      </header>

      <div className="max-w-4xl mx-auto px-4 py-6">
        
        <div className="flex gap-2 mb-6 border-b border-gray-200">
          <button
            onClick={() => setActiveTab('enroll')}
            className={`px-5 py-2 text-sm font-medium border-b-2 transition-colors
              ${activeTab === 'enroll'
                ? 'border-blue-600 text-blue-600'
                : 'border-transparent text-gray-500 hover:text-gray-700'
              }`}
          >
            S'inscrire à un cours
          </button>
          <button
            onClick={() => setActiveTab('dashboard')}
            className={`px-5 py-2 text-sm font-medium border-b-2 transition-colors
              ${activeTab === 'dashboard'
                ? 'border-blue-600 text-blue-600'
                : 'border-transparent text-gray-500 hover:text-gray-700'
              }`}
          >
            Mon Dashboard
          </button>
        </div>

        {activeTab === 'enroll' && (
          <div className="max-w-lg mx-auto">
            <EnrollForm onEnrollSuccess={() => setActiveTab('dashboard')} />
          </div>
        )}

        {activeTab === 'dashboard' && (
          <DashboardPage />
        )}

      </div>
    </div>
  );
}

export default HomePage;