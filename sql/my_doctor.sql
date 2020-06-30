-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 30, 2020 lúc 07:00 PM
-- Phiên bản máy phục vụ: 10.1.36-MariaDB
-- Phiên bản PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `my_doctor`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `medical_record`
--

CREATE TABLE `medical_record` (
  `RECORD_ID` bigint(20) NOT NULL,
  `PATIENT_ID` bigint(20) NOT NULL,
  `REASON` text NOT NULL,
  `SYMPTOM` text,
  `GUESS` text,
  `NOTE` text,
  `CREATED_DTM` bigint(20) DEFAULT NULL,
  `DOCTOR_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Đang đổ dữ liệu cho bảng `medical_record`
--

INSERT INTO `medical_record` (`RECORD_ID`, `PATIENT_ID`, `REASON`, `SYMPTOM`, `GUESS`, `NOTE`, `CREATED_DTM`, `DOCTOR_ID`) VALUES
(1, 2, 'khùng', 'hay c?n, hay d?i', 'th?n kinh', 'ghét', 1546186216997, 2),
(2, 1, 'ádasd', 'ádasd', 'ádas', '?âsd', 1546186668597, 2),
(3, 16, 'asdasd', 'zxcasd', 'asdzxc', 'asdasd', 1593527508795, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `patient`
--

CREATE TABLE `patient` (
  `PATIENT_ID` bigint(20) NOT NULL,
  `PATIENT_NAME` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `GENDER` tinyint(1) NOT NULL DEFAULT '1',
  `ADDRESS` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `PHONE` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `BIRTHDAY` bigint(20) DEFAULT NULL,
  `JOB` varchar(255) CHARACTER SET utf8mb4 DEFAULT 'Không',
  `NATION` varchar(255) CHARACTER SET utf8mb4 DEFAULT 'Không',
  `CREATE_DTM` bigint(20) DEFAULT NULL,
  `UPDATE_DTM` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Đang đổ dữ liệu cho bảng `patient`
--

INSERT INTO `patient` (`PATIENT_ID`, `PATIENT_NAME`, `GENDER`, `ADDRESS`, `PHONE`, `BIRTHDAY`, `JOB`, `NATION`, `CREATE_DTM`, `UPDATE_DTM`) VALUES
(1, '\0L\0i\0n\0h\0n\0d', 1, '\0d\0a\0k\0l\0a\0k', '\00\09\07\02\07\00\08\07\08\07', 1543121215087, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543121215087, 1543121215087),
(2, '\0D\0i\0e\0u\0 \0q\0u\0y\0e\0n', 0, '\01\02\03\04\05\06', '\00\01\06\04\09\05\07\06\01\04', 1543165200000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543241885526, 1543241885526),
(3, '\0n\0g\0u\0y\0e\0n\0 \0d\0u\0y', 1, '\0d\0a\0k\0 \0l\0a\0k', '\00\09\07\02\07\00\08\07\08\08', 1543510800000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543335079222, 1543335079222),
(4, '\0?\0d', 1, '\0?\0d\0a', '\0s\0d\0a\0s\0d', 1543251600000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543336698052, 1543336698052),
(5, '\0?\0d\0?\0d\0a\0s', 1, '\0?\0d\0a\0d\0s\0a', '\0s\0d\0a\0s\0d\0d\0s\0a', 1543251600000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543336701436, 1543336701436),
(6, '\0?\0d\0?\0d\0a\0s\0x\0c\0x', 1, '\0?\0d\0a\0d\0s\0a\0c\0x\0c', '\0s\0d\0a\0s\0d\0d\0s\0a\0x\0c', 1543251600000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543336704747, 1543336704747),
(7, '\0c\0x\0c', 1, '\0?\0d\0a\0d\0s\0x\0c\0x\0c\0a', '\0s\0d\0a\0s\0d\0c\0x\0c\0d\0s', 1543251600000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543336708915, 1543336708915),
(8, '\0z\0c', 1, '\0z\0x\0c', '\0z\0x\0c\0c\0z\0x', 1543251600000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543336718859, 1543336718859),
(9, '\0z\0c', 1, '\0z\0x\0c', '\0z\0x\0c\0c\0z\0x', 1543251600000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543336720299, 1543336720299),
(10, '\0z\0c', 1, '\0z\0x\0c', '\0z\0x\0c\0c\0z\0x', 1543251600000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543336721494, 1543336721494),
(11, '\0z\0c', 1, '\0z\0x\0c', '\0z\0x\0c\0c\0z\0x', 1543251600000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543336722771, 1543336722771),
(12, '\0b\0e\0n\0h\0 \0n\0h\0a\0n\0 ', 1, '\01\02\03\04\05\06', '\01\02\03\04\05\06', 1543597200000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543668049613, 1543668049613),
(13, '\0f\0g\0h\0d\0f\0g', 1, NULL, NULL, 1543597200000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543669887619, 1543669887619),
(14, 'Nguyễn Duy Khương', 0, '\0D\0a\0k\0 \0l\0a\0k', '\00\09\01\03\00\07\04\08\04\08', 507920400000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543766015248, 1543766015248),
(15, '\0D\0i\0e\0u\0q\0u\0y\0e\0n\0 ', 0, '\01\02\03\04\05\06', '\01\02\03\04\05\06\07\08', 1038934800000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1543930272295, 1543930272295),
(16, '\0q\0q\0q\0q\0q', 1, '\01\02\03', '\01\02\03\04\05\06', 1593450000000, '\0K\0h\0?\0n\0g', '\0K\0h\0?\0n\0g', 1593527484860, 1593527484860),
(17, 'Nguy?n Duy Khuong', 1, '?ak l?c', '097404', 1593450000000, NULL, NULL, 1593529778932, 1593529778932),
(18, 'Nguy?n V?n A', 1, '??c l?c', '08751', 1593450000000, NULL, NULL, 1593530211410, 1593530211410),
(19, 'Nguy?n V?n B', 1, '??c l?c', '12321', 1593450000000, NULL, NULL, 1593530437121, 1593530437121);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `requestpatient`
--

CREATE TABLE `requestpatient` (
  `REQUEST_ID` bigint(20) NOT NULL,
  `PATIENT_ID` bigint(20) NOT NULL,
  `CREATE_DTM` bigint(20) DEFAULT NULL,
  `STATUS` text
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Đang đổ dữ liệu cho bảng `requestpatient`
--

INSERT INTO `requestpatient` (`REQUEST_ID`, `PATIENT_ID`, `CREATE_DTM`, `STATUS`) VALUES
(1, 1, 1543118923307, NULL),
(2, 2, 1543241885551, NULL),
(3, 3, 1543335079247, NULL),
(4, 4, 1543336698067, NULL),
(5, 5, 1543336701444, NULL),
(6, 6, 1543336704757, NULL),
(7, 7, 1543336708925, NULL),
(8, 8, 1543336718868, NULL),
(9, 9, 1543336720308, NULL),
(10, 10, 1543336721502, NULL),
(11, 11, 1543336722780, NULL),
(12, 1, 1543591567513, NULL),
(13, 1, 1543594304682, NULL),
(14, 2, 1543594849005, NULL),
(15, 3, 1543667338493, NULL),
(16, 12, 1543668049629, NULL),
(17, 2, 1543668098971, NULL),
(18, 13, 1543669887634, NULL),
(19, 1, 1543669920225, NULL),
(20, 7, 1543670072042, NULL),
(21, 4, 1543670131962, NULL),
(22, 6, 1543671888161, NULL),
(23, 1, 1543762666345, NULL),
(24, 2, 1543762670591, NULL),
(25, 3, 1543762678990, NULL),
(26, 14, 1543766015264, NULL),
(27, 1, 1543929739495, NULL),
(28, 2, 1543929742661, NULL),
(29, 3, 1543929746140, NULL),
(30, 15, 1543930272311, NULL),
(31, 1, 1544241647112, NULL),
(32, 2, 1544241650310, NULL),
(33, 3, 1544241653977, NULL),
(34, 5, 1544242232416, NULL),
(35, 7, 1544242236519, NULL),
(36, 9, 1544242240223, NULL),
(37, 12, 1544242245759, NULL),
(38, 1, 1544450923159, NULL),
(39, 2, 1544451176708, NULL),
(40, 1, 1545198124730, NULL),
(41, 3, 1545198134839, NULL),
(42, 1, 1545748420470, NULL),
(43, 2, 1545749151300, NULL),
(44, 3, 1545749270856, NULL),
(45, 1, 1546176630082, 'DONE'),
(46, 2, 1546184902024, 'DONE'),
(47, 16, 1593527484874, 'DONE'),
(48, 1, 1593528823757, 'WAITING'),
(49, 2, 1593528861855, 'WAITING'),
(50, 17, 1593529778947, 'WAITING'),
(51, 18, 1593530211425, 'WAITING'),
(52, 19, 1593530437136, 'WAITING');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `stock`
--

CREATE TABLE `stock` (
  `STOCK_ID` bigint(20) NOT NULL,
  `STOCK_CODE` varchar(10) NOT NULL,
  `STOCK_NAME` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `USER_ID` bigint(20) NOT NULL,
  `DISPLAY_NAME` text,
  `USERNAME` text,
  `PASSWORD` text,
  `GENDER` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`USER_ID`, `DISPLAY_NAME`, `USERNAME`, `PASSWORD`, `GENDER`) VALUES
(2, 'Linh', 'linhnd3', '1bb6e82e3b8c68a7589636eb9173fada', NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `medical_record`
--
ALTER TABLE `medical_record`
  ADD PRIMARY KEY (`RECORD_ID`);

--
-- Chỉ mục cho bảng `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`PATIENT_ID`);

--
-- Chỉ mục cho bảng `requestpatient`
--
ALTER TABLE `requestpatient`
  ADD PRIMARY KEY (`REQUEST_ID`);

--
-- Chỉ mục cho bảng `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`STOCK_ID`),
  ADD UNIQUE KEY `STOCK_CODE` (`STOCK_CODE`),
  ADD UNIQUE KEY `STOCK_NAME` (`STOCK_NAME`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`USER_ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `medical_record`
--
ALTER TABLE `medical_record`
  MODIFY `RECORD_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `patient`
--
ALTER TABLE `patient`
  MODIFY `PATIENT_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `requestpatient`
--
ALTER TABLE `requestpatient`
  MODIFY `REQUEST_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT cho bảng `stock`
--
ALTER TABLE `stock`
  MODIFY `STOCK_ID` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
