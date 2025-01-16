<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="styles/creacionCategoria.css">
</head>
<header>
	<span class="Titulo">Creación Categoría</span>
</header>
<body>
	<div class="Contenido">

		<form action="GestionarCategoriaController?ruta=guardar" method="POST">
			<div class="Caja-nombre">
				<label class="negrita-negra">Nombre:</label> 
				<input type="text" name="nombre" class="ItemFormulario1" required />
			</div>
			<input type="hidden" name="tipo" value="${param.tipo}" />
			<!-- Tipo oculto -->
			<div class="BtnCrear" id="btnCrear">
				<button type="submit">Crear</button>
			</div>
			<div class="BtnCancelar" id="btnCancelar">
					<button type="button" class="btn-cancelar"
						onclick="window.location.href='VerTableroController?ruta=ajustes&section=categoria'">Cancelar</button>
				</div>
		</form>

	</div>
</body>

</html>
