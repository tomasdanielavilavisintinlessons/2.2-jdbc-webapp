package com.example.jdbcWebappExercise.service.professor;

import java.util.List;

public interface ProfessorService {
    void insertProfessor(String name, String surname, int age, String subject, int assumption_year);
    void deleteProfessorBy(int id);
    void updateProfessorTaughtSubject(int id, String newSubjectName);
    List<String> retrieveWorkingProfessors();
    List<String> retrieveFreeProfessors();
    List<String> retrieveAll();
}
