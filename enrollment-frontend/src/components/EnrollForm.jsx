import React, { useState, useEffect } from 'react';
import { getAllCourses, enrollStudent } from '../api/api';

function EnrollForm({ onEnrollSuccess }) {
  const [courses, setCourses] = useState([]);
  const [cnie, setCnie] = useState('');
  const [courseId, setCourseId] = useState('');
  const [message, setMessage] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    getAllCourses()
      .then(res => setCourses(res.data))
      .catch(() => setError("Impossible de charger les cours."));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage(null);
    setError(null);
   const cnieRegex = /^[A-Z]{2}\d{4}$/;

    if (!cnieRegex.test(cnie)) {
      setError("CNIE invalide. Format attendu : 2 lettres + 4 chiffres");
      return;
    }
    if (!cnie || !courseId) {
      setError("Veuillez remplir tous les champs.");
      return;
    }

    setLoading(true);
    try {
      await enrollStudent(cnie, parseInt(courseId));
      setMessage("Inscription réussie !");
      setCnie('');
      setCourseId('');
      if (onEnrollSuccess) onEnrollSuccess();
    } catch (err) {
      setError(err.response?.data || "Erreur lors de l'inscription.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="bg-white border border-gray-200 rounded-xl p-6 shadow-sm">
      <h2 className="text-xl font-semibold text-gray-800 mb-4">
        Nouvelle inscription
      </h2>

      <form onSubmit={handleSubmit} className="space-y-4">
        
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            CNIE de l'étudiant
          </label>
          <input
            type="text"
            value={cnie}
            onChange={(e) => setCnie(e.target.value)}
            placeholder="Ex: CD2387"
            className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Cours
          </label>
          <select
            value={courseId}
            onChange={(e) => setCourseId(e.target.value)}
            className="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
          >
            <option value="">-- Sélectionner un cours --</option>
            {courses.map(course => (
              <option key={course.id} value={course.id}>
                {course.title} ({course.credits} crédits)
              </option>
            ))}
          </select>
        </div>

        {message && (
          <p className="text-green-600 text-sm font-medium">{message}</p>
        )}
        {error && (
          <p className="text-red-500 text-sm font-medium">{error}</p>
        )}

        <button
          type="submit"
          disabled={loading}
          className="w-full bg-blue-600 text-white py-2 rounded-lg font-medium hover:bg-blue-700 transition-colors disabled:opacity-50"
        >
          {loading ? 'Inscription en cours...' : "S'inscrire"}
        </button>

      </form>
    </div>
  );
}

export default EnrollForm;