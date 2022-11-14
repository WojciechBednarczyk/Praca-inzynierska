ALTER TABLE mentees
    ADD trainer_id int(255);
ALTER TABLE mentees
    ADD FOREIGN KEY (trainer_id) REFERENCES personal_trainers (personal_trainer_id);