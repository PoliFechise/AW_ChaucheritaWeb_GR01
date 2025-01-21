<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Cuenta</title>
    <link rel="stylesheet" href="styles/formularioCreacionCuenta.css">
</head>
<body>
    <header>
        <span class="titulo">Creación de Cuenta</span>
    </header>

    <div class="contenido">
        <div class="formulario">
            <form action="GestionarCuentaController" method="POST">
                <input type="hidden" name="ruta" value="guardar">
                <input type="hidden" name="redirect" value="VerTableroController?ruta=ajustes">

                <div class="campos">
                    <div class="campo">
                        <span class="negrita-negra">Nombre:</span>
                        <div class="item-formulario1">
                            <input type="text" name="nombre" required>
                        </div>
                    </div>
                    <div class="campo">
                        <span class="negrita-negra">Número:</span>
                        <div class="item-formulario1">
                            <input type="number" name="numero" required>
                        </div>
                    </div>
                    <div class="campo">
                        <span class="negrita-negra">Balance inicial:</span>
                        <div class="item-formulario1">
                            <input type="number" name="saldo" step="0.01" required>
                        </div>
                    </div>
                </div>

                <div class="botones">
                    <button type="submit" class="btn btn-guardar">Guardar</button>
                    <button type="button" class="btn btn-cancelar" onclick="window.location.href='VerTableroController?ruta=ajustes&section=cuenta'">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>