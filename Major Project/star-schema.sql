/* 
	Student1 Name : Bhavye Anand Gupta
	Student1 Roll : 2017038
   
	Student2 Name : Raghav Rathi
	Student2 Roll : 2017083
*/

DROP DATABASE IF EXISTS DW;

CREATE DATABASE IF NOT EXISTS DW;

USE DW;


-- Dimension Tables

CREATE TABLE IF NOT EXISTS dimCompany (
	id INT(15) NOT NULL,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dimPosition (
	id INT(15) NOT NULL,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dimJobDescription (
	id INT(15) NOT NULL,
	job_description VARCHAR(1000) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dimStudent (
	id INT(15) NOT NULL,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dimDate (
	date_time DATETIME NOT NULL,
	PRIMARY KEY (date_time)
);


-- Fact Tables

CREATE TABLE IF NOT EXISTS factJobRequirement (
	company_id INT(15) NOT NULL,
	position_id INT(15) NOT NULL,
	jd_id INT(15) NOT NULL,
	date_time DATETIME NOT NULL,

	PRIMARY KEY (company_id, position_id, jd_id, date_time),

	FOREIGN KEY (company_id)
		REFERENCES dimCompany(id),
	FOREIGN KEY (position_id)
		REFERENCES dimPosition(id),
	FOREIGN KEY (jd_id)
		REFERENCES dimJobDescription(id),
	FOREIGN KEY (date_time)
		REFERENCES dimDate(date_time)

	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS factEligibility (
	company_id INT(15) NOT NULL,
	student_id INT(15) NOT NULL,
	date_time DATETIME NOT NULL,

	backlogs INT(2) NOT NULL,
	allowed_backlogs INT(2) NOT NULL,
	student_gpa DECIMAL(4, 2) NOT NULL,
	required_gpa DECIMAL(4, 2) NOT NULL,

	PRIMARY KEY (company_id, student_id, date_time),

	FOREIGN KEY (company_id)
		REFERENCES dimCompany(id),
	FOREIGN KEY (student_id)
		REFERENCES dimStudent(id),
	FOREIGN KEY (date_time)
		REFERENCES dimDate(date_time)

	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS factBenefits (
	company_id INT(15) NOT NULL,
	date_time DATETIME NOT NULL,

	work_hours INT(2) NOT NULL,
	bonus_offered INT(25) NOT NULL,
	paid_overtime INT(25) NOT NULL,

	PRIMARY KEY (company_id, date_time),

	FOREIGN KEY (company_id)
		REFERENCES dimCompany(id),
	FOREIGN KEY (date_time)
		REFERENCES dimDate(date_time)

	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS factSalary (
	company_id INT(15) NOT NULL,
	student_id INT(15) NOT NULL,
	position_id INT(15) NOT NULL,
	date_time DATETIME NOT NULL,

	base_amount INT(25) NOT NULL,
	allowances INT(25) NOT NULL,
	signing_bonus INT(25) NOT NULL,
	joining_bonus INT(25) NOT NULL,
	expected_salary INT(25) NOT NULL,

	required_gpa DECIMAL(4,2) NOT NULL,

	PRIMARY KEY (company_id, student_id, date_time),

	FOREIGN KEY (company_id)
		REFERENCES dimCompany(id),
	FOREIGN KEY (student_id)
		REFERENCES dimStudent(id),
	FOREIGN KEY (position_id)
		REFERENCES dimPosition(id),
	FOREIGN KEY (date_time)
		REFERENCES dimDate(date_time)

	ON UPDATE CASCADE
	ON DELETE RESTRICT
); 

CREATE TABLE IF NOT EXISTS factCity (
	company_id INT(15) NOT NULL,
	student_id INT(15) NOT NULL,
	date_time DATETIME NOT NULL,

	work_city VARCHAR(50) NOT NULL,
	home_city VARCHAR(50) NOT NULL,
	distance DECIMAL(25, 2) NOT NULL,

	PRIMARY KEY (company_id, student_id, date_time),

	FOREIGN KEY (company_id)
		REFERENCES dimCompany(id),
	FOREIGN KEY (student_id)
		REFERENCES dimStudent(id),
	FOREIGN KEY (date_time)
		REFERENCES dimDate(date_time)

	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS factInterviewProcess (
	company_id INT(15) NOT NULL,
	date_time DATETIME NOT NULL,

	pass_rate DECIMAL(5, 2) NOT NULL,
	number_of_rounds INT(2) NOT NULL,
	syllabus VARCHAR(100) NOT NULL,

	PRIMARY KEY (company_id, date_time),

	FOREIGN KEY (company_id)
		REFERENCES dimCompany(id),
	FOREIGN KEY (date_time)
		REFERENCES dimDate(date_time)

	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS factAppearForInteview (
	company_id INT(15) NOT NULL,
	student_id INT(15) NOT NULL,
	date_time DATETIME NOT NULL,

	student_gpa DECIMAL(4, 2) NOT NULL,
	ctc INT(25) NOT NULL,
	work_city VARCHAR(50) NOT NULL,
	is_selected VARCHAR(5) NOT NULL,

	PRIMARY KEY (company_id, student_id, date_time),

	FOREIGN KEY (company_id)
		REFERENCES dimCompany(id),
	FOREIGN KEY (student_id)
		REFERENCES dimStudent(id),
	FOREIGN KEY (date_time)
		REFERENCES dimDate(date_time)

	ON UPDATE CASCADE
	ON DELETE RESTRICT
);
