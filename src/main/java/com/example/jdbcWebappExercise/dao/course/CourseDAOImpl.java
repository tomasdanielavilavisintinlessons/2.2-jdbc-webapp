package com.example.jdbcWebappExercise.dao.course;

import com.example.jdbcWebappExercise.connection.ConnectionHandler;
import com.example.jdbcWebappExercise.connection.ConnectionHandlerImpl;
import com.example.jdbcWebappExercise.utils.ResultSetUtility;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private ConnectionHandler connectionHandler;

    private static CourseDAO INSTANCE = new CourseDAOImpl();

    private CourseDAOImpl() {
        this.connectionHandler = ConnectionHandlerImpl.getInstance();
    }

    public static CourseDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertCourse(String courseName, int professorId) {
        try {
            String insertCourse = """
                insert into courses (course_name, professorId) values (?, ?);    
            """;
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(insertCourse);

            updateStatement.setString(1, courseName);
            updateStatement.setInt(2, professorId);

            updateStatement.executeUpdate();

            updateStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCourseBy(int id) {
        try {
            String deleteCourse = "delete from courses where id = ?;";
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(deleteCourse);

            updateStatement.setInt(1, id);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCourseName(int id, String courseNewName) {
        try {
            String updateCourseName = """
                    update courses 
                    set course_name = ?
                    where id = ?;
            """;
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(updateCourseName);

            updateStatement.setString(1, courseNewName);
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
            String query = "select * from courses;";
            PreparedStatement queryStatement = connectionHandler.getConnection().prepareStatement(query);

            result = ResultSetUtility.toArrayList(queryStatement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
