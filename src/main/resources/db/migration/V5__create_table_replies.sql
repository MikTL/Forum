CREATE TABLE replies (
id INT PRIMARY KEY AUTO_INCREMENT,
message TEXT NOT NULL,
creation_date TIMESTAMP NOT NULL,
topic_id INT NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY (topic_id) REFERENCES topics(id),
FOREIGN KEY (user_id) REFERENCES users(id)
);