CREATE DATABASE tuyensinh_db;
USE tuyensinh_db;

CREATE TABLE Articles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    coverImage VARCHAR(255),
    summary VARCHAR(500) NOT NULL,
    content TEXT NOT NULL,
    publishedDate DATE NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    subcategory VARCHAR(50) NOT NULL,
    INDEX idx_category (category),
    INDEX idx_publishedDate (publishedDate)
);

-- Tạo bảng Majors
CREATE TABLE Majors (
    majorCode VARCHAR(10) PRIMARY KEY,
    majorName VARCHAR(100) NOT NULL,
    quota INT NOT NULL,
    description TEXT NOT NULL
);

-- Tạo bảng StudentAccounts
CREATE TABLE StudentAccounts (
    citizenId VARCHAR(12) PRIMARY KEY,
    fullName VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    UNIQUE (email)
);

-- Tạo bảng Applications (đã bỏ majorCode, thay score thành DECIMAL(10,2))
CREATE TABLE Applications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    citizenId VARCHAR(12) NOT NULL,
    submissionDate DATETIME NOT NULL,
    status ENUM('pending', 'approved', 'rejected') NOT NULL,
    addr VARCHAR(255) NOT NULL,
    certificate VARCHAR(255),
    score DECIMAL(10,2),
    additionalInfo JSON,
    applicationYear INT NOT NULL DEFAULT 2025,
    FOREIGN KEY (citizenId) REFERENCES StudentAccounts(citizenId),
    INDEX idx_citizenId (citizenId),
    INDEX idx_status (status)
);

-- Tạo bảng AdminAccounts
CREATE TABLE AdminAccounts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE (username)
);