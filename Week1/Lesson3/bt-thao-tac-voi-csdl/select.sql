/* Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order */

SELECT O.oID, O.oDate, SUM(Od.odQTY * P.pPrice) AS oTotalPrice

FROM Orders O
JOIN OrderDetail Od ON O.oID = Od.oID
JOIN Product P ON Od.pID = P.pID
GROUP BY O.oID, O.oDate;

/* Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách */

SELECT C.Name, C.cAge, P.pName, P.pPrice, SUM(Od.odQTY * P.pPrice) AS oTotalPrice
FROM Customer C
JOIN Orders O ON C.cID = O.cID
JOIN OrderDetail Od ON O.oID = Od.oID
JOIN Product P ON Od.pID = P.pID
GROUP BY C.Name, C.cAge, P.pName, P.pPrice;

/* Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào */

SELECT C.Name
FROM Customer C
LEFT JOIN Orders O ON C.cID = O.cID
WHERE O.oID IS NULL;

/* Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của từng loại được tính = odQTY*pPrice) */

SELECT O.oID, O.oDate, SUM(OD.odQTY * P.pPrice) AS oTotalPrice
FROM Orders O
JOIN OrderDetail OD ON O.oID = OD.oID
JOIN Product P ON OD.pID = P.pID
GROUP BY O.oID, O.oDate;