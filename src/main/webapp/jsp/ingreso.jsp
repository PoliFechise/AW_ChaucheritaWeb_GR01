<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Ingreso</title>
<link rel="stylesheet" href="../styles/ingreso-styles.css">
</head>
<body>
	<main>
		<div class="main">
			<div class="ingreso">
				<div class="titulo">
					<img src="${pageContext.request.contextPath}/img/Ingreso.png"
						alt="ingreso">
				</div>
				<div class="content">
					<div class="left-side">
						<div class="ls-content">
							<div class="cuenta">
								<h2>Cuenta: ${param.numero}</h2>
							</div>
							<div class="formulario">
								<form action="RegistrarIngresoController" method="POST">
									<div>
										<label for="valor">Valor:</label> <input type="number"
											id="valor" name="valor"
											placeholder="Ingrese el valor del ingreso" required>
									</div>
									<div>
										<label for="concepto">Concepto:</label> <input type="text"
											id="concepto" name="concepto"
											placeholder="Ingrese una descripción del ingreso" required>
									</div>
									<div>
										<label for="categoria">Categoría:</label> <select
											name="categoria" id="categoria" required>
											<option value="" selected disabled>Seleccione una
												categoría</option>
											<c:forEach var="categoria" items="${categoriasIngreso}">
												<option value="${categoria.id}">${categoria.nombre}</option>
											</c:forEach>
										</select>
									</div>
									<div>
										<label for="destino">Destino:</label> <input type="text"
											id="destino" name="destino" placeholder="${param.numero}"
											disabled>
									</div>
									<div>
										<label for="fecha">Fecha:</label> <input type="date"
											id="fecha" name="fecha" required>
									</div>
									<input type="hidden" id="destino" name="numeroCuenta"
										value="${param.numero}">
									<div class="botones">
										<button type="submit" class="btn-guardar" id="btnGuardar">
											Guardar</button>
										<button type="button" class="btn-cancelar" id="btnCancelar">
											Cancelar</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="right-side">
						<div class="saldo">
							<p>$ ${saldoCuenta}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer></footer>

	<script>
        const btnCancelar = document.getElementById('btnCancelar');
        btnCancelar.addEventListener('click', () => {
            window.history.back(); // Regresar a la página anterior
        });
    </script>
</body>
</html>
