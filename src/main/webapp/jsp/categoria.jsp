<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<div class="boton-regresar" onclick="location.href='VerTableroController?ruta=ver'"><span>Regresar</span></div>
	</div>

	<div class="contenedor-recuadros">
		<div class="recuadro-movimiento">
			<div class="titulo-movimiento">
				<h3>Ingresos</h3>
			</div>

			<div class="contenedor-crear">
				<div class="boton-crear" onclick="location.href='GestionarCategoriaController?ruta=crear&tipo=ingreso'">
					<span>Crear</span>
				</div>
			</div>

			<div class="subtitulo-categorias-actuales">Categorías actuales</div>

			<div class="contenedor-tabla-categorias">
                <table>
                    <tr>
                        <th>No.</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                    <c:forEach items="${categoriasIngreso}" var="categoria" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${categoria.nombre}</td>
                            <td>
                                <div class="acciones">
                                    <div class="boton-actualizar" onclick="location.href='GestionarCategoriaController?ruta=actualizar&id=${categoria.id}&nombre=${categoria.nombre}'">
                                        <img src="${pageContext.request.contextPath}/img/IconoLapiz.png" alt="">
                                    </div>
                                    <div class="boton-eliminar">
                                        <a href="GestionarCategoriaController?ruta=eliminar&id=${categoria.id}">
                                            <img src="${pageContext.request.contextPath}/img/IconoBasurero.png" alt="">
                                        </a>
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
				<div class="boton-crear" onclick="location.href='GestionarCategoriaController?ruta=crear&tipo=egreso'">
					<span>Crear</span>
				</div>
			</div>

			<div class="subtitulo-categorias-actuales">Categorías actuales</div>

			<div class="contenedor-tabla-categorias">
                <table>
                    <tr>
                        <th>No.</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                    <c:forEach items="${categoriasEgreso}" var="categoria" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${categoria.nombre}</td>
                            <td>
                                <div class="acciones">
                                    <div class="boton-actualizar" onclick="location.href='GestionarCategoriaController?ruta=actualizar&id=${categoria.id}&nombre=${categoria.nombre}'">
                                        <img src="${pageContext.request.contextPath}/img/IconoLapiz.png" alt="">
                                    </div>
                                    <div class="boton-eliminar">
                                        <a href="GestionarCategoriaController?ruta=eliminar&id=${categoria.id}">
                                            <img src="${pageContext.request.contextPath}/img/IconoBasurero.png" alt="">
                                        </a>
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
				<div class="boton-crear" onclick="location.href='GestionarCategoriaController?ruta=crear&tipo=transferencia'">
					<span>Crear</span>
				</div>
			</div>

			<div class="subtitulo-categorias-actuales">Categorías actuales</div>

			<div class="contenedor-tabla-categorias">
                <table>
                    <tr>
                        <th>No.</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                    <c:forEach items="${categoriasTransferencia}" var="categoria" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${categoria.nombre}</td>
                            <td>
                                <div class="acciones">
                                    <div class="boton-actualizar" onclick="location.href='GestionarCategoriaController?ruta=actualizar&id=${categoria.id}&nombre=${categoria.nombre}'">
                                        <img src="${pageContext.request.contextPath}/img/IconoLapiz.png" alt="">
                                    </div>
                                    <div class="boton-eliminar">
                                        <a href="GestionarCategoriaController?ruta=eliminar&id=${categoria.id}">
                                            <img src="${pageContext.request.contextPath}/img/IconoBasurero.png" alt="">
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
		</div>
	</div>

</body>
</html>
