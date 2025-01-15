<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar categoria</title>
<link rel="stylesheet" href="styles/actualizaciondeCategoria.css">
</head>
<body>
    <div class="Contenido">

        <div class="Formulario">
            <div class="Campos">
                <div class="FilaFormulario">
                    <label class="negrita-negra">No:</label>
                    <div class="ItemFormulario"></div>
                    <label class="negrita-negra">Tipo:</label>
                    <div class="ItemFormulario Tipo"></div>
                </div>
                    <div class="Caja-nombre">
                    <label class="negrita-negra">Nombre:</label>
                    <input type="text" class="ItemFormulario1" />
                    </div>
                <div class="BtnCrear" id="btnActualizar">
                    <button>Actualizar</button>
                </div>
                <div class="BtnCancelar">
                    <button type="button" class="btn-cancelar" onclick="window.location.href='VerTableroController?ruta=ajustes&section=categoria'">Cancelar</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Redirigir a ajustes.html con el parámetro 'section=cuenta' al presionar "Crear"
        const btnCrear = document.getElementById('btnActualizar');
        btnCrear.addEventListener('click', () => {
            window.location.href = 'ajustes.html?section=categoria';
        });
    </script>


</body>
</html>