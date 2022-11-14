package com.example.jdbcWebappExercise.dao.course;

import java.util.List;

public interface CourseDAO {
    void insertCourse(String courseName, int professorId);
    void deleteCourseBy(int id);
    void updateCourseName(int id, String courseNewName);
    List<String> retrieveAll();
}