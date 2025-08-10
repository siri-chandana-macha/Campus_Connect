CREATE TABLE users (
	roll_number VARCHAR(50) NOT NULL UNIQUE PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    pwd VARCHAR(255) NOT NULL,
    branch VARCHAR(50) NOT NULL,
    role ENUM('user', 'admin') NOT NULL
);

CREATE TABLE interview_experiences (
    id INT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(50) NOT NULL,
    name VARCHAR(100),
    company VARCHAR(100) NOT NULL,
    package_offered VARCHAR(50) NOT NULL,
    duration VARCHAR(50) NOT NULL,
    questions TEXT NOT NULL,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (roll_number) REFERENCES users(roll_number)
);

CREATE TABLE podcasts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    description TEXT,
    file_path VARCHAR(255),
    upload_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE notes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    file_path VARCHAR(255),
    upload_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE events (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    organizer VARCHAR(255),
    date DATE,
    time VARCHAR(50),
    venue VARCHAR(255),
    description TEXT
);


