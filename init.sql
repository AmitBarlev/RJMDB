CREATE TABLE users (
    id BIGSERIAL PRIMARY  KEY,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    address TEXT NOT NULL
);

CREATE TABLE rentals (
    id BIGSERIAL PRIMARY KEY,
    userId BIGSERIAL NOT NULL,
    CONSTRAINT fkUserId FOREIGN KEY (userId) REFERENCES users(id),
    movieName TEXT NOT NULL,
    startTime TIMESTAMP NOT NULL,
    endTime TIMESTAMP DEFAULT NULL
);
