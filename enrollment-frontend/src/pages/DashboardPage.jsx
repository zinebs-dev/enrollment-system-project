import React, { useState } from 'react';
import { getEnrollmentsByCnie } from '../api/api';
import EnrollmentCard from '../components/EnrollmentCard';

function DashboardPage() {
  const [cnie, setCnie] = useState('');
  const [enrollments, setEnrollments] = useState([]);
  const [searched, setSearched] = useState(false);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleSearch = async (e) => {
    e.preventDefault();
    setError(null);
    setSearched(false);
    setEnrollments([]);
    const cnieRegex = /^[A-Z]{2}\d{4}$/;

    if (!cnieRegex.test(cnie)) {
      setError("CNIE invalide. Format attendu : 2 lettres + 4 chiffres");
      return;
    }
    if (!cnie) {
      setError("Veuillez entrer un CNIE.");
      return;
    }

    setLoading(true);
    try {
      const res = await getEnrollmentsByCnie(cnie);
      setEnrollments(res.data);
      setSearched(true);
    } catch (err) {
      setError(err.response?.data || "Étudiant introuvable ou aucune inscription.");
      setEnrollments([]);
    } finally {
      setLoading(false);
    }
  };

 const handleCancel = (enrollmentId) => {
  setEnrollments(prev =>
    prev.map(e =>
      e.enrollmentId === enrollmentId
        ? { ...e, deletable: false }  
        : e
    )
  );
};

  return (
    <div className="max-w-2xl mx-auto py-8 px-4">
      <h1 className="text-2xl font-bold text-gray-800 mb-6">
        Mon Dashboard
      </h1>

      <form onSubmit={handleSearch} className="flex gap-3 mb-8">
        <input
          type="text"
          value={cnie}
          onChange={(e) => setCnie(e.target.value)}
          placeholder="Entrez votre CNIE (ex: CD2387)"
          className="flex-1 border border-gray-300 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
        />
        <button
          type="submit"
          disabled={loading}
          className="bg-blue-600 text-white px-6 py-2 rounded-lg font-medium hover:bg-blue-700 transition-colors disabled:opacity-50"
        >
          {loading ? 'Recherche...' : 'Rechercher'}
        </button>
      </form>

      {error && (
        <p className="text-red-500 text-sm mb-4">{error}</p>
      )}

      {searched && enrollments.length === 0 && (
        <p className="text-gray-500 text-sm">
          Aucune inscription trouvée pour ce CNIE.
        </p>
      )}

      <div className="space-y-3">
        {enrollments.map(enrollment => (
          <EnrollmentCard
            key={enrollment.enrollmentId}
            enrollment={enrollment}
            onCancel={handleCancel}
          />
        ))}
      </div>
    </div>
  );
}

export default DashboardPage;