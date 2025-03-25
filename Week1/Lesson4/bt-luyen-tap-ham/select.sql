Select *
From Mark;

/* Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất. */

SELECT SubID, SubName, Credit
FROM Subject
GROUP BY SubID, SubName, Credit
HAVING AVG(Credit) >= ALL (SELECT Credit FROM Subject GROUP BY SubID);

/* Hiển thị các thông tin môn học có điểm thi lớn nhất. */

SELECT Sub.SubID, Sub.SubName, MAX(M.Mark) AS MaxMark
FROM Subject Sub
JOIN Mark M ON Sub.SubID = M.SubID
GROUP BY Sub.SubID, Sub.SubName
HAVING MAX(M.Mark) = (
    SELECT MAX(Mark) FROM Mark
);

/* Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần */

SELECT S.StudentID, S.StudentName, AVG(M.Mark) AS AvgMark
FROM Student S
JOIN Mark M ON S.StudentID = M.StudentID
GROUP BY S.StudentID, S.StudentName
ORDER BY AVG(M.Mark) DESC;