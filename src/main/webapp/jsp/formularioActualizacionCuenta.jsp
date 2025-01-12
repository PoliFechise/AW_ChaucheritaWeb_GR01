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
        <span class="Titulo">Actualización de Cuenta</span>
    </header>

    <div class="Contenido">
        <div class="Formulario">
            <form class="form" action="GestionarCuentaController" method="POST">
                <input type="hidden" name="ruta" value="modificar">
                <input type="hidden" name="redirect" value="VerTableroController?ruta=ajustes">
                <input type="hidden" name="numero" value="${numero}">

                <div class="Campos">
                    <div class="campo">
                        <span class="negrita-negra">Nombre:</span>
                        <div class="ItemFormulario1">
                            <input type="text" id="nombre" name="nombre" value="${nombre}" required>
                        </div>
                    </div>
                    <div class="campo">
                        <span class="negrita-negra">Saldo:</span>
                        <div class="ItemFormulario1">
                            <input type="number" id="saldo" name="saldo" value="${saldo}" step="0.01" required>
                        </div>
                    </div>
                </div>

                <div class="BtnCrear" id="btnActualizar">
                    <button type="submit" class="btn-guardar">Actualizar</button>
                </div>
                <div class="BtnCancelar">
                    <button type="button" class="btn-cancelar" onclick="window.location.href='VerTableroController?ruta=ajustes&section=cuenta'">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>