CREATE TABLE IF NOT EXISTS book_info
(
    id          serial    NOT NULL,
    book_id     serial    NOT NULL,
    start_time  timestamp,
    finish_time timestamp,
    ts_created  timestamp NOT NULL,
    ts_updated  timestamp,
    PRIMARY KEY (id)
)
