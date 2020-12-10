CREATE TABLE employee (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  admission_date DATE NOT NULL,
  base_salary INT NOT NULL,
  finish_date DATE);