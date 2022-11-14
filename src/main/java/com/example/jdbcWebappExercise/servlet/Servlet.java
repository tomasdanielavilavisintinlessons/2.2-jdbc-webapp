package com.example.jdbcWebappExercise.servlet;

import com.example.jdbcWebappExercise.service.course.CourseService;
import com.example.jdbcWebappExercise.service.course.CourseServiceImpl;
import com.example.jdbcWebappExercise.service.course_enrollment.CourseEnrollmentService;
import com.example.jdbcWebappExercise.service.course_enrollment.CourseEnrollmentServiceImpl;
import com.example.jdbcWebappExercise.service.person.PersonService;
import com.example.jdbcWebappExercise.service.person.PersonServiceImpl;
import com.example.jdbcWebappExercise.service.professor.ProfessorService;
import com.example.jdbcWebappExercise.service.professor.ProfessorServiceImpl;
import com.example.jdbcWebappExercise.service.student.StudentService;
import com.example.jdbcWebappExercise.service.student.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        urlPatterns = {
                "/operations-on-professor",
                "/operations-on-people",
                "/operations-on-student",
                "/operations-on-course",
                "/operations-on-enrollments",
                "/research"
        }
)
public class Servlet extends HttpServlet {
    private PersonService personService;
    private ProfessorService professorService;
    private StudentService studentService;
    private CourseService courseService;
    private CourseEnrollmentService courseEnrollmentService;

    public void init() {
        this.personService = PersonServiceImpl.getInstance();
        this.professorService = ProfessorServiceImpl.getInstance();
        this.studentService = StudentServiceImpl.getInstance();
        this.courseService = CourseServiceImpl.getInstance();
        this.courseEnrollmentService = CourseEnrollmentServiceImpl.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        switch (request.getServletPath()) {
            case "/operations-on-people" -> operationsOnPeople(request, response);
            case "/operations-on-professor" -> operationsOnProfessors(request, response);
            case "/operations-on-student" -> operationsOnStudent(request, response);
            case "/operations-on-course" -> operationsOnCourse(request, response);
            case "/operations-on-enrollments" -> operationsOnEnrollments(request, response);
            case "/research" -> researchOperations(request, response);
        }
    }

    private void researchOperations(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getParameter("enrollmentCountButton") != null) {
                int selectedNumberOfCourses = Integer.parseInt(request.getParameter("coursesText"));

                request.setAttribute(
                        "enrollmentCountByStudentList",
                        courseEnrollmentService.retrieveEnrollmentCountByStudent(selectedNumberOfCourses));
            }
            if(request.getParameter("confirmProfessorIdButton") != null) {
                int chosenWorkingProfessorId = Integer.parseInt(request.getParameter("chosenWorkingProfessor"));

                request.setAttribute(
                        "enrolledStudents",
                        courseEnrollmentService
                                .retrieveStudentsEnrolledToACourseTaughtBy(chosenWorkingProfessorId));
                courseEnrollmentService
                        .retrieveStudentsEnrolledToACourseTaughtBy(chosenWorkingProfessorId);
            }

            request.setAttribute(
                    "professorIds",
                    professorService.retrieveWorkingProfessors());
            if(request.getAttribute("enrollmentCountByStudentList") == null) {
                request.setAttribute(
                        "enrollmentCountByStudentList",
                        new ArrayList<String>());
            }
            if(request.getAttribute("enrolledStudents") == null) {
                request.setAttribute(
                        "enrolledStudents",
                        new ArrayList<String>());
            }
            request.getRequestDispatcher("/research.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void operationsOnCourse(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getParameter("insertButton") != null) {
                int professorId = Integer.parseInt(request.getParameter("chosenFreeProfessor"));
                String courseName = request.getParameter("courseNameText");

                courseService.insertCourse(courseName, professorId);
            }
            if(request.getParameter("deleteButton") != null) {
                int courseToDeleteId = Integer.parseInt(request.getParameter("courseToDeleteIdText"));
                courseService.deleteCourseBy(courseToDeleteId);
            }
            if(request.getParameter("updateButton") != null) {
                int courseToUpdateId = Integer.parseInt(request.getParameter("courseToUpdateIdText"));
                String newcourseName = request.getParameter("newCourseNameText");

                courseService.updateCourseName(courseToUpdateId, newcourseName);
            }

