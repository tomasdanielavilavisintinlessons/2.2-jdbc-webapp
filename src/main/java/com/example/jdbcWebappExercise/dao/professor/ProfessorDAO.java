package com.example.jdbcWebappExercise.dao.professor;

import java.util.List;

public interface ProfessorDAO {
    void insertProfessor(String subject, int assumption_year, int personId);
    void deleteProfessorBy(int id);
    void updateProfessorTaughtSubject(int id, String newSubjectName);
    List<String> retrieveWorkingProfessors();
    List<String> retrieveFreeProfessors();
    List<String> retrieveAll();
}
