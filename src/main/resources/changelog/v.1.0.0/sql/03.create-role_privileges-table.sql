CREATE TABLE IF NOT EXISTS roles_privileges
(
    privilege_id integer   NOT NULL,
    role_id      integer   NOT NULL,
    ts_created   timestamp NOT NULL,
    ts_updated   timestamp,
    FOREIGN KEY (privilege_id) REFERENCES privilege (id),
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE,
    PRIMARY KEY (privilege_id, role_id)
);
