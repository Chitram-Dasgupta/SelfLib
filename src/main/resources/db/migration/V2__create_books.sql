CREATE TABLE books (
   isbn VARCHAR(255) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   author_id INTEGER,
   FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);
