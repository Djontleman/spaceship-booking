CREATE TABLE flights (
id BIGSERIAL PRIMARY KEY,
journey_id INT NOT NULL REFERENCES journeys(id),
spaceship_id INT NOT NULL REFERENCES spaceships(id)
);