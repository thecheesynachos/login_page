USE logindata;

CREATE TABLE users (
                     id INT KEY NOT NULL AUTO_INCREMENT,
                     username VARCHAR(20) UNIQUE NOT NULL,
                     password VARCHAR(60) NOT NULL,
                     name VARCHAR(40) NOT NULL
);

INSERT INTO users VALUES (DEFAULT, 'root', '$2a$10$LeFx4xliIv79uuYywzucFeFTzY.nnhO3sAsV0qgqdD4amsI8Kt8Ai', 'Gigadot');