<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Cuenta</title>
    <link rel="stylesheet" href="styles/formularioActualizacionCuenta.css">
</head>
<body>
    <header>
        <span class="titulo">Actualización de Cuenta</span>
    </header>

    <div class="contenido">
        <div class="formulario">
            <form action="GestionarCuentaController" method="POST">
                <input type="hidden" name="ruta" value="actualizar">
                <input type="hidden" name="redirect" value="VerTableroController?ruta=ajustes">
                <input type="hidden" name="numero" value="${cuenta.numero}">

                <div class="campos">
                    <div class="campo">
                        <span class="negrita-negra">Nombre:</span>
                        <div class="item-formulario">
                            <input type="text" id="nombre" name="nombre" value="${cuenta.nombre}" required>
                        </div>
                    </div>
                    <div class="campo">
                        <span class="negrita-negra">Saldo:</span>
                        <div class="item-formulario">
                            <input type="number" id="saldo" name="saldo" value="${cuenta.saldo}" step="0.01" required>
                        </div>
                    </div>
                </div>

                <div class="botones">
                    <button type="submit" class="btn btn-guardar">Actualizar</button>
                    <button type="button" class="btn btn-cancelar" onclick="window.location.href='VerTableroController?ruta=ajustes&section=cuenta'">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>