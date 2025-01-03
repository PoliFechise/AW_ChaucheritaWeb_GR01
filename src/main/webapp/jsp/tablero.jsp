<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tablero</title>
<link rel="stylesheet" href="styles/tablero.css">

</head>
<body>
	<!-- Header -->
	<header>
		<div class="header">
			<div class="titulo-ChW">
				<img src="${pageContext.request.contextPath}/img/Titulo%20ChW.png" alt="">
			</div>
			<div class="opciones-header">
				<div>
					<button class="cerrarSesion"
						onclick="location.href='../index.html'">Cerrar Sesión</button>
				</div>
				<div class="logoConfiguracion"
					onclick="location.href='ajustes.html'">
					<img src="${pageContext.request.contextPath}/img/IconoEngranaje.png" alt="">
				</div>
			</div>
		</div>
	</header>

	<!-- Main Container -->
	<div class="container">

		<!-- Tabla de Cuentas -->
		<div class="section">
			<table>
				<thead>
					<tr>
						<th>N°</th>
						<th>Cuenta</th>
						<th>Número de Cuenta</th>
						<th>Balance</th>
						<th colspan="2">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>Banco Pichincha - Ahorros</td>
						<td>1234567890</td>
						<td>$ 100.00</td>
						<td class="inspeccionar"
							onclick="location.href='cuentaMovimiento.html'">
							<button>Inspeccionar</button>
						</td>
						<td class="actions">
							<button onclick="window.location.href='ingreso.html'">Registrar
								ingreso</button>
							<button onclick="window.location.href='egreso.html'">Registrar
								egreso</button>
							<button onclick="window.location.href='transferencia.html'">Registrar
								transferencia</button>
						</td>
					</tr>
					<tr>
						<td>2</td>
						<td>Banco Pichincha - Corriente</td>
						<td>0987654321</td>
						<td>$ 100.00</td>
						<td class="inspeccionar"
							onclick="location.href='cuentaMovimiento.html'">
							<button>Inspeccionar</button>
						</td>
						<td class="actions">
							<button onclick="window.location.href='ingreso.html'">Registrar
								ingreso</button>
							<button onclick="window.location.href='egreso.html'">Registrar
								egreso</button>
							<button onclick="window.location.href='transferencia.html'">Registrar
								transferencia</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="medio">
			<div class="titulo-MisGastos">
				<img src="${pageContext.request.contextPath}/img/MisGastos.png" alt="Mis gastos">
			</div>
			<div>
				<button class="filtrar-Fecha" onclick="window.location.href=''">Filtrar
					por fechas</button>
			</div>
		</div>

		<!-- Tabla de Gastos -->
		<div class="section">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Categoría</th>
						<th>Total Egreso</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categorias}" var="categoria">
						<tr>
							<td>${categoria.id}</td>
							<td>${categoria.nombre}</td>
							<td>${categoria.totalEgreso}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>