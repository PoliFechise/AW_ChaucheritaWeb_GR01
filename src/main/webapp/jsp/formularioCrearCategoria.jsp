<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Categoría</title>
    <link rel="stylesheet" href="styles/formularioCrearCategoria.css">
</head>
<body>
    <header>
        <span class="titulo">Creación de Categoría</span>
    </header>
    <div class="contenido">
        <div class="formulario">
            <form action="GestionarCategoriaController?ruta=guardar&section=categoria" method="POST">
                <div class="caja-nombre">
                    <label class="negrita-negra">Nombre:</label>
                    <div class="item-formulario1">
                        <input type="text" name="nombre" required />
                    </div>
                </div>
                <input type="hidden" name="tipo" value="${param.tipo}" />
                <!-- Tipo oculto -->
                <div class="botones">
                    <button type="submit" class="btn btn-guardar">Crear</button>
                    <button type="button" class="btn btn-cancelar" onclick="window.location.href='VerTableroController?ruta=ajustes&section=categoria'">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</body>

</html>