CREATE TABLE teacher
(
    teacher_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       VARCHAR(25) NOT NULL,
    surname    VARCHAR(25) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    phone_nr   VARCHAR(15) NOT NULL
);


CREATE TABLE student
(
    student_id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(25) NOT NULL,
    surname         VARCHAR(25) NOT NULL,
    email           VARCHAR(50) NOT NULL,
    phone_nr        VARCHAR(15) NOT NULL,
    date_of_birth   DATE        NOT NULL,
    login_to_app    VARCHAR(20),
    password_to_app VARCHAR(20),
    class_group_id  BIGINT
);


CREATE TABLE class_group
(
    class_group_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name           VARCHAR(25) NOT NULL,
    lvl            VARCHAR(25) NOT NULL,
    teacher_id     BIGINT
);

CREATE TABLE lesson
(
    lesson_id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    lesson_duration INTEGER,
    start           TIMESTAMP,
    class_group     BIGINT
);

CREATE TABLE lesson_student
(
    lesson_id  BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    PRIMARY KEY (lesson_id, student_id),
    CONSTRAINT fk_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (lesson_id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student (student_id)
);

ALTER TABLE lesson
    ADD CONSTRAINT fk_lesson_group FOREIGN KEY (class_group) REFERENCES class_group (class_group_id) ON DELETE RESTRICT;

ALTER TABLE student
    ADD CONSTRAINT fk_student_group FOREIGN KEY (class_group_id) REFERENCES class_group (class_group_id) ON DELETE SET NULL;

ALTER TABLE class_group
    ADD CONSTRAINT fk_teacher_group FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id) ON DELETE SET NULL;