package com.example.jdbcWebappExercise.service.course;

import java.util.List;

public interface CourseService {
    void insertCourse(String courseName, int professorId);
    void deleteCourseBy(int id);
    void updateCourseName(int id, String courseNewName);
    List<String> retrieveAll();
}
