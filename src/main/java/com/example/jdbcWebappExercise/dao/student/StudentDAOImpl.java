package com.example.jdbcWebappExercise.dao.student;

import com.example.jdbcWebappExercise.connection.ConnectionHandler;
import com.example.jdbcWebappExercise.connection.ConnectionHandlerImpl;
import com.example.jdbcWebappExercise.utils.ResultSetUtility;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private ConnectionHandler connectionHandler;

    private static StudentDAO INSTANCE = new StudentDAOImpl();

    private StudentDAOImpl() {
        this.connectionHandler = ConnectionHandlerImpl.getInstance();
    }

    public static StudentDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertStudent(int yearOfStudy, int enrollmentYear, int personId) {
        try {
            String insertStudent = """
                insert into students (year_of_study, enrollment_year, personId) values (?, ?, ?);
            """;
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(insertStudent);

            updateStatement.setInt(1, yearOfStudy);
            updateStatement.setInt(2, enrollmentYear);
            updateStatement.setLong(3, personId);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudentBy(int id) {
        try {
            String deleteStudent = "delete from students where id = ?;";
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(deleteStudent);

            updateStatement.setInt(1, id);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> retrieveFreeStudents() {
        ArrayList<String> result = new ArrayList<>();

        try {
            String query = """
                            select *
                            from students
                            where id not in (
                                select s.id
                                from students as s 
                                join course_enrollments as c on c.studentId = s.id
                            );  
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
            String query = "select * from students join people on students.personId=people.id";
            PreparedStatement queryStatement = connectionHandler.getConnection()
                    .prepareStatement(query);

            result = ResultSetUtility.toArrayList(queryStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
