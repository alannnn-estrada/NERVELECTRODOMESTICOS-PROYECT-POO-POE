-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-11-2022 a las 03:18:07
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6
-- 

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nervele`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `CveCliente` int(3) NOT NULL,
  `NomCliente` varchar(30) NOT NULL,
  `DireCliente` varchar(50) NOT NULL,
  `TelCliente` int(10) NOT NULL,
  `CorreoCliente` varchar(30) NOT NULL,
  `RFC` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientestienda`
--

CREATE TABLE `clientestienda` (
  `IDTienda` int(11) NOT NULL,
  `NomCliente` varchar(255) NOT NULL,
  `Direccion` varchar(255) NOT NULL,
  `Telefono` varchar(14) NOT NULL,
  `Correo` varchar(255) NOT NULL,
  `RFC` varchar(255) NOT NULL,
  `NombreCargo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ID` int(11) NOT NULL,
  `Cliente` varchar(100) NOT NULL,
  `Tipo` varchar(255) NOT NULL,
  `Marca` varchar(255) NOT NULL,
  `Cantidad` int(10) NOT NULL,
  `CostoTOT` int(10) NOT NULL,
  `CostoXPZ` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `ID` int(11) NOT NULL,
  `Tipo` varchar(100) NOT NULL,
  `Marca` varchar(100) NOT NULL,
  `Cantidad` int(255) NOT NULL,
  `TipoDaño` varchar(255) NOT NULL,
  `Comentario` varchar(255) NOT NULL,
  `FechaGENE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID` int(11) NOT NULL,
  `Usuario` varchar(255) NOT NULL,
  `Contraseña` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID`, `Usuario`, `Contraseña`) VALUES
(3, 'User', 'User');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`CveCliente`,`NomCliente`,`TelCliente`,`DireCliente`,`CorreoCliente`) USING BTREE;
ALTER TABLE `clientes` ADD FULLTEXT KEY `RFCC` (`RFC`);

--
-- Indices de la tabla `clientestienda`
--
ALTER TABLE `clientestienda`
  ADD PRIMARY KEY (`IDTienda`,`NomCliente`) USING BTREE,
  ADD UNIQUE KEY `NombreCargo` (`NombreCargo`),
  ADD UNIQUE KEY `RFC` (`RFC`),
  ADD UNIQUE KEY `Telefono` (`Telefono`),
  ADD UNIQUE KEY `Correo` (`Correo`),
  ADD KEY `Direccion` (`Direccion`) USING BTREE;

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ID`,`Tipo`,`Marca`,`Cantidad`,`CostoTOT`,`CostoXPZ`,`Cliente`) USING BTREE;

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`ID`,`Tipo`,`Marca`,`Cantidad`,`TipoDaño`,`Comentario`,`FechaGENE`) USING BTREE;

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Usuario` (`Usuario`,`Contraseña`),
  ADD UNIQUE KEY `Usuario_2` (`Usuario`,`Contraseña`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `CveCliente` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientestienda`
--
ALTER TABLE `clientestienda`
  MODIFY `IDTienda` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reportes`
--
ALTER TABLE `reportes`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
