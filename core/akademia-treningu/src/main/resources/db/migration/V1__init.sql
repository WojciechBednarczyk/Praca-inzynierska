CREATE TABLE users
(
    user_id       int(255)     NOT NULL AUTO_INCREMENT,
    first_name    varchar(255) NOT NULL,
    second_name   varchar(255) NOT NULL,
    login         varchar(255) NOT NULL UNIQUE,
    password      varchar(255) NOT NULL,
    email         varchar(255) NOT NULL UNIQUE,
    date_of_birth date         NOT NULL,
    location      varchar(255),
    PRIMARY KEY (user_id)
);
CREATE TABLE personal_trainers
(
    personal_trainer_id int(255) NOT NULL AUTO_INCREMENT,
    user_id             int(255) NOT NULL,
    rating              float,
    description         varchar(255),
    PRIMARY KEY (personal_trainer_id)
);
CREATE TABLE mentees
(
    mentee_id           int(255) NOT NULL AUTO_INCREMENT,
    user_id             int(255) NOT NULL,
    weight              int(3),
    height              int(3),
    waist_circumference int(3),
    PRIMARY KEY (mentee_id)
);
CREATE TABLE messages
(
    message_id   int(255)     NOT NULL AUTO_INCREMENT,
    message      varchar(255) NOT NULL,
    date_of_sent date         NOT NULL,
    chat_id      int(255)     NOT NULL,
    user_id      int(255)     NOT NULL,
    PRIMARY KEY (message_id)
);
CREATE TABLE chats
(
    chat_id int(255) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (chat_id)
);
CREATE TABLE users_x_chats
(
    users_x_chat_id int(255) NOT NULL AUTO_INCREMENT,
    user_id         int(255) NOT NULL UNIQUE,
    chat_id         int(255) NOT NULL UNIQUE,
    PRIMARY KEY (users_x_chat_id)
);
CREATE TABLE exercises
(
    exercise_id  int(255)     NOT NULL AUTO_INCREMENT,
    name         varchar(255) NOT NULL,
    description  varchar(1000),
    url          varchar(255),
    rating       int(1)       NOT NULL,
    muscle_group varchar(255) NOT NULL,
    PRIMARY KEY (exercise_id)
);
CREATE TABLE trainings
(
    training_id      int(255)     NOT NULL AUTO_INCREMENT,
    name             varchar(255) NOT NULL,
    date_of_training date         NOT NULL,
    user_id          int(255)     NOT NULL,
    PRIMARY KEY (training_id)
);
CREATE TABLE training_exercises
(
    training_exercise_id int(255) NOT NULL AUTO_INCREMENT,
    exercise_id          int(255) NOT NULL,
    sets                 int(2)   NOT NULL,
    reps                 int(2)   NOT NULL,
    training_id          int(255) NOT NULL,
    PRIMARY KEY (training_exercise_id)
);
CREATE TABLE opinions
(
    opinion_id          int(255)      NOT NULL AUTO_INCREMENT,
    title               varchar(255)  NOT NULL,
    description         varchar(1000) NOT NULL,
    rating              int(1)        NOT NULL,
    mentee_id           int(255)      NOT NULL,
    personal_trainer_id int(255)      NOT NULL,
    PRIMARY KEY (opinion_id)
);
CREATE TABLE posts
(
    post_id int(255)     NOT NULL AUTO_INCREMENT,
    title   varchar(255) NOT NULL,
    content varchar(255) NOT NULL,
    user_id int(255)     NOT NULL,
    PRIMARY KEY (post_id)
);
ALTER TABLE mentees
    ADD CONSTRAINT FKmentees654618 FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE personal_trainers
    ADD CONSTRAINT FKpersonal_t970714 FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE messages
    ADD CONSTRAINT FKmessages760577 FOREIGN KEY (chat_id) REFERENCES chats (chat_id);
ALTER TABLE messages
    ADD CONSTRAINT FKmessages781842 FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE users_x_chats
    ADD CONSTRAINT FKusers_x_ch579002 FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE users_x_chats
    ADD CONSTRAINT FKusers_x_ch36582 FOREIGN KEY (chat_id) REFERENCES chats (chat_id);
ALTER TABLE training_exercises
    ADD CONSTRAINT FKtraining_e908595 FOREIGN KEY (training_id) REFERENCES trainings (training_id);
ALTER TABLE trainings
    ADD CONSTRAINT FKtrainings304977 FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE training_exercises
    ADD CONSTRAINT FKtraining_e161647 FOREIGN KEY (exercise_id) REFERENCES exercises (exercise_id);
ALTER TABLE opinions
    ADD CONSTRAINT FKopinions201209 FOREIGN KEY (mentee_id) REFERENCES mentees (mentee_id);
ALTER TABLE opinions
    ADD CONSTRAINT FKopinions763763 FOREIGN KEY (personal_trainer_id) REFERENCES personal_trainers (personal_trainer_id);
ALTER TABLE posts
    ADD CONSTRAINT FKposts831890 FOREIGN KEY (user_id) REFERENCES users (user_id);
