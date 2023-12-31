CREATE TABLE IF NOT EXISTS role
(
    id         serial             NOT NULL,
    name       varchar(25) UNIQUE NOT NULL,
    ts_created timestamp          NOT NULL,
    ts_updated timestamp,
    PRIMARY KEY (id)
);

