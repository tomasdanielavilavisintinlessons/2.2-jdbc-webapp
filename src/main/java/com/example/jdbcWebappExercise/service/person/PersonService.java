package com.example.jdbcWebappExercise.service.person;

import java.util.List;

public interface PersonService {
    String retrievePersonById(int id);
    int insertPerson(String name, String surname, int age);
    void deletePersonById(int id);
    void updatePersonName(int id, String name);
    List<String> retrieveAll();
}
