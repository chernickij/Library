CREATE TABLE IF NOT EXISTS author
(
    id         serial       NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    ts_created timestamp    NOT NULL,
    ts_updated timestamp,
    PRIMARY KEY (id)
);
