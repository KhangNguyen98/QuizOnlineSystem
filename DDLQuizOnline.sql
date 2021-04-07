CREATE DATABASE QuizOnline
COLLATE SQL_Latin1_General_CP1_CS_AS 
GO
USE QuizOnline
GO

CREATE TABLE Role
(
	id int PRIMARY KEY,
	name varchar(30) NOT NULL,
	note varchar(max)
)
GO

CREATE TABLE Status
(
	id int PRIMARY KEY,
	name varchar(30) NOT NULL,
	note varchar(max)
)
GO

CREATE TABLE Subject
(
	id char(6) PRIMARY KEY,
	name varchar(30) NOT NULL,
)
GO

CREATE TABLE Account
(
	email varchar(320) PRIMARY KEY,
	name varchar(30) NOT NULL,
	password varchar(64) NOT NULL,
	roleID int FOREIGN KEY REFERENCES Role(id) NOT NULL,
	status int REFERENCES Status(id) NOT NULL
)
GO

CREATE TABLE StudentEnroll
(
	id int IDENTITY PRIMARY KEY,
	userID varchar(320) FOREIGN KEY REFERENCES Account(email) NOT NULL,
	subjectID char(6) FOREIGN KEY REFERENCES Subject(id) NOT NULL,
	status int REFERENCES Status(id) NOT NULL
)
GO

CREATE TABLE Quiz
(
	id int IDENTITY PRIMARY KEY,
	userID varchar(320) FOREIGN KEY REFERENCES Account(email) NOT NULL,
	length int NOT NULL,
	createDate bigint NOT NULL,
	status int REFERENCES Status(id) NOT NULL
)
GO

CREATE TABLE Question
(
	id int IDENTITY PRIMARY KEY,
	subjectID char(6) FOREIGN KEY REFERENCES Subject(id),
	userID varchar(320) FOREIGN KEY REFERENCES Account(email) NOT NULL,
	question_content varchar(300) NOT NULL,
	option1 varchar(4000) NOT NULL,
	option2 varchar(4000) NOT NULL,
	option3 varchar(4000) NOT NULL,
	correctAnswer varchar(4000) NOT NULL,
	status int REFERENCES Status(id) NOT NULL
)
GO

CREATE TABLE QuizDetail
(
	id int IDENTITY PRIMARY KEY,
	quizID int FOREIGN KEY REFERENCES Quiz(id) NOT NULL,
	questionID int FOREIGN KEY REFERENCES Question(id) NOT NULL
)
GO

CREATE TABLE ResultInExam
(
	id int IDENTITY PRIMARY KEY,
	userID varchar(320) FOREIGN KEY REFERENCES Account(email) NOT NULL,
	quizID int FOREIGN KEY REFERENCES Quiz(id) NOT NULL,
	submitDate bigint NOT NULL,
	numberOfCorrectAnswer int NOT NULL,
	CONSTRAINT chk_correctAnswers CHECK(numberOfCorrectAnswer >=0),
	totalQuestion  int NOT NULL,
	CONSTRAINT chk_totalQuestionInQuiz CHECK(totalQuestion > 0),
	mark float NOT NULL,
	CONSTRAINT chk_Mark CHECK(mark >= 0),
	status int REFERENCES Status(id) NOT NULL,
	subjectID char(6) FOREIGN KEY REFERENCES Subject(id) NOT NULL
)
GO


CREATE TABLE AnswerByStudent
(
	id int IDENTITY PRIMARY KEY,
	userID varchar(320) FOREIGN KEY REFERENCES Account(email) NOT NULL,
	quizID int FOREIGN KEY REFERENCES Quiz(id) NOT NULL,
	questionID int FOREIGN KEY REFERENCES Question(id) NOT NULL,
	answerByStudent varchar(4000) ,--do dung radio nen co the de null
	correctAnswer varchar(4000) NOT NULL
)
GO

CREATE TABLE SavedQuiz
(
	id int IDENTITY PRIMARY KEY,
	userID varchar(320) FOREIGN KEY REFERENCES Account(email) NOT NULL,
	quizID int FOREIGN KEY REFERENCES Quiz(id) NOT NULL,
	questionID int FOREIGN KEY REFERENCES Question(id) NOT NULL,
	subjectID char(6) FOREIGN KEY REFERENCES Subject(id)
)

CREATE TRIGGER trg_Result ON ResultInExam AFTER INSERT AS
BEGIN
	DELETE SavedQuiz WHERE userID = (SELECT userID FROM inserted WHERE userID = SavedQuiz.userID)
END
GO