USE QuizOnline
GO

INSERT INTO Role(id, name)
VALUES
(
	'1',
	'Admin'
),

(
	'2',
	'Student'
)
GO

INSERT INTO Status(id, name)
VALUES
(
	'1',
	'Active'
),
(
	'0',
	'Inactive'
)
GO

INSERT INTO Subject(id, name)
VALUES
(
	'PRJ321',
	'Java Web'
),

(
	'PRO192',
	'Java OOP'
),

(
	'DBI202',
	'Database'
)
GO

INSERT INTO Account(email, name, password, roleID, status)
VALUES
(
	'tienttse@gmail.com',
	'Tien',
	'6CA13D52CA70C883E0F0BB101E425A89E8624DE51DB2D2392593AF6A84118090',
	'1',
	'1'
),
(
	'bangnbse@gmail.com',
	'Bang',
	'6CA13D52CA70C883E0F0BB101E425A89E8624DE51DB2D2392593AF6A84118090',
	'1',
	'1'
),
(
	'sonpcse@gmail.com',
	'Son',
	'6CA13D52CA70C883E0F0BB101E425A89E8624DE51DB2D2392593AF6A84118090',
	'2',
	'1'
),
(
	'haintse@gmail.com',
	'Hai',
	'6CA13D52CA70C883E0F0BB101E425A89E8624DE51DB2D2392593AF6A84118090',
	'2',
	'1'
),
(
	'khangnhse@gmail.com',
	'Khang',
	'6CA13D52CA70C883E0F0BB101E425A89E8624DE51DB2D2392593AF6A84118090',
	'2',
	'1'
)
GO

INSERT INTO StudentEnroll(userID,subjectID,status)
VALUES
(
	'sonpcse@gmail.com',
	'PRJ321',
	1
),
(
	'haintse@gmail.com',
	'PRO192',
	1
),
(
	'khangnhse@gmail.com',
	'PRJ321',
	1
),
(
	'sonpcse@gmail.com',
	'DBI202',
	1
)
GO

INSERT INTO Question(subjectID, userID, question_content, option1, option2, option3, correctAnswer, status)
VALUES
(
	'PRJ321',
	'bangnbse@gmail.com',
	'Which can help to get your own file?',
	'JButton',
	'JList',
	'JLabel',
	'FileChooser',
	1
),


(
	'DBI202',
	'bangnbse@gmail.com',
	'How many data model do u learn?',
	'1',
	'2',
	'4',
	'3',
	1
),


(
	'PRJ321',
	'bangnbse@gmail.com',
	'Which does command keyword belong DDL?',
	'Insert',
	'Update',
	'Join',
	'Create',
	1
),

(
	'PRO192',
	'tienttse@gmail.com',
	'How many properties of Java OOP?',
	'1',
	'3',
	'5',
	'4',
	1
),


(
	'PRO192',
	'tienttse@gmail.com',
	'Which does not belong  to Java OOP?',
	'interface',
	'class',
	'getter',
	'internal',
	1
),

(
	'PRO192',
	'bangnbse@gmail.com',
	'Which keyword describe inheritence?',
	'New',
	'Nothing',
	'void',
	'extends',
	1
),

(
	'PRO192',
	'tienttse@gmail.com',
	'Type of list?',
	'JButton',
	'JList',
	'Class',
	'Interface',
	1
),

(
	'PRJ321',
	'tienttse@gmail.com',
	'Which method to set attribute?',
	'String.getAttribute()',
	'Session.getAttribute()',
	'Response.setAttribute()',
	'Session.setAttribute()',
	1
),

(
	'PRJ321',
	'tienttse@gmail.com',
	'How many ways to declare Servlet?',
	'0',
	'1',
	'2',
	'3',
	1
),

