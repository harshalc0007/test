DROP TABLE IF EXISTS billionaires;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  dateOfBirth VARCHAR(250)
);

CREATE TABLE address (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  line1 VARCHAR(250) NOT NULL,
  line2 VARCHAR(250),
  city  VARCHAR(250),
  state VARCHAR(250),
  country VARCHAR(250),
  zip_code INT,
  emp_id INT
);


