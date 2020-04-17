CREATE TABLE student (
                         id INT NOT NULL,
                         name VARCHAR(50),
                         PRIMARY KEY(id)
)ENGINE = InnoDB CHARSET = utf8mb4;

CREATE TABLE teacher (
                         id INT NOT NULL,
                         name VARCHAR(50),
                         PRIMARY KEY(id)
)ENGINE = InnoDB CHARSET = utf8mb4;

CREATE TABLE subject (
                         id INT NOT NULL,
                         name VARCHAR(50),
                         PRIMARY KEY(id)
)ENGINE = InnoDB CHARSET = utf8mb4;

CREATE TABLE course (
                        no INT NOT NULL AUTO_INCREMENT,
                        student_id INT ,
                        teacher_id INT ,
                        subject_id INT ,
                        score FLOAT,
                        PRIMARY KEY(no)
)ENGINE  = InnoDB CHARSET = utf8mb4;

CREATE TABLE teacher_subject (
                                no Int,
                                 teacher_id INT ,
                                 subject_id INT,
                                 PRIMARY KEY (no)
)ENGINE  = InnoDB CHARSET = utf8mb4;


CREATE TABLE login (
                       id VARCHAR(50) NOT NULL,
                       passwords VARCHAR(50) NOT NULL,
                       authority VARCHAR(50) NOT NULL
)ENGINE = InnoDB CHARSET = utf8mb4;