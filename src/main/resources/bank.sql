-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2023 a las 21:40:50
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bank`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `account`
--

CREATE TABLE `account` (
  `iban` varchar(34) NOT NULL,
  `balance` decimal(10,0) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `account`
--

INSERT INTO `account` (`iban`, `balance`, `createdAt`) VALUES
('ES210045123456789', 2000000, '2023-07-12 21:39:12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `account_holders`
--

CREATE TABLE `account_holders` (
  `customer_id` int(11) NOT NULL,
  `account_id` varchar(34) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `account_holders`
--

INSERT INTO `account_holders` (`customer_id`, `account_id`) VALUES
(1, 'ES210045123456789'),
(2, 'ES210045123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birth_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `customer`
--

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `birth_date`) VALUES
(1, 'Alfredo', 'Rueda', '1978-07-19'),
(2, 'David', 'Iruela', '1995-12-05'),
(3, 'Ayoub', 'El Moussaoui', '1997-11-16'),
(5, 'Katy', 'Saratovska', '1997-04-20'),
(6, 'Jesus', 'Fernandez', '1991-08-15'),
(7, 'Daniel', 'Apesteguia', '1999-11-03'),
(8, 'María', 'Cosío', '1988-05-20'),
(9, 'David', 'Pereiras', '1992-12-08'),
(10, 'Marcelo', 'de Souza', '1982-06-11'),
(11, 'David', 'Villa', '1976-01-20'),
(12, 'Diego', 'Suárez', '1994-11-28'),
(13, 'Miguel', 'Vives', '1973-08-01');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`iban`);

--
-- Indices de la tabla `account_holders`
--
ALTER TABLE `account_holders`
  ADD PRIMARY KEY (`customer_id`,`account_id`),
  ADD KEY `account_fk` (`account_id`);

--
-- Indices de la tabla `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `account_holders`
--
ALTER TABLE `account_holders`
  ADD CONSTRAINT `account_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`iban`),
  ADD CONSTRAINT `customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
