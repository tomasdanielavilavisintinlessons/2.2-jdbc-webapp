package com.example.jdbcWebappExercise.dao.course_enrollment;

import java.util.List;

public interface CourseEnrollmentDAO {
    void insertEnrollment(int studentId, int courseId);
    void deleteEnrollmentBy(int studentId, int courseId);
    List<String> retrieveStudentsEnrolledToACourseTaughtBy(int professorId);
    List<String> retrieveEnrollmentCountByStudent(int numberOfCourses);
    List<String> retrieveAll();
}
