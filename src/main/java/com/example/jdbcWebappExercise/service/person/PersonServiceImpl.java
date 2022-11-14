package com.example.jdbcWebappExercise.service.person;

import com.example.jdbcWebappExercise.dao.person.PersonDAO;
import com.example.jdbcWebappExercise.dao.person.PersonDAOImpl;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private PersonDAO personDAO = PersonDAOImpl.getInstance();

    private static PersonService INSTANCE = new PersonServiceImpl();

    private PersonServiceImpl() {
    }

    public static PersonService getInstance() {
        return INSTANCE;
    }

    @Override
    public String retrievePersonById(int id) {
        return personDAO.retrievePersonById(id);
    }

    @Override
    public int insertPerson(String name, String surname, int age) {
        return personDAO.insertPerson(name, surname, age);
    }

    @Override
    public void deletePersonById(int id) {
        personDAO.deletePersonById(id);
    }

    @Override
    public void updatePersonName(int id, String name) {
        personDAO.updatePersonName(id, name);
    }

    @Override
    public List<String> retrieveAll() {
        return personDAO.retrieveAll();
    }
}
