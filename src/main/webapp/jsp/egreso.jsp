<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrar Egreso</title>
<link rel="stylesheet" href="styles/egreso-styles.css">
</head>
<body>
	<header> </header>
	<main>
		<div class="main">
			<div class="fondo"></div>
			<div class="egreso">
				<div class="content">
					<div class="left-side">
						<div class="ls-content">
							<div class="titulo">
								Egreso
							</div>
							<div class="cuenta">
								<h2>Cuenta: ${param.numero}</h2>
							</div>
							<div class="formulario">
								<form action="RegistrarEgresoController" method="POST">
									<div>
										<label for="valor">Valor:</label> <input type="number"
											id="valor" name="valor"
											placeholder="Ingrese el valor del egreso">
									</div>
									<div>
										<label for="concepto">Concepto:</label> <input type="text"
											id="concepto" name="concepto"
											placeholder="Ingrese una descripcion del egreso">
									</div>
									<div>
										<label for="origen">Origen:</label> <input type="text"
											id="origen" name="origen" placeholder="${param.numero}"
											disabled>
									</div>
									<div>
										<label for="categoria">Categoría:</label> <select
											name="categoria" id="categoria" required>
											<option value="" selected disabled>Seleccione una
												categoría</option>
											<c:forEach var="categoria" items="${categoriasEgreso}">
												<option value="${categoria.id}">${categoria.nombre}</option>
											</c:forEach>
										</select>
									</div>
									<div>
										<label for="fecha">Fecha:</label> <input type="date"
											id="fecha" name="fecha">
									</div>
									<input type="hidden" id="destino" name="numeroCuenta"
										value="${param.numero}">
									<div class="botones">
										<button type="submit" class="btn-guardar" id="btnGuardar">
											<p>Guardar</p>
										</button>
										<button class="btn-cancelar" id="btnCancelar">
											<p>Cancelar</p>
										</button>
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
	<footer> </footer>
	<script>
	const btnCancelar = document.getElementById('btnCancelar');
    btnCancelar.addEventListener('click', () => {
        window.history.back(); // Regresar a la página anterior
    });
    </script>

</body>
</html>