package com.example.jdbcWebappExercise.dao.student;

import java.util.List;

public interface StudentDAO {
    void insertStudent(int yearOfStudy, int enrollmentYear, int personId);
    void deleteStudentBy(int id);
    List<String> retrieveFreeStudents();
    List<String> retrieveAll();
}
