package com.example.jdbcWebappExercise.service.student;

import com.example.jdbcWebappExercise.dao.person.PersonDAO;
import com.example.jdbcWebappExercise.dao.person.PersonDAOImpl;
import com.example.jdbcWebappExercise.dao.student.StudentDAO;
import com.example.jdbcWebappExercise.dao.student.StudentDAOImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private PersonDAO personDAO;
    private StudentDAO studentDAO;

    private static StudentService INSTANCE = new StudentServiceImpl();

    private StudentServiceImpl() {
        studentDAO = StudentDAOImpl.getInstance();
        personDAO = PersonDAOImpl.getInstance();
    }

    public static StudentService getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertStudent(String name, String surname, int age, int yearOfStudy, int enrollmentYear) {
        int personId = personDAO.insertPerson(name, surname, age);
        studentDAO.insertStudent(yearOfStudy, enrollmentYear, personId);
    }

    @Override
    public void deleteStudentBy(int id) {
        studentDAO.deleteStudentBy(id);
    }

    @Override
    public List<String> retrieveFreeStudents() {
        return studentDAO.retrieveFreeStudents();
    }

    @Override
    public List<String> retrieveAll() {
        return studentDAO.retrieveAll();
    }
}
