<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chaucherita Web</title>
<link rel="stylesheet" href="styles/index.css">
</head>
<body>
	<div class="contenedor">
		<!-- Logo -->
		<div class="logo">
			<img src="img/Logo Chaucherita Web.png" alt="Logo">
		</div>

		<!-- Título -->
		<div class="titulo">CHAUCHERITA WEB</div>

		<!-- Frase -->
		<div class="frase">Registra tu billete y que no te coja el
			chuchaqui financiero</div>

		<!-- Formulario -->
		<form id="loginForm" method="POST"
			action="../loginController?ruta=login">
			<div class="campo">
				<input type="text" id="usuario" placeholder="Usuario:" required>
			</div>
			<div class="campo">
				<input type="password" id="contrasena" placeholder="Contraseña:"
					required>
			</div>
			<div class="boton-container">
				<button type="submit" class="boton">Iniciar Sesión</button>
			</div>
		</form>
	</div>
</body>
</html>