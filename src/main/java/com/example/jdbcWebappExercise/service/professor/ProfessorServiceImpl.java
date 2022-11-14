package com.example.jdbcWebappExercise.service.professor;

import com.example.jdbcWebappExercise.dao.person.PersonDAO;
import com.example.jdbcWebappExercise.dao.person.PersonDAOImpl;
import com.example.jdbcWebappExercise.dao.professor.ProfessorDAO;
import com.example.jdbcWebappExercise.dao.professor.ProfessorDAOImpl;

import java.util.List;

public class ProfessorServiceImpl implements ProfessorService {
    private PersonDAO personDAO;
    private ProfessorDAO professorDAO;
    
    private static ProfessorService INSTANCE = new ProfessorServiceImpl();

    private ProfessorServiceImpl() {
        personDAO = PersonDAOImpl.getInstance();
        professorDAO = ProfessorDAOImpl.getInstance();
    }

    public static ProfessorService getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertProfessor(
            String name, String surname, int age,
            String subject, int assumption_year) {
        int personId = personDAO.insertPerson(name, surname, age);
        professorDAO.insertProfessor(subject, assumption_year, personId);
    }

    @Override
    public void deleteProfessorBy(int id) {
       professorDAO.deleteProfessorBy(id);
    }

    @Override
    public void updateProfessorTaughtSubject(int id, String newSubjectName) {
        professorDAO.updateProfessorTaughtSubject(id, newSubjectName);
    }

    @Override
    public List<String> retrieveWorkingProfessors() {
        return professorDAO.retrieveWorkingProfessors();
    }

    @Override
    public List<String> retrieveFreeProfessors() {
        return professorDAO.retrieveFreeProfessors();
    }

    @Override
    public List<String> retrieveAll() {
        return professorDAO.retrieveAll();
    }
}
