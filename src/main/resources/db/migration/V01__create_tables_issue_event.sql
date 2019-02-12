CREATE TABLE issue (
 id SERIAL PRIMARY KEY,
 title TEXT,
 state TEXT
);

CREATE TABLE event (
 id SERIAL PRIMARY KEY,
 action TEXT,
 fk_issue INTEGER,
 created_at TIMESTAMP,
 updated_at TIMESTAMP,
 FOREIGN KEY (fk_issue) REFERENCES issue(id)
);