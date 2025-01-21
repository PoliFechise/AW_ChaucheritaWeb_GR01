<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Categoría</title>
    <link rel="stylesheet" href="styles/formularioActualizarCategoria.css">
</head>
<body>
    <header>
        <span class="titulo">Actualizar Categoría</span>
    </header>
    <div class="contenido">
        <form action="GestionarCategoriaController" method="post">
            <!-- Campo oculto para definir la ruta -->
            <input type="hidden" name="ruta" value="cambiar">
            <!-- Campo oculto para enviar el ID de la categoría -->
            <input type="hidden" name="id" value="${categoriaId}">

            <div class="formulario">
                <div class="campos">
                    <!-- Mostrar el ID de la categoría -->
                    <div class="fila-formulario">
                        <label class="negrita-negra">No:</label>
                        <div class="item-formulario">${categoriaId}</div>
                    </div>

                    <!-- Campo de entrada para editar el nombre de la categoría -->
                    <div class="caja-nombre">
                        <label class="negrita-negra" for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" class="item-formulario1" value="${nombre}" required>
                    </div>

                    <!-- Botones de acción -->
                    <div class="botones">
                        <button type="submit" class="btn btn-actualizar">Actualizar</button>
                        <button type="button" class="btn btn-cancelar" onclick="window.location.href='VerTableroController?ruta=ajustes&section=categoria'">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>