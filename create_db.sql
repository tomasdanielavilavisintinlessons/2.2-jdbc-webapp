create database jdbc-webapp;

use jdbc-webapp;

CREATE TABLE `course_enrollments` (
    `studentId` int DEFAULT NULL,
    `courseId` int DEFAULT NULL,
    UNIQUE KEY `studentId` (`studentId`,`courseId`),
    KEY `courseId` (`courseId`),
    CONSTRAINT `course_enrollments_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `students` (`id`) ON DELETE CASCADE,
    CONSTRAINT `course_enrollments_ibfk_2` FOREIGN KEY (`courseId`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `courses` (
    `id` int NOT NULL AUTO_INCREMENT,
    `course_name` varchar(50) DEFAULT NULL,
    `professorId` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `professorId` (`professorId`),
    CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`professorId`) REFERENCES `professors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `people` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    `surname` varchar(50) DEFAULT NULL,
    `age` int DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `professors` (
    `id` int NOT NULL AUTO_INCREMENT,
    `subject` varchar(50) DEFAULT NULL,
    `assumption_year` year DEFAULT NULL,
    `personId` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `personId` (`personId`),
    CONSTRAINT `professors_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `people` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `students` (
    `id` int NOT NULL AUTO_INCREMENT,
    `year_of_study` int DEFAULT NULL,
    `enrollment_year` year DEFAULT NULL,
    `personId` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `personId` (`personId`),
    CONSTRAINT `students_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `people` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci