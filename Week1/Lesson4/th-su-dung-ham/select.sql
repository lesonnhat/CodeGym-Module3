Select *
From Mark;

/* Sử dụng hàm count để hiển thị số lượng sinh viên ở từng nơi */

SELECT Address, COUNT(Address) AS Count
FROM Student
GROUP BY Address;

/* Tính điểm trung bình các môn học của mỗi học viên bằng cách sử dụng hàm AVG */

SELECT S.StudentId,S.StudentName, AVG(Mark)
FROM Student S join Mark M on S.StudentId = M.StudentId
GROUP BY S.StudentId, S.StudentName;

/* Hiển thị những bạn học viên co điểm trung bình các môn học lớn hơn 15 */

SELECT S.StudentId, S.StudentName, AVG(Mark) AS Avg
FROM Student S join Mark M on S.StudentId = M.StudentId
GROUP BY S.StudentId, S.StudentName
HAVING Avg > 15;

/* Hiển thị thông tin các học viên có điểm trung bình lớn nhất */

SELECT S.StudentId, S.StudentName, AVG(Mark)
FROM Student S join Mark M on S.StudentId = M.StudentId
GROUP BY S.StudentId, S.StudentName
HAVING AVG(Mark) >= ALL (SELECT AVG(Mark) FROM Mark GROUP BY Mark.StudentId);