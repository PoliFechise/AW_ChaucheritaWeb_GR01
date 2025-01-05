<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categoria</title>
<link rel="stylesheet" href="styles/categoria.css">
</head>
<body>
	<div class="titulo-categoria">
		<h2>Configuración de Categorías</h2>
	</div>

	<div class="contenedor-regresar">
		<div class="boton-regresar" onclick="location.href='VerTableroController?ruta=ajustes'">Regresar
		</div>
	</div>

	<div class="contenedor-recuadros">
		<div class="recuadro-movimiento">
			<div class="titulo-movimiento">
				<h3>Ingresos</h3>
			</div>

			<div class="contenedor-crear">
				<div class="boton-crear"
					onclick="location.href='formularioCreacionCategoria.html'">
					Crear</div>
			</div>

			<div class="subtitulo-categorias-actuales">Categorías actuales
			</div>

			<div class="contenedor-tabla-categorias">
				<table>
					<tr>
						<th>No.</th>
						<th>Nombre</th>
						<th>Acciones</th>
					</tr>
					<c:forEach items="${categoriasIngreso}" var="categoria">
						<tr>
							<td>${categoria.id}</td>
							<td>${categoria.nombre}</td>
							<td>
								<div class="acciones">
									<div class="boton-actualizar"
										onclick="location.href='formularioActualizacionCategoria.html'">
										<img
											src="${pageContext.request.contextPath}/img/IconoLapiz.png"
											alt="">
									</div>
									<div class="boton-eliminar">
										<img
											src="${pageContext.request.contextPath}/img/IconoBasurero.png"
											alt="">
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>

		<div class="recuadro-movimiento">
			<div class="titulo-movimiento">
				<h3>Egresos</h3>
			</div>

			<div class="contenedor-crear">
				<div class="boton-crear"
					onclick="location.href='formularioCreacionCategoria.html'">
					Crear</div>
			</div>

			<div class="subtitulo-categorias-actuales">Categorías actuales
			</div>

			<div class="contenedor-tabla-categorias">
				<table>
					<tr>
						<th>No.</th>
						<th>Nombre</th>
						<th>Acciones</th>
					</tr>
					<c:forEach items="${categoriasEgreso}" var="categoria">
						<tr>
							<td>${categoria.id}</td>
							<td>${categoria.nombre}</td>
							<td>
								<div class="acciones">
									<div class="boton-actualizar"
										onclick="location.href='formularioActualizacionCategoria.html'">
										<img
											src="${pageContext.request.contextPath}/img/IconoLapiz.png"
											alt="">
									</div>
									<div class="boton-eliminar">
										<img
											src="${pageContext.request.contextPath}/img/IconoBasurero.png"
											alt="">
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div class="recuadro-movimiento">
			<div class="titulo-movimiento">
				<h3>Transferencias</h3>
			</div>

			<div class="contenedor-crear">
				<div class="boton-crear"
					onclick="location.href='formularioCreacionCategoria.html'">
					Crear</div>
			</div>

			<div class="subtitulo-categorias-actuales">Categorías actuales
			</div>

			<div class="contenedor-tabla-categorias">
				<table>
					<tr>
						<th>No.</th>
						<th>Nombre</th>
						<th>Acciones</th>
					</tr>
					<c:forEach items="${categoriasTransferencia}" var="categoria">
						<tr>
							<td>${categoria.id}</td>
							<td>${categoria.nombre}</td>
							<td>
								<div class="acciones">
									<div class="boton-actualizar"
										onclick="location.href='formularioActualizacionCategoria.html'">
										<img
											src="${pageContext.request.contextPath}/img/IconoLapiz.png"
											alt="">
									</div>
									<div class="boton-eliminar">
										<img
											src="${pageContext.request.contextPath}/img/IconoBasurero.png"
											alt="">
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<div id="modalEliminar" class="modal">
		<div class="modal-contenido">
			<span class="cerrar">&times;</span>
			<p>¿Estás seguro de que deseas eliminar?</p>
			<div class="modal-botones">
				<button id="btnSi">Sí</button>
				<button id="btnNo">No</button>
			</div>
		</div>
	</div>

	<script>
    document.addEventListener('DOMContentLoaded', () => {
        const modal = document.getElementById('modalEliminar');
        const btnEliminar = document.querySelectorAll('.boton-eliminar');
        const spanCerrar = document.querySelector('.cerrar');
        const btnSi = document.getElementById('btnSi');
        const btnNo = document.getElementById('btnNo');

        btnEliminar.forEach(btn => {
            btn.addEventListener('click', () => {
                modal.style.display = 'block';
            });
        });

        spanCerrar.addEventListener('click', () => {
            modal.style.display = 'none';
        });

        btnSi.addEventListener('click', () => {
            modal.style.display = 'none';
        });

        btnNo.addEventListener('click', () => {
            modal.style.display = 'none';
        });

        window.addEventListener('click', event => {
            if (event.target === modal) {
                modal.style.display = 'none';
            }
        });
    });

    function limpiarSeccion() {
        // Recargar ajustes.html sin parámetros en la URL
        window.location.href = 'ajustes.jsp';
    }
</script>

</body>
</html>