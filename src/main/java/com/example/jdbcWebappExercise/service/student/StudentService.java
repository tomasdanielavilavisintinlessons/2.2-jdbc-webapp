package com.example.jdbcWebappExercise.service.student;

import java.util.List;

public interface StudentService {
    void insertStudent(String name, String surname, int age,
                       int yearOfStudy, int enrollmentYear);
    void deleteStudentBy(int id);
    List<String> retrieveFreeStudents();
    List<String> retrieveAll();
}
