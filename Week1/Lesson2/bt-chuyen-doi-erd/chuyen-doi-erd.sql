create database QuanLyDonHang;

use QuanLyDonHang;

create table PhieuXuat (
SoPX int not null primary key,
NgayXuat datetime not null
);

create table VatTu (
MaVT int not null primary key,
TenVT varchar(50) not null
);

create table PhieuXuat_VatTu (
SoPX int not null,
MaVT int not null,
DGXuat float not null,
SLXuat int not null,
primary key (SoPX, MaVT),
foreign key (SoPX) references PhieuXuat(SoPX),
foreign key (MaVT) references VatTu(MaVT)
);

create table PhieuNhap (
SoPN int not null primary key,
NgayNhap datetime not null
);

create table PhieuNhap_VatTu (
SoPN int not null,
MaVT int not null,
DGNhap float not null,
SLNhap int not null,
primary key (SoPN, MaVT),
foreign key (SoPN) references PhieuNhap(SoPN),
foreign key (MaVT) references VatTu(MaVT)
);

create table NhaCC (
MaNCC int not null primary key,
TenNCC varchar(50) not null,
DiaChi varchar(200) not null,
SDT varchar(10) not null
);

create table DonDH (
SoDH int not null primary key,
NgayDH datetime not null,
MaNCC int not null,
foreign key (MaNCC) references NhaCC(MaNCC)
);

create table DonDH_VatTu (
SoDH int not null,
MaVT int not null,
primary key (SoDH, MaVT),
foreign key (SoDH) references DonDH(SoDH),
foreign key (MaVT) references VatTu(MaVT)
);