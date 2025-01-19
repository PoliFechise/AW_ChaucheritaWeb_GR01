<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tablero</title>
    <link rel="stylesheet" href="styles/tablero.css">
    <!-- Incluye Font Awesome para los iconos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <!-- Header -->
    <header>
        <div class="header">
            <div class="titulo-ChW">
                Chaucherita Web
            </div>
            <div class="opciones-header">
                <div>
                    <button class="cerrarSesion" onclick="location.href='LoginController'">
                        <span><i class="fas fa-sign-out-alt"></i>Cerrar Sesión</span>
                    </button>
                </div>
                <div class="logoConfiguracion" onclick="location.href='VerTableroController?ruta=ajustes'">
                    <span><i class="fas fa-cog"></i></span>
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
                        <th style="width: 10%;">N°</th>
                        <th style="width: 30%;">Cuenta</th>
                        <th style="width: 20%;">Balance</th>
                        <th style="width: 40%;">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cuenta" items="${tableroDTO.cuentas}">
                        <tr>
                            <td>${tableroDTO.cuentas.indexOf(cuenta) + 1}</td>
                            <td>${cuenta.nombre}</td>
                            <td>$ ${cuenta.saldo}</td>
                            <td class="actions">
                                <div class="actions-inspect">
                                    <button onclick="location.href='VerMovimientos?ruta=inspeccionar&numero=${cuenta.numero}&nombre=${cuenta.nombre}&saldo=${cuenta.saldo}'">
                                    <span>Inspeccionar</span>
                                    </button>
                                </div>
                                <div class="actions-group">
                                    <button onclick="location.href='RegistrarIngresoController?ruta=registrar-ingreso&numero=${cuenta.numero}'"><span>Registrar ingreso</span></button>
                                    <button onclick="window.location.href='egreso.html?numero=${cuenta.numero}'"><span>Registrar egreso</span></button>
                                    <button onclick="window.location.href='transferencia.html?numero=${cuenta.numero}'"><span>Registrar transferencia</span></button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Filtro por Fechas y Título Categorías -->
        <div class="filtro-categorias">
            <div class="categorias-titulo">
                Categorías
            </div>
            <form method="get" action="VerTableroController">
                <input type="hidden" name="ruta" value="filtrarPorFechas">
                <label for="fechaInicio" class="label-fecha">Fecha Inicio:</label>
                <input type="date" id="fechaInicio" name="fechaInicio" class="input-fecha" value="${param.fechaInicio}">
                <label for="fechaFin" class="label-fecha">Fecha Fin:</label>
                <input type="date" id="fechaFin" name="fechaFin" class="input-fecha" value="${param.fechaFin}">
                <button type="submit" class="filtrar-Fecha"><span>Filtrar por fechas</span></button>
                <button type="button" class="borrar-Filtro" onclick="window.location.href='VerTableroController?ruta=borrarFiltro'"><span>Borrar filtro</span></button>
            </form>
        </div>

        <!-- Tabla de Ingresos -->
        <div class="section">
            <table>
                <thead>
                    <tr>
                        <th style="width: 10%;">N°</th>
                        <th style="width: 45%;">Categoría</th>
                        <th style="width: 45%;">Total Ingreso</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tableroDTO.categoriasIngreso}" var="categoria" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${categoria.nombre}</td>
                            <td>${categoria.sumaValor}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Tabla de Gastos -->
        <div class="section">
            <table>
                <thead>
                    <tr>
                        <th style="width: 10%;">N°</th>
                        <th style="width: 45%;">Categoría</th>
                        <th style="width: 45%;">Total Egreso</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tableroDTO.categoriasEgreso}" var="categoria" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${categoria.nombre}</td>
                            <td>${categoria.totalEgreso}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Tabla de Transferencias -->
        <div class="section">
            <table>
                <thead>
                    <tr>
                        <th style="width: 10%;">N°</th>
                        <th style="width: 45%;">Categoría</th>
                        <th style="width: 45%;">Total Transferencia</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tableroDTO.categoriasTransferencia}" var="categoria" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${categoria.nombre}</td>
                            <td>${categoria.sumaValor}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
    </div>
</body>
</html>