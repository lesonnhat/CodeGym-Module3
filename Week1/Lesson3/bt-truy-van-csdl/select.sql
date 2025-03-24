USE QuanLySinhVien;

SELECT *
FROM Student;

INSERT INTO Student
VALUES (1,'Nhat','Hai Duong','0984883383',1,11),
(2,'Lien','Hai Phong','0912345678',1,11),
(3,'Phu','Hai Phong','0933555888',1,12),
(4,'Hung','Ha Noi','0322999000',1,12),
(5,'Truong','Hai Duong','0988123987',1,11);

INSERT INTO Class
VALUES (11,'C1124A1','2024-11-30',1),
(12,'C1224G1','2024-12-28',1);

INSERT INTO Subject
VALUES (1,'Toan',5,1),
(2,'Ngu Van',6,1),
(3,'Tieng Anh',4,1);

INSERT INTO Mark
VALUES (1,1,1,70,1),
(2,2,1,60,2),
(3,1,2,80,1),
(4,1,3,90,1),
(5,3,4,50,2),
(6,1,5,75,1);

/* Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’ */

SELECT * 
FROM Student 
WHERE StudentName LIKE 'H%';

/* Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12. */

SELECT * 
FROM Class 
WHERE MONTH(StartDate) = 12;

/* Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5 */

SELECT *
FROM Subject
WHERE Credit BETWEEN 3 AND 5;

/* Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 11 */

UPDATE Student 
SET ClassID = 11 
WHERE StudentName = 'Hung' AND StudentID = 12;

/* Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần */

SELECT s.StudentName, sub.SubName, m.Mark 
FROM Student s
JOIN Mark m ON s.StudentID = m.StudentID
JOIN Subject sub ON m.SubID = sub.SubID
ORDER BY m.Mark DESC, s.StudentName ASC