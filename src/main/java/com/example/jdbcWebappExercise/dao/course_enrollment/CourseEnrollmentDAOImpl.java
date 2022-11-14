package com.example.jdbcWebappExercise.dao.course_enrollment;

import com.example.jdbcWebappExercise.connection.ConnectionHandler;
import com.example.jdbcWebappExercise.connection.ConnectionHandlerImpl;
import com.example.jdbcWebappExercise.utils.ResultSetUtility;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseEnrollmentDAOImpl implements CourseEnrollmentDAO {
    private ConnectionHandler connectionHandler;

    private static CourseEnrollmentDAO INSTANCE = new CourseEnrollmentDAOImpl();

    private CourseEnrollmentDAOImpl() {
        this.connectionHandler = ConnectionHandlerImpl.getInstance();
    }

    public static CourseEnrollmentDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertEnrollment(int studentId, int courseId) {
        try {
            String insertEnrollment = """
                insert into course_enrollments (studentId, courseId) values (?, ?);    
            """;
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(insertEnrollment, PreparedStatement.RETURN_GENERATED_KEYS);

            updateStatement.setInt(1, studentId);
            updateStatement.setInt(2, courseId);

            updateStatement.executeUpdate();

            updateStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEnrollmentBy(int studentId, int courseId) {
        try {
            String deleteEnrollment = "delete from course_enrollments where studentId = ? and courseId = ?;";
            PreparedStatement updateStatement = connectionHandler.getConnection().prepareStatement(deleteEnrollment);

            updateStatement.setInt(1, studentId);
            updateStatement.setInt(2, courseId);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> retrieveStudentsEnrolledToACourseTaughtBy(int professorId) {
        ArrayList<String> result = new ArrayList<>();

        try {
            String query = """
                             SELECT studentId, p.name as student_name, p.surname as student_surname
                             FROM course_enrollments
                             join students as s on s.id = studentId
                             join people as p on p.id=s.personId
                             where courseId = (
                                    select id
                                    from courses
                                    where professorId = ?);
            """;
            PreparedStatement queryStatement = connectionHandler.getConnection()
                    .prepareStatement(query);

            queryStatement.setInt(1, professorId);

            result = ResultSetUtility.toArrayList(queryStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<String> retrieveEnrollmentCountByStudent(int numberOfCourses) {
        ArrayList<String> result = new ArrayList<>();

        try {
            String query = """
                             SELECT studentId, p.name as student_name, p.surname as student_surname, count(*) as enrollment_count
                             FROM course_enrollments
                             join students as s on s.id = studentId
                             join people as p on p.id=s.personId
                             join courses as c on c.id=courseId
                             group by studentId
                             having enrollment_count = ?;
            """;
            PreparedStatement queryStatement = connectionHandler.getConnection()
                    .prepareStatement(query);

            queryStatement.setInt(1, numberOfCourses);

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
            String query = """
                             SELECT studentId, p.name, p.surname, c.id, c.course_name
                             FROM course_enrollments
                             join students on students.id=studentId
                             join people as p on students.personId=p.id
                             join courses as c on c.id=courseId;
            """;
            PreparedStatement queryStatement = connectionHandler.getConnection()
                    .prepareStatement(query);
            result = ResultSetUtility.toArrayList(queryStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
