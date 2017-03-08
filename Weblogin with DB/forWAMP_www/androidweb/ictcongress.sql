-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 02, 2017 at 02:06 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ictcongress`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_student`
--

CREATE TABLE IF NOT EXISTS `tbl_student` (
  `idno` varchar(10) NOT NULL,
  `familyname` varchar(50) NOT NULL,
  `givenname` varchar(50) NOT NULL,
  `course` varchar(10) NOT NULL,
  `yearlevel` varchar(5) NOT NULL,
  `campus` varchar(10) NOT NULL,
  `attended` int(11) NOT NULL,
  PRIMARY KEY (`idno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_student`
--

INSERT INTO `tbl_student` (`idno`, `familyname`, `givenname`, `course`, `yearlevel`, `campus`, `attended`) VALUES
('0001', 'DURANO', 'DENNIS', 'BSIT', '5', 'MAIN', 1),
('0002', 'HELLO', 'WORLD', 'BSIT', '4', 'MAIN', 0),
('0003', 'ALPHA', 'BRAVO', 'BSCS', '3', 'UCLM', 0),
('0004', 'CHARLIE', 'DELTA', 'ACT', '2', 'MAIN', 0),
('0006', 'WHISKEY', 'TANGO', 'ACT', '1', 'MAIN', 0),
('0007', 'HOTEL', 'INDIA', 'ACT', '2', 'UCLM', 0);
