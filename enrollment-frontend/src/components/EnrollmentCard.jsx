import React, { useState } from 'react';
import { cancelEnrollment } from '../api/api';

function EnrollmentCard({ enrollment, onCancel }) {
  const [cancelled, setCancelled] = useState(false);

  const handleDelete = async () => {
    try {
      await cancelEnrollment(enrollment.enrollmentId);
      setCancelled(true);
      onCancel(enrollment.enrollmentId);
    } catch (error) {
      alert(error.response?.data || "Erreur lors de l'annulation !");
    }
  };

  return (
    <div className="bg-white border border-gray-200 rounded-xl p-4 flex justify-between items-center shadow-sm">
      
      <div>
        <p className="font-semibold text-gray-800 text-lg">
          {enrollment.courseName}
        </p>
        <p className="text-sm text-gray-500 mt-1">
          Inscrit le : {new Date(enrollment.date).toLocaleDateString('fr-FR')}
        </p>
      </div>

      <button
        onClick={handleDelete}
        disabled={!enrollment.deletable || cancelled}
        className={`px-4 py-2 rounded-lg text-sm font-medium transition-colors
          ${enrollment.deletable && !cancelled
            ? 'bg-red-500 text-white hover:bg-red-600 cursor-pointer'
            : 'bg-gray-200 text-gray-400 cursor-not-allowed'
          }`}
      >
        {cancelled ? 'Annulé' : enrollment.deletable ? 'Annuler' : 'Expiré'}
      </button>

    </div>
  );
}

export default EnrollmentCard;