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
    <header class="header">
        <h1>Crear Nueva Cuenta</h1>
    </header>

    <main class="form-container">
        <form class="form" action="GestionarCuentaController" method="POST">
            <input type="hidden" name="ruta" value="guardar">
            <input type="hidden" name="redirect" value="VerTableroController?ruta=ajustes">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="numero">Número:</label>
                <input type="number" id="numero" name="numero" required>
            </div>
            <div class="form-group">
                <label for="saldo">Saldo:</label>
                <input type="number" id="saldo" name="saldo" step="0.01" required>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn guardar">Guardar</button>
                <a class="btn cancelar" href="VerTableroController?ruta=ajustes">Cancelar</a>
            </div>
        </form>
    </main>
</body>
</html>
