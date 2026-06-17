import axios from 'axios';

const GATEWAY = 'http://localhost:8080';

export const getStudentByCnie = (cnie) =>
  axios.get(`${GATEWAY}/api/students/cnie/${cnie}`);

export const getAllCourses = () =>
  axios.get(`${GATEWAY}/api/course`);

export const getEnrollmentsByCnie = (cnie) =>
  axios.get(`${GATEWAY}/api/enrollment/${cnie}`);

export const enrollStudent = (studentCnie, courseId) =>
  axios.post(`${GATEWAY}/api/enrollment`, { studentCnie, courseId });

export const cancelEnrollment = (enrollmentId) =>
  axios.delete(`${GATEWAY}/api/enrollment/${enrollmentId}`);