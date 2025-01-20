package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orm.entities.*;
import model.bdd.BddConnection;
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;
import model.dao.EgresoDAO;
import model.dao.IngresoDAO;
import model.dao.MovimientoDAO;

@WebServlet("/RegistrarEgresoController")
public class RegistrarEgresoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Egreso egreso = new Egreso();
		Movimiento movimiento = new Movimiento();
		Cuenta cuenta = new Cuenta();
		CatEgreso catEgreso = new CatEgreso();

		movimiento.setConcepto(request.getParameter("concepto"));

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime dateTime = LocalDate.parse(request.getParameter("fecha"), format).atStartOfDay();
		movimiento.setFecha(dateTime);

		CuentaDAO cuentaDAO = new CuentaDAO();
		cuenta = cuentaDAO.encontrarPorNumero(request.getParameter("numeroCuenta"));
		movimiento.setCuenta(cuenta);

		movimiento.setValor(BigDecimal.valueOf(Double.parseDouble(request.getParameter("valor"))));

		MovimientoDAO movimientoDAO = new MovimientoDAO();
		movimientoDAO.guardarMovimiento(movimiento);

		catEgreso.setNombre(request.getParameter("categoria"));
		egreso.setDestino(catEgreso);

		egreso.setOrigen(cuenta);

		egreso.setMovimiento(movimiento);

		EgresoDAO egresoDAO = new EgresoDAO();
		egresoDAO.guardarEgreso(egreso);

		BigDecimal nuevoBalance = movimiento.getValor().add(cuenta.getSaldo().negate());
		cuenta.setSaldo(nuevoBalance);

		cuentaDAO.update(cuenta);

		response.sendRedirect("VerTableroController");
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null) ? "listar" : request.getParameter("ruta");

		switch (ruta) {
		case "egreso":
			this.prepararIngreso(request, response);
			break;
		case "registrar-egreso":
			this.registrarIngreso(request, response);
		default:
			response.sendRedirect("ingreso.jsp");
			break;
		}
	}

	private void registrarIngreso(HttpServletRequest request, HttpServletResponse response) {

	}

	private void prepararIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Obtener categorías de ingreso
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			List<Categoria> categoriasEgreso = categoriaDAO.obtenerCategoriasEgreso();

			// Obtener saldo de la cuenta
			String numeroCuenta = request.getParameter("numero");
			CuentaDAO cuentaDAO = new CuentaDAO();
			BigDecimal saldoCuenta = cuentaDAO.encontrarPorNumero(numeroCuenta).getSaldo();

			// Pasar las categorías como atributo
			request.setAttribute("categoriasEgreso", categoriasEgreso);
			request.setAttribute("saldoCuenta", saldoCuenta);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mensajeError", "Error al cargar las categorías: " + e.getMessage());
		}

		// Redirigir a ingreso.jsp
		getServletContext().getRequestDispatcher("/jsp/egreso.jsp").forward(request, response);
	}
}
