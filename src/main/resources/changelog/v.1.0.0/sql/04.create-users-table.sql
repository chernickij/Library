CREATE TABLE IF NOT EXISTS users
(
    id         serial       NOT NULL,
    role_id    serial       NOT NULL,
    password   varchar(255) NOT NULL,
    first_name varchar(255),
    last_name  varchar(255),
    email      varchar(255) UNIQUE NOT NULL,
    phone      varchar(255),
    ts_created timestamp    NOT NULL,
    ts_updated timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role (id) ON UPDATE CASCADE
);

-- CREATE SEQUENCE IF NOT EXISTS users_seq;
