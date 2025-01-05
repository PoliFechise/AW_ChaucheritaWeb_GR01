<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajustes</title>
    <link rel="stylesheet" href="styles/ajustes.css">
    <link rel="stylesheet" href="styles/cuenta.css">
</head>
<header>
    <div class="titulo-ajustes">
        <h1>Ajustes</h1>
    </div>
</header>
<body>

    <div class="barra-ajustes">
        <div class="boton-cuenta"
             onclick="cargarContenido('GestionarCuentaController?action=listar', this)">
            <h2>Cuenta</h2>
        </div>
        <div class="boton-categoria"
             onclick="cargarContenido('GestionarCategoriaController?action=listar', this)">
            <h2>Categoría</h2>
        </div>
    </div>
    <div id="contenedor-dinamico" class="contenedor-dinamico">
        <!-- Contenido dinámico -->
    </div>
    <div id="contenedor-regresar" class="contenedor-regresar">
        <div class="boton-regresar" onclick="location.href='tablero.html'">
            Regresar</div>
    </div>

    <script>
        function cargarContenido(url, boton) {
            const contenidoDinamico = document.getElementById('contenedor-dinamico');
            const contenedorRegresar = document.getElementById('contenedor-regresar');
            const botones = document.querySelectorAll('.barra-ajustes .boton-cuenta, .barra-ajustes .boton-categoria');

            // Remover clase 'active' de todos los botones
            botones.forEach(b => b.classList.remove('active'));

            // Agregar clase 'active' al botón seleccionado
            boton.classList.add('active');

            // Realizar la solicitud para cargar el contenido dinámico
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al cargar el contenido');
                    }
                    return response.text();
                })
                .then(data => {
                    contenidoDinamico.innerHTML = data;
                    contenedorRegresar.style.display = 'none';
                })
                .catch(error => console.error(error));
        }

        // Detectar el parámetro 'section' al cargar la página
        document.addEventListener('DOMContentLoaded', () => {
            const urlParams = new URLSearchParams(window.location.search);
            const section = urlParams.get('section');

            if (section === 'cuenta') {
                cargarContenido('GestionarCuentaController?action=listar', document.querySelector('.boton-cuenta'));
            } else if (section === 'categoria') {
                cargarContenido('GestionarCategoriaController?action=listar', document.querySelector('.boton-categoria'));
            }
        });
    </script>

</body>
</html>