(
	'PRJ321',
	'bangnbse@gmail.com',
	'Type of attribute',
	'int',
	'float',
	'boolean',
	'object',
	1
),
(
	'PRJ321',
	'bangnbse@gmail.com',
	'Which can help to get your own file?',
	'JButton',
	'JList',
	'JLabel',
	'FileChooser',
	1
),
(
	'PRJ321',
	'tienttse@gmail.com',
	'Which way can not mapping servlet?',
	'Annotation',
	'Mapping in web.xml',
	'using servletConext.addServlet() ',
	'ServletConfig.mappingServlet()',
	1
),
(
	'DBI202',
	'bangnbse@gmail.com',
	'Which is a language for defining data structures?',
	'DML',
	'DSL',
	'DWL',
	'DDL',
	1
),
(
	'DBI202',
	'bangnbse@gmail.com',
	'Which statement is used to remove a relation named R?',
	'DELETE TABLE R',
	'REMOVE TABLE R',
	'GET RID OF TABLE R',
	'DROP TABLE R',
	1
),
(
	'DBI202',
	'tienttse@gmail.com',
	'Which SQL keyword is used to sort the result-set?',
	'SORT',
	'SORT BY',
	'ORDER',
	'ORDER BY',
	1
),
(
	'DBI202',
	'tienttse@gmail.com',
	'Any Create command maybe reserved by using a',
	'DELETE',
	'ROLL BACK',
	'COMMIT',
	'DROP',
	1
),
(
	'PRO192',
	'tienttse@gmail.com',
	'What keyword is used to prevent an object from being serialized?',
	'private',
	'public',
	'volatile',
	'transient',
	1
),
(
	'DBI202',
	'bangnbse@gmail.com',
	'The keyword extends refers to what type of relationship?',
	'has a',
	'was a',
	'were a',
	'is a',
	1
),
(
	'PRO192',
	'tienttse@gmail.com',
	'Which of the following keywords is used to invoke a method in the parent class?',
	'this',
	'final',
	'static',
	'super',
	1
),
(
	'PRO192',
	'tienttse@gmail.com',
	'method adds a specified element to the begining of the vector?',
	'vector.insert()',
	'vector.addOne()',
	'vectort.insertOne()',
	'vector.add()',
	1
),
(
	'PRO192',
	'tienttse@gmail.com',
	'Which access modifier allows you to access method calls in libraries not created in Java?',
	'private',
	'transient',
	'volatile',
	'static',
	1
),
(
	'PRO192',
	'bangnbse@gmail.com',
	'What is the value of x after the following operation is performed? x = 23 % 4;',
	'10',
	'5',
	'2',
	'3',
	1
),
(
	'PRJ321',
	'bangnbse@gmail.com',
	'Which of the following keywords is used to invoke a method in the parent class?',
	'this',
	'final',
	'static',
	'super',
	1
),
(
	'PRO192',
	'tienttse@gmail.com',
	'What method call is used to tell a thread that it has the opportunity to run?',
	'wait()',
	'start()',
	'sleetp()',
	'notify()',
	1
),
(
	'PRO192',
	'bangnbse@gmail.com',
	' the valid primitive data types is',
	'volatile',
	'this',
	'transient',
	'char',
	1
),
(
	'PRJ321',
	'bangnbse@gmail.com',
	'Which of the following elements are used for error handling and are child elements of <web-app> of a deployment descriptor?',
	'<error>',
	'<error_location>',
	'<error-page>',
	'<error_page>',
	1
),
(
	'PRJ321',
	'bangnbse@gmail.com',
	'Which is not method of Http?',
	'delete',
	'trace',
	'post',
	'remvove',
	1
),
(
	'PRJ321',
	'bangnbse@gmail.com',
	'Which is method of Http?',
	'select',
	'create',
	'update',
	'get',
	1
),
(
	'PRJ321',
	'tienttse@gmail.com',
	'Does jsp have  owned session?',
	'NO',
	'Unknown',
	'Maybe',
	'Yes',
	1
),
(
	'PRJ321',
	'bangnbse@gmail.com',
	'Does jsp exist in client-side?',
	'Yes',
	'Unkwnon',
	'Maybe',
	'No',
	1
),
(
	'PRJ321',
	'tienttse@gmail.com',
	'Does url mapping can access many servlets?',
	'No',
	'Just only 3',
	'Just only 1',
	'Yes',
	1
),

(
	'PRJ321',
	'tienttse@gmail.com',
	'Does tag not belong to jsp',
	'set',
	'forEach',
	'if',
	'get',
	1
),
(
	'PRO192',
	'bangnbse@gmail.com',
	'Can a class extend many parent at a time',
	'Yes',
	'Just only 4',
	'Just only 2',
	'NO',
	1
),

(
	'PRO192',
	'tienttse@gmail.com',
	'Can class implement many interfaces?',
	'Only 2',
	'Only 1',
	'Only 5',
	'Yes',
	1
),
(
	'PRO192',
	'bangnbse@gmail.com',
	'Nan is a data type of which language?',
	'Java',
	'C',
	'C#',
	'JS',
	1
),
(
	'DBI202',
	'bangnbse@gmail.com',
	'Which is created first?',
	'None of all',
	'Physical model',
	'Logical model',
	'Conceptual model',
	1
),
(
	'DBI202',
	'bangnbse@gmail.com',
	'Which is not in SQL?',
	'Trigger',
	'Procedure',
	'Transaction',
	'None of all',
	1
),
(
	'DBI202',
	'tienttse@gmail.com',
	'Which is not join?',
	'Cross join',
	'Natural join',
	'Inner join',
	'Disaster join',
	1
),
(
	'DBI202',
	'tienttse@gmail.com',
	'Which is not belong to ACID?',
	'Atomic',
	'Durabily',
	'Isolation',
	'Association',
	1
),
(
	'PRO192',
	'bangnbse@gmail.com',
	'If you declare data type of variable.Type of it is?',
	'private',
	'public',
	'protected',
	'default',
	1
),
(
	'PRJ321',
	'bangnbse@gmail.com',
	'Which is responsible for mapping servlet?',
	'Application',
	'Coder',
	'None of all',
	'Container',
	1
),
(
	'PRJ321',
	'tienttse@gmail.com',
	'How many times of mapping',
	'4',
	'3',
	'1',
	'As many times as it access',
	1
)





