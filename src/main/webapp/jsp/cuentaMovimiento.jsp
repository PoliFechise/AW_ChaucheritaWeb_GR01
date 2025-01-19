<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            <div class="cuenta">Cuenta: ${cuenta.nombre}</div>
            <div class="saldo">$ ${cuenta.saldo}</div>
        </div>

        <div class="numero-cuenta">Número: ${cuenta.numero}</div>

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
            <form method="get" action="VerMovimientosController">
                <input type="hidden" name="ruta" value="filtrarPorCategoria">
                <input type="hidden" name="numero" value="${cuenta.numero}">
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
                <c:if test="${empty categorias}">
                    <div class="mensaje-error">No hay categorías disponibles para el tipo seleccionado.</div>
                </c:if>
            </form>
        </div>

        <!-- Tabla con Divs -->
        <div class="container-tabla" id="container-tabla">
            <table>
                <!-- Encabezado -->
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Movimiento</th>
                        <th>Concepto</th>
                        <th>Origen</th>
                        <th>Destino</th>
                        <th>Categoría</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="1" />
                    <!-- Ingresos -->
                    <c:forEach var="ingreso" items="${ingresos}">
                        <tr>
                            <td>${index}</td>
                            <td>Ingreso</td>
                            <td>${ingreso.movimiento.concepto}</td>
                            <td>${ingreso.origen.nombre}</td>
                            <td>${ingreso.destino.nombre}</td>
                            <td>${ingreso.origen.nombre}</td>
                            <td>${ingreso.movimiento.valor}</td>
                        </tr>
                        <c:set var="index" value="${index + 1}" />
                    </c:forEach>
                    <c:if test="${empty ingresos && (tipoCategoriaSeleccionada == 'Ingreso' || tipoCategoriaSeleccionada == '')}">
                        <tr>
                            <td colspan="7" style="text-align: center;">No hay ingresos registrados.</td>
                        </tr>
                    </c:if>

                    <!-- Egresos -->
                    <c:forEach var="egreso" items="${egresos}">
                        <tr>
                            <td>${index}</td>
                            <td>Egreso</td>
                            <td>${egreso.movimiento.concepto}</td>
                            <td>${egreso.origen.nombre}</td>
                            <td>${egreso.destino.nombre}</td>
                            <td>${egreso.destino.nombre}</td>
                            <td>${egreso.movimiento.valor}</td>
                        </tr>
                        <c:set var="index" value="${index + 1}" />
                    </c:forEach>
                    <c:if test="${empty egresos && (tipoCategoriaSeleccionada == 'Egreso')}">
                        <tr>
                            <td colspan="7" style="text-align: center;">No hay egresos registrados.</td>
                        </tr>
                    </c:if>

                    <!-- Transferencias -->
                    <c:forEach var="transferencia" items="${transferencias}">
                        <tr>
                            <td>${index}</td>
                            <td>Transferencia</td>
                            <td>${transferencia.movimiento.concepto}</td>
                            <td>${transferencia.origen.nombre}</td>
                            <td>${transferencia.destino.nombre}</td>
                            <td>${transferencia.categoria.nombre}</td>
                            <td>${transferencia.movimiento.valor}</td>
                        </tr>
                        <c:set var="index" value="${index + 1}" />
                    </c:forEach>
                    <c:if test="${empty transferencias && (tipoCategoriaSeleccionada == 'Transferencia')}">
                        <tr>
                            <td colspan="7" style="text-align: center;">No hay transferencias registradas.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>