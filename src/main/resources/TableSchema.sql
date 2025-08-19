CREATE TABLE IF NOT EXISTS Violations(
	id VARCHAR(10) PRIMARY KEY,
  	violation_date DATE,
  	violation_code VARCHAR(10),
  	violation_status VARCHAR(10),
  	violation_description TEXT,
  	violation_inspector_comments TEXT,
  	address VARCHAR(100)
);

CREATE INDEX IF NOT EXISTS violations_address_index ON Violations(address);

CREATE TABLE Scofflaws(
	record_id VARCHAR(30) PRIMARY KEY,
  	address VARCHAR(100),
  	building_list_date DATE
);

CREATE INDEX IF NOT EXISTS scofflaws_address_index ON Scofflaws(address);
CREATE INDEX IF NOT EXISTS scofflaws_list_date_index ON Scofflaws(building_list_date);

CREATE TABLE IF NOT EXISTS Comments (
  	comment_id SERIAL PRIMARY KEY,
	author VARCHAR(15),
  	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  	address VARCHAR(100),
  	comment TEXT
);