<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cuenta</title>
<link rel="stylesheet" href="styles/cuenta.css">
</head>
<body>
	<div class="contenedor-cuentas">
		<div class="titulo-cuenta">
			<h2>Configuración de Cuentas</h2>
		</div>
		<div class="contenedor-botones">
			<div class="boton-crear"
				onclick="location.href='GestionarCuentaController?ruta=crear'">Crear</div>
			<div class="boton-regresar"
				onclick="location.href='VerTableroController?ruta=ver'">Regresar</div>
		</div>
		<div class="subtitulo-cuentas-actuales">Cuentas actuales</div>
		<div class="contenedor-tabla-cuentas">
			<table class="table-cuentas">
				<thead>
					<tr>
						<th>No.</th>
						<th>Nombre</th>
						<th>Número</th>
						<th>Balance</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cuenta" items="${cuentas}">
						<tr>
							<td>${cuentas.indexOf(cuenta) + 1}</td>
							<td>${cuenta.nombre}</td>
							<td>${cuenta.numero}</td>
							<td>$ ${cuenta.saldo}</td>
							<td class="acciones">
								<div class="boton-actualizar"
									onclick="location.href='GestionarCuentaController?ruta=actualizar&numero=${cuenta.numero}'">
									Actualizar</div>
								<div class="boton-eliminar"
									onclick="if (confirm('¿Estás seguro de que deseas eliminar esta cuenta?')) {
                    location.href='GestionarCuentaController?ruta=eliminar&numero=${cuenta.numero}';
                 }">
									Eliminar</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<form id="formEliminar" action="GestionarCuentaController"
			method="get" style="display: none;">
			<input type="hidden" name="ruta" value="eliminar"> <input
				type="hidden" id="numeroCuenta" name="numero">
		</form>


		<script>
			function eliminarCuenta(numero) {
				if (confirm("¿Estás seguro de que deseas eliminar esta cuenta?")) {
					// Llenar el formulario oculto con el número de la cuenta
					document.getElementById('numeroCuenta').value = numero;

					// Enviar el formulario
					document.getElementById('formEliminar').submit();
				}
			}
		</script>
</body>
</html>