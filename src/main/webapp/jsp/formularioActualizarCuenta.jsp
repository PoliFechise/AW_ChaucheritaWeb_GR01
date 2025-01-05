<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Actualizar Cuenta</title>
<link rel="stylesheet" href="styles/formularioActualizacionCuenta.css">
</head>
<body>
	<header class="header">
		<h1>Actualizar Cuenta</h1>
	</header>

	<main class="form-container">
		<form class="form" action="GestionarCuentaController" method="POST">
			<input type="hidden" name="ruta" value="modificar"> <input
				type="hidden" name="redirect"
				value="VerTableroController?ruta=ajustes">
			<!-- Para redirigir -->
			<input type="hidden" name="numero" value="${numero}">
			<div class="form-group">
				<label for="nombre">Nombre:</label> <input type="text" id="nombre"
					name="nombre" value="${nombre}" required>
			</div>
			<div class="form-group">
				<label for="saldo">Saldo:</label> <input type="number" id="saldo"
					name="saldo" value="${saldo}" step="0.01" required>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn guardar">Guardar</button>
				<a class="btn cancelar" href="VerTableroController?ruta=ajustes">Cancelar</a>
			</div>
		</form>
	</main>
</body>
</html>
