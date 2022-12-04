CREATE TABLE requests
(
    request_id          int primary key,
    mentee_id           int,
    personal_trainer_id int
);
ALTER TABLE requests
    ADD FOREIGN KEY (mentee_id) REFERENCES mentees (mentee_id),
    ADD FOREIGN KEY (personal_trainer_id) references personal_trainers (personal_trainer_id);