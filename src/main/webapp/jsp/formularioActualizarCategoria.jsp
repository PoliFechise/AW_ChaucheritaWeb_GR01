<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Categoría</title>
    <link rel="stylesheet" href="styles/actualizaciondeCategoria.css">
</head>
<body>
    <header>
        <h2 class="Titulo">Actualizar Categoría</h2>
    </header>
    <div class="Contenido">
        <form action="GestionarCategoriaController" method="post">
            <!-- Campo oculto para definir la ruta -->
            <input type="hidden" name="ruta" value="cambiar">
            <!-- Campo oculto para enviar el ID de la categoría -->
            <input type="hidden" name="id" value="${categoriaId}">

            <div class="Formulario">
                <div class="Campos">
                    <!-- Mostrar el ID de la categoría -->
                    <div class="FilaFormulario">
                        <label class="negrita-negra">No:</label>
                        <div class="ItemFormulario">${categoriaId}</div>
                    </div>

                    <!-- Campo de entrada para editar el nombre de la categoría -->
                    <div class="Caja-nombre">
                        <label class="negrita-negra" for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" class="ItemFormulario1" value="${nombre}" required>
                    </div>

                    <!-- Botones de acción -->
                    <div class="Botones">
                        <button type="submit" class="BtnActualizar">Actualizar</button>
                        <button type="button" class="BtnCancelar" onclick="window.location.href='VerTableroController?ruta=ajustes&section=categoria'">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
