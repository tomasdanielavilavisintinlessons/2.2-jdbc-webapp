package com.example.jdbcWebappExercise.service.course_enrollment;

import com.example.jdbcWebappExercise.dao.course_enrollment.CourseEnrollmentDAO;
import com.example.jdbcWebappExercise.dao.course_enrollment.CourseEnrollmentDAOImpl;

import java.util.List;

public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {
    private CourseEnrollmentDAO courseEnrollmentDAO;

    private static CourseEnrollmentService INSTANCE = new CourseEnrollmentServiceImpl();

    private CourseEnrollmentServiceImpl() {
        this.courseEnrollmentDAO = CourseEnrollmentDAOImpl.getInstance();
    }

    public static CourseEnrollmentService getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertEnrollment(int studentId, int courseId) {
        courseEnrollmentDAO.insertEnrollment(studentId, courseId);
    }

    @Override
    public void deleteEnrollmentBy(int studentId, int courseId) {
        courseEnrollmentDAO.deleteEnrollmentBy(studentId, courseId);
    }

    @Override
    public List<String> retrieveStudentsEnrolledToACourseTaughtBy(int professorId) {
        return courseEnrollmentDAO.retrieveStudentsEnrolledToACourseTaughtBy(professorId);
    }

    @Override
    public List<String> retrieveEnrollmentCountByStudent(int numberOfCourses) {
        return courseEnrollmentDAO.retrieveEnrollmentCountByStudent(numberOfCourses);
    }

    @Override
    public List<String> retrieveAll() {
        return courseEnrollmentDAO.retrieveAll();
    }
}
