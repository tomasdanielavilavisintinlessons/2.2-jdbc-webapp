create database jdbc-webapp;

use jdbc-webapp;

create table people
(
    id int not null auto_increment,
    name varchar(50),
    surname varchar(50),
    age int,
    primary key (id)
);

create table professors
(
    id int not null auto_increment,
    subject  varchar(50),
    assumption_year year,
    personId int,
    primary key (id),
    foreign key (personId) references people (id)
);

create table students
(
    id int not null auto_increment,
    year_of_study int,
    enrollment_year year,
    personId int,
    primary key (id),
    foreign key (personId) references people (id)
);

create table courses
(
    id int not null auto_increment,
    course_name varchar(50),
    professorId int,
    primary key (id),
    foreign key (professorId) references professors (id)
);

create table course_enrollments
(
    studentId int not null,
    courseId  int not null,
    primary key (studentId, courseId),
    foreign key (studentId) references students (id) on delete cascade,
    foreign key (courseId) references courses (id) on delete cascade
);