<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chaucherita Web</title>
<link rel="stylesheet" href="../styles/index.css">
</head>
<body>
    <div class="background-animation"></div>
    <div class="contenedor">
        <!-- Logo -->
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/Logo_Chaucherita_Web.png" alt="Logo" class="logo-img">
            <img src="${pageContext.request.contextPath}/img/Dinero.png" alt="Dinero" class="dinero">
        </div>

        <!-- Título -->
        <div class="titulo">CHAUCHERITA WEB</div>

        <!-- Contenedor de Frase -->
        <div class="frase-container">
            <div class="frase">Registra tu billete y que no te coja el chuchaqui financiero</div>
        </div>

        <!-- Formulario -->
        <form id="loginForm" method="POST" action="../LoginController?ruta=login">
            <div class="campo">
                <input type="text" id="usuario" name="usuario" placeholder="Usuario:" required>
            </div>
            <div class="campo">
                <input type="password" id="contrasena" name="contrasena" placeholder="Contraseña:" required>
            </div>
            <div class="boton-container">
                <button type="submit" class="boton">Iniciar Sesión</button>
            </div>
        </form>
    </div>
</body>
</html>