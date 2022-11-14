package com.example.jdbcWebappExercise.dao.person;

import com.example.jdbcWebappExercise.connection.ConnectionHandler;
import com.example.jdbcWebappExercise.connection.ConnectionHandlerImpl;
import com.example.jdbcWebappExercise.utils.ResultSetUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private ConnectionHandler connectionHandler = ConnectionHandlerImpl.getInstance();

    private static PersonDAO INSTANCE = new PersonDAOImpl();

    private PersonDAOImpl() {
    }

    public static PersonDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public String retrievePersonById(int id) {
        String result = "";

        try {
            String query = "select * from people where id = ?;";
            PreparedStatement queryStatement = connectionHandler.getConnection().prepareStatement(query);
            queryStatement.setInt(1, id);

            ResultSet personRetrieved = queryStatement.executeQuery();
            while (personRetrieved.next()) {
                result = personRetrieved.getString("id") + " " +
                        personRetrieved.getString("name") + " " +
                        personRetrieved.getString("surname") + " " +
                        personRetrieved.getString("age");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int insertPerson(String name, String surname, int age) {
        int result = 0;

        try {
            String insertPerson = """
                insert into people (name, surname, age) values (?, ?, ?);    
        """;
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(insertPerson, PreparedStatement.RETURN_GENERATED_KEYS);

            updateStatement.setString(1, name);
            updateStatement.setString(2, surname);
            updateStatement.setInt(3, age);

            updateStatement.executeUpdate();
            ResultSet r = updateStatement.getGeneratedKeys();
            result = ResultSetUtility.getFirstGeneratedKeyFrom(updateStatement);

            updateStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void deletePersonById(int id) {
        try {
            String deletePerson = "delete from people where id = ?;";
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(deletePerson);

            updateStatement.setInt(1, id);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePersonName(int id, String name) {
        try {
            String updatePersonName = """
                    update people 
                    set name = ?
                    where id = ?;
            """;
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(updatePersonName);

            updateStatement.setString(1, name);
            updateStatement.setInt(2, id);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> retrieveAll() {
        List<String> result = new ArrayList<>();

        try {
            String query = "select * from people;";
            PreparedStatement queryStatement = connectionHandler.getConnection().prepareStatement(query);

            result = ResultSetUtility.toArrayList(queryStatement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
