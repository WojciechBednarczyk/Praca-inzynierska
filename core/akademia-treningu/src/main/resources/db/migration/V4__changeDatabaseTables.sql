ALTER TABLE messages
    ADD user_sender_id int(255);
ALTER TABLE messages
    ADD FOREIGN KEY (user_sender_id) REFERENCES users (user_id);
ALTER TABLE messages
    RENAME COLUMN user_id to user_receiver_id;
