DROP SCHEMA IF EXISTS yoop cascade;
CREATE SCHEMA yoop;

CREATE TABLE yoop.MOVIES (
                             id bigserial,
                             name varchar(150),
                             average_critic_rating double precision,
                             genre varchar(150),
                             created_date timestamp,
                             modified_date timestamp,
                             PRIMARY KEY (id)
);
CREATE INDEX idx_movie_genre ON yoop.MOVIES(genre);

CREATE TABLE yoop.RATINGS (
                              id bigserial,
                              score int,
                              movie_id bigserial references yoop.MOVIES (id),
                              submitted_by varchar(150),
                              created_date timestamp,
                              modified_date timestamp,
                              PRIMARY KEY (id)
);
CREATE INDEX idx_movie_id ON yoop.RATINGS(movie_id);


INSERT INTO
    yoop.MOVIES (name, average_critic_rating, genre, created_date, modified_date)
VALUES
    ('First Grade Math Lessons', 90, 'Math', current_timestamp, current_timestamp),
    ('Second Grade Math Lessons', 95, 'Math', current_timestamp, current_timestamp),
    ('Third Grade Math Lessons', 100, 'Math', current_timestamp, current_timestamp),
    ('First Grade English Lessons', NULL, 'ELA', current_timestamp, current_timestamp),
    ('Fifth Grade English Lessons', NULL, 'ELA', current_timestamp, current_timestamp);

INSERT INTO
    yoop.RATINGS (score, movie_id, submitted_by, created_date, modified_date)
VALUES
    (90, 1, 'Alice Smith', current_timestamp, current_timestamp),
    (95, 2, 'Janet Jackson', current_timestamp, current_timestamp),
    (100, 3, 'John Waters', current_timestamp, current_timestamp);