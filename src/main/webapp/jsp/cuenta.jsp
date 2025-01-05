<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            Configuración de Cuentas
        </div>
        <div class="contenedor-botones">
            <div class="boton-crear" onclick="location.href='GestionarCuentaController?ruta=crear'">Crear</div>
            <div class="boton-regresar" onclick="location.href='VerTableroController?ruta=ajustes'">Regresar</div>
        </div>
        <div class="subtitulo-cuentas-actuales">
            Cuentas actuales
        </div>
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
                                    Actualizar
                                </div>
                                <div class="boton-eliminar" 
                                     onclick="confirmarEliminacion('${cuenta.numero}')">
                                    Eliminar
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal de Confirmación -->
    <div id="modalConfirmacion" class="modal">
        <div class="modal-contenido">
            <p>¿Está seguro de que desea eliminar esta cuenta?</p>
            <div class="modal-botones">
                <button id="btnSi" class="btn-confirmar">Sí</button>
                <button id="btnNo" class="btn-cancelar" onclick="cerrarModal()">No</button>
            </div>
        </div>
    </div>

    <script>
        function confirmarEliminacion(numero) {
            const modal = document.getElementById('modalConfirmacion');
            modal.style.display = 'block';
            const btnSi = document.getElementById('btnSi');
            btnSi.onclick = function () {
                location.href = `GestionarCuentaController?ruta=eliminar&numero=${numero}&redirect=VerTableroController?ruta=ajustes`;
            };
        }

        function cerrarModal() {
            document.getElementById('modalConfirmacion').style.display = 'none';
        }
    </script>
</body>
</html>
