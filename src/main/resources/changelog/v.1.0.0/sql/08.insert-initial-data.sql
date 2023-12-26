INSERT INTO privilege(id, name, ts_created)
VALUES (1, 'MANAGE_USER', current_timestamp),
       (2, 'GET_USER', current_timestamp),
       (3, 'MANAGE_PRIVILEGE', current_timestamp),
       (4, 'GET_PRIVILEGE', current_timestamp),
       (5, 'MANAGE_ROLE', current_timestamp),
       (6, 'GET_ROLE', current_timestamp),
       (7, 'MANAGE_BOOK', current_timestamp),
       (8, 'GET_BOOK', current_timestamp),
       (9, 'MANAGE_AUTHOR', current_timestamp),
       (10, 'GET_AUTHOR', current_timestamp),
       (11, 'MANAGE_GENRE', current_timestamp),
       (12, 'GET_GENRE', current_timestamp);

INSERT INTO role(id, name, ts_created)
VALUES (1, 'ADMIN', current_timestamp),
       (2, 'MANAGER', current_timestamp),
       (3, 'USER', current_timestamp);

INSERT INTO roles_privileges(privilege_id, role_id, ts_created)
VALUES (1, 1, current_timestamp),
       (2, 1, current_timestamp),
       (3, 1, current_timestamp),
       (4, 1, current_timestamp),
       (5, 1, current_timestamp),
       (6, 1, current_timestamp),
       (7, 2, current_timestamp),
       (8, 2, current_timestamp),
       (9, 2, current_timestamp),
       (10, 2, current_timestamp),
       (11, 2, current_timestamp),
       (12, 2, current_timestamp),
       (8, 3, current_timestamp),
       (10, 3, current_timestamp),
       (12, 3, current_timestamp);

INSERT INTO users(id, role_id, password, first_name, last_name, email, phone, ts_created)
VALUES (1, 1, 'admin', 'ADMIN', 'ADMIN', 'admin@gmail.com', '+375291234567', current_timestamp);