            request.setAttribute(
                    "freeProfessors",
                    professorService.retrieveFreeProfessors());
            request.setAttribute(
                    "courses",
                    courseService.retrieveAll());

            request.getRequestDispatcher("/operationsOnCourse.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void operationsOnEnrollments(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getParameter("insertButton") != null) {
                int studentId = Integer.parseInt(request.getParameter("studentIdText"));
                int courseId = Integer.parseInt(request.getParameter("courseIdText"));

                courseEnrollmentService.insertEnrollment(studentId, courseId);
            }
            if(request.getParameter("deleteButton") != null) {
                int studentId = Integer.parseInt(request.getParameter("studentToDeleteIdText"));
                int courseId = Integer.parseInt(request.getParameter("courseToDeleteIdText"));

                courseEnrollmentService.deleteEnrollmentBy(studentId, courseId);
            }

            request.setAttribute(
                    "courses",
                    courseService.retrieveAll()
            );
            request.setAttribute(
                    "students",
                    studentService.retrieveAll()
            );
            request.setAttribute(
                    "enrollments",
                    courseEnrollmentService.retrieveAll()
            );
            request.getRequestDispatcher("/operationsOnEnrollments.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void operationsOnStudent(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getParameter("insertButton") != null) {
                String name = request.getParameter("nameText");
                String surname = request.getParameter("surnameText");
                int age = Integer.parseInt(request.getParameter("ageText"));
                int yearsOfStudy = Integer.parseInt(request.getParameter("yearsText"));
                int enrollmentYear = Integer.parseInt(request.getParameter("enrollmentText"));

                studentService.insertStudent(name, surname, age, yearsOfStudy, enrollmentYear);
            }
            if(request.getParameter("deleteButton") != null) {
                int studentToDeleteId = Integer.parseInt(request.getParameter("studentToDeleteIdText"));
                studentService.deleteStudentBy(studentToDeleteId);
            }

            request.setAttribute(
                    "students",
                    studentService.retrieveAll());
            request.getRequestDispatcher("/operationsOnStudent.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void operationsOnPeople(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getParameter("idButton") != null) {
                int personId = Integer.parseInt(request.getParameter("personIdText"));

                request.setAttribute(
                        "person-info",
                        personService.retrievePersonById(personId));
            }
            if(request.getParameter("deleteButton") != null) {
                int personToDeleteId = Integer.parseInt(request.getParameter("personToDeleteIdText"));
                personService.deletePersonById(personToDeleteId);
            }
            if(request.getParameter("updateButton") != null) {
                int personToUpdate = Integer.parseInt(request.getParameter("personToUpdateIdText"));
                String newName = request.getParameter("newNameText");

                personService.updatePersonName(personToUpdate, newName);
            }

            request.setAttribute(
                    "peopleList",
                    personService.retrieveAll());

            request.getRequestDispatcher("/operationsOnPeople.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void operationsOnProfessors(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getParameter("insertButton") != null) {
                String name = request.getParameter("nameText");
                String surname = request.getParameter("surnameText");
                int age = Integer.parseInt(request.getParameter("ageText"));
                String subject = request.getParameter("subjectText");
                int assumptionYear = Integer.parseInt(request.getParameter("assumptionText"));

                professorService.insertProfessor(name, surname, age,
                        subject, assumptionYear);
            }
            if(request.getParameter("deleteButton") != null) {
                int professorToDeleteId = Integer.parseInt(request.getParameter("professorToDeleteIdText"));
                professorService.deleteProfessorBy(professorToDeleteId);
            }
            if(request.getParameter("updateButton") != null) {
                int professorToUpdate = Integer.parseInt(request.getParameter("professorToUpdateIdText"));
                String newSubjectName = request.getParameter("newSubjectText");

                professorService.updateProfessorTaughtSubject(professorToUpdate, newSubjectName);
            }

            request.setAttribute(
                    "professors",
                    professorService.retrieveAll());
            request.getRequestDispatcher("/operationsOnProfessors.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}