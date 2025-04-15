CREATE DATABASE quanlythuvien;

USE quanlythuvien;

CREATE TABLE Sach (
    maSach VARCHAR(10) PRIMARY KEY,
    tenSach VARCHAR(255) NOT NULL,
    tacGia VARCHAR(255),
    moTa TEXT,
    soLuong INT NOT NULL
);

CREATE TABLE HocSinh (
    maHocSinh VARCHAR(10) PRIMARY KEY,
    hoTen VARCHAR(255) NOT NULL,
    lop VARCHAR(10)
);

CREATE TABLE TheMuon (
    maMuonSach VARCHAR(10) PRIMARY KEY,
    maSach VARCHAR(10),
    maHocSinh VARCHAR(10),
    trangThai BOOLEAN DEFAULT TRUE,
    ngayMuon DATE,
    ngayTra DATE,
    FOREIGN KEY (maSach) REFERENCES Sach(maSach),
    FOREIGN KEY (maHocSinh) REFERENCES HocSinh(maHocSinh)
);

INSERT INTO Sach
VALUES ("S-0001","Số đỏ","Vũ Trọng Phụng","nhân vật chính là Xuân tóc đỏ và ...",10),
("S-0002","Truyện Kiểu","Nguyễn Du","cuộc đời Thúy Kiều ...",15);

INSERT INTO HocSinh
VALUES ('HS-001', 'Nguyễn Văn A', '10A1'),
('HS-002', 'Trần Thị B', '11B2');

SELECT * FROM Sach;
SELECT * FROM HocSinh;