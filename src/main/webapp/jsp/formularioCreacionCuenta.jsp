<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Crear Cuenta</title>
<link rel="stylesheet" href="styles/formularioCreacionCuenta.css">
</head>
<body>
	<header>
		<span class="Titulo">Creación de Cuenta</span>
	</header>

	<div class="Contenido">
		<div class="Formulario">
			<form class="form" action="GestionarCuentaController" method="POST">
				<input type="hidden" name="ruta" value="guardar"> <input
					type="hidden" name="redirect"
					value="VerTableroController?ruta=ajustes">

				<div class="Campos">
					<div>
						<span class="negrita-negra">Nombre:</span>
						<div class="ItemFormulario1">
							<input type="text" id="nombre" name="nombre" required>
						</div>
					</div>
					<div>
						<span class="negrita-negra">Número:</span>
						<div class="ItemFormulario1">
							<input type="number" id="numero" name="numero" required>
						</div>
					</div>
					<div>
						<span class="negrita-negra">Balance inicial:</span>
						<div class="ItemFormulario1">
							<input type="number" id="saldo" name="saldo" step="0.01" required>
						</div>
					</div>
				</div>

				<div class="BtnCrear" id="btnCrear">
					<button type="submit" class="btn-guardar">Guardar</button>
				</div>
				<div class="BtnCancelar" id="btnCancelar">
					<button type="button" class="btn-cancelar"
						onclick="window.location.href='VerTableroController?ruta=ajustes&section=cuenta'">Cancelar</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>