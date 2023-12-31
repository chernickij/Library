CREATE TABLE IF NOT EXISTS genre
(
    id         serial       NOT NULL,
    name       varchar(255) UNIQUE NOT NULL,
    ts_created timestamp    NOT NULL,
    ts_updated timestamp,
    PRIMARY KEY (id)
);
