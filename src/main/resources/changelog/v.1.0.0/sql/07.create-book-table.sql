CREATE TABLE IF NOT EXISTS book
(
    id          serial              NOT NULL,
    author_id   serial              NOT NULL,
    genre_id    serial              NOT NULL,
    isbn        varchar(255) UNIQUE NOT NULL,
    name        varchar(255)        NOT NULL,
    description varchar(255),
    ts_created  timestamp           NOT NULL,
    ts_updated  timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author (id) ON UPDATE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre (id) ON UPDATE CASCADE
)
