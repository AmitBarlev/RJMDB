CREATE TABLE users (
    id BIGSERIAL PRIMARY  KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    address TEXT NOT NULL
);

CREATE TABLE rentals (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    movie_name TEXT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP DEFAULT NULL
);
