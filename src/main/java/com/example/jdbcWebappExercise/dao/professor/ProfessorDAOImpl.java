package com.example.jdbcWebappExercise.dao.professor;

import com.example.jdbcWebappExercise.connection.ConnectionHandler;
import com.example.jdbcWebappExercise.connection.ConnectionHandlerImpl;
import com.example.jdbcWebappExercise.utils.ResultSetUtility;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {
    private ConnectionHandler connectionHandler;

    private static ProfessorDAO INSTANCE = new ProfessorDAOImpl();

    private ProfessorDAOImpl() {
        this.connectionHandler = ConnectionHandlerImpl.getInstance();
    }

    public static ProfessorDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertProfessor(String subject, int assumption_year, int personId) {
        int result = 0;

        try {
            String insertProfessor = """
                insert into professors (subject, assumption_year, personId) values (?, ?, ?);    
        """;
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(insertProfessor);

            updateStatement.setString(1, subject);
            updateStatement.setInt(2, assumption_year);
            updateStatement.setInt(3, personId);

            updateStatement.executeUpdate();

            updateStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProfessorBy(int id) {
        try {
            String deleteProfessor = "delete from professors where id = ?;";
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(deleteProfessor);

            updateStatement.setInt(1, id);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProfessorTaughtSubject(int id, String newSubjectName) {
        try {
            String updatePersonName = """
                    update professors 
                    set subject = ?
                    where id = ?;
            """;
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(updatePersonName);

            updateStatement.setString(1, newSubjectName);
            updateStatement.setInt(2, id);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> retrieveWorkingProfessors() {
        ArrayList<String> result = new ArrayList<>();

        try {
            String query = """
                             select distinct professorId
                             from courses;
            """;
            PreparedStatement queryStatement = connectionHandler.getConnection()
                    .prepareStatement(query);

            result = ResultSetUtility.toArrayList(queryStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<String> retrieveFreeProfessors() {
        ArrayList<String> result = new ArrayList<>();

        try {
            String query = """
                    select id
                    from professors
                    where id not in (
                    select p.id
                    from professors as p join courses as c on c.professorId = p.id);
                    """;
            PreparedStatement queryStatement = connectionHandler.getConnection()
                    .prepareStatement(query);

            result = ResultSetUtility.toArrayList(queryStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<String> retrieveAll() {
        ArrayList<String> result = new ArrayList<>();

        try {
            String query = "select * from professors join people on professors.personId=people.id";
            PreparedStatement queryStatement = connectionHandler.getConnection()
                    .prepareStatement(query);

            result = ResultSetUtility.toArrayList(queryStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
