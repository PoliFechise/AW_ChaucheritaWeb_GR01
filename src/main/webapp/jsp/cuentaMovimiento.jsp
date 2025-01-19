<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cuenta y Movimiento</title>
<link rel="stylesheet" href="styles/cuentaMovimiento.css">
</head>
<body>

    <!-- Header -->
    <header class="header">
        <div class="titulo-ChW">
            Chaucherita Web
        </div>
        <div class="opciones-header">
            <div class="btn-regresar" onclick="location.href='VerTableroController?ruta=ver'">
                <span>Regresar</span>
            </div>
        </div>
    </header>

    <div class="container">

        <!-- Información de la Cuenta -->
        <div class="container-info">
            <div class="cuenta">Cuenta: ${nombre}</div>
            <div class="saldo">$ ${saldo}</div>
        </div>

        <div class="numero-cuenta">Número: ${numero}</div>

        <div class="registro-txt">Registro de movimientos</div>

        <!-- Botones -->
        <div class="container-botones">
            <div class="boton" onclick="window.location.href='ingreso.html?from=cuentaMovimiento'">
                <span>Registrar ingreso</span>
            </div>
            <div class="boton" onclick="window.location.href='egreso.html?from=cuentaMovimiento'">
                <span>Registrar egreso</span>
            </div>
            <div class="boton" onclick="window.location.href='transferencia.html?from=cuentaMovimiento'">
                <span>Registrar transferencia</span>
            </div>
        </div>

        <!-- Filtro -->
        <div class="container-filtro">
            <div class="titulo-seccion">Lista de movimientos</div>
            <form method="get" action="VerMovimientos">
                <input type="hidden" name="ruta" value="filtrarPorCategoria">
                <select name="tipoCategoria" onchange="this.form.submit()">
                    <option value="" ${tipoCategoriaSeleccionada == '' ? 'selected' : ''}>Todos los tipos</option>
                    <option value="Ingreso" ${tipoCategoriaSeleccionada == 'Ingreso' ? 'selected' : ''}>Ingreso</option>
                    <option value="Egreso" ${tipoCategoriaSeleccionada == 'Egreso' ? 'selected' : ''}>Egreso</option>
                    <option value="Transferencia" ${tipoCategoriaSeleccionada == 'Transferencia' ? 'selected' : ''}>Transferencia</option>
                </select>
                <select name="categoria" onchange="this.form.submit()">
                    <option value="">Todas las categorías</option>
                    <c:forEach var="categoria" items="${categorias}">
                        <option value="${categoria.nombre}" ${categoriaSeleccionada == categoria.nombre ? 'selected' : ''}>${categoria.nombre}</option>
                    </c:forEach>
                </select>
            </form>
        </div>

        <!-- Tabla con Divs -->
        <div class="container-tabla" id="container-tabla">
            <!-- Encabezado -->
            <div class="row header">
                <div class="cell-1">No.</div>
                <div class="cell-2">Movimiento</div>
                <div class="cell-3">Concepto</div>
                <div class="cell-2">Origen</div>
                <div class="cell-2">Destino</div>
                <div class="cell-2">Categoría</div>
                <div class="cell-1">Valor</div>
            </div>
            <!-- Las Filas dinámicas se generan aquí -->
            <c:forEach var="movimiento" items="${movimientos}">
                <div class="row">
                    <div class="cell-1">${movimientos.indexOf(movimiento) + 1}</div>
                    <div class="cell-2">
                        <c:choose>
                            <c:when test="${not empty movimiento.ingreso}">Ingreso</c:when>
                            <c:when test="${not empty movimiento.egreso}">Egreso</c:when>
                            <c:when test="${not empty movimiento.transferencia}">Transferencia</c:when>
                        </c:choose>
                    </div>
                    <div class="cell-3">${movimiento.concepto}</div>
                    <div class="cell-2">${movimiento.origen}</div>
                    <div class="cell-2">${movimiento.destino}</div>
                    <div class="cell-2">${movimiento.categoria.nombre}</div>
                    <div class="cell-1">${movimiento.valor}</div>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>