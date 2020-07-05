-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 05, 2020 lúc 12:25 PM
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
  `REASON` text CHARACTER SET utf8mb4 NOT NULL,
  `SYMPTOM` text CHARACTER SET utf8mb4,
  `GUESS` text CHARACTER SET utf8mb4,
  `NOTE` text CHARACTER SET utf8mb4,
  `CREATED_DTM` bigint(20) DEFAULT NULL,
  `DOCTOR_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Đang đổ dữ liệu cho bảng `medical_record`
--

INSERT INTO `medical_record` (`RECORD_ID`, `PATIENT_ID`, `REASON`, `SYMPTOM`, `GUESS`, `NOTE`, `CREATED_DTM`, `DOCTOR_ID`) VALUES
(4, 21, 'Nguyễn Duy Khương', 'Nguyễn Duy Khương', 'Nguyễn Duy Khương', 'Nguyễn Duy Khương', 1593703368395, 2),
(5, 23, 'dsaas', 'đá', 'ádasd', 'ád', 1593918983106, 2);

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
(21, 'Nguyễn Duy Khương', 1, 'Nguyễn Duy Khương', 'Nguyễn Duy Khương', 1593622800000, '', '', 1593703348884, 1593703348884),
(22, 'Nguyễn Duy Linh', 1, 'Nguyễn Duy Linh', 'Nguyễn Duy Linh', 1593622800000, 'Nguyễn Duy Linh', 'Nguyễn Duy Linh', 1593703567483, 1593703567483),
(23, 'Nguyễn Duy Linh', 1, 'Nguyễn Duy Linh', 'Nguyễn Duy Linh', 1593882000000, 'Nguyễn Duy Linh', 'Nguyễn Duy Linh', 1593918966292, 1593918966292);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `requestpatient`
--

CREATE TABLE `requestpatient` (
  `REQUEST_ID` bigint(20) NOT NULL,
  `PATIENT_ID` bigint(20) NOT NULL,
  `CREATE_DTM` bigint(20) DEFAULT NULL,
  `STATUS` text CHARACTER SET utf8mb4
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Đang đổ dữ liệu cho bảng `requestpatient`
--

INSERT INTO `requestpatient` (`REQUEST_ID`, `PATIENT_ID`, `CREATE_DTM`, `STATUS`) VALUES
(54, 21, 1593703348902, 'DONE'),
(55, 22, 1593703567500, 'WAITING'),
(56, 23, 1593918966316, 'DONE');

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
  MODIFY `RECORD_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `patient`
--
ALTER TABLE `patient`
  MODIFY `PATIENT_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `requestpatient`
--
ALTER TABLE `requestpatient`
  MODIFY `REQUEST_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
