CREATE TABLE spaceships (
id BIGSERIAL PRIMARY KEY,
call_sign TEXT NOT NULL,
generic_spaceship_id INT NOT NULL REFERENCES generic_spaceships(id)
);