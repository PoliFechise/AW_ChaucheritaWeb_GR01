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
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;
import model.dao.IngresoDAO;
import model.dao.MovimientoDAO;

@WebServlet("/RegistrarIngresoController")
public class RegistrarIngresoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Ingreso ingreso = new Ingreso();
	Movimiento movimiento = new Movimiento();
	Cuenta cuenta = new Cuenta();
	CatIngreso catIngreso = new CatIngreso();
	
	CuentaDAO cuentaDAO = new CuentaDAO();
	IngresoDAO ingresoDAO = new IngresoDAO();
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	MovimientoDAO movimientoDAO = new MovimientoDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		movimiento.setConcepto(request.getParameter("concepto"));
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime dateTime = LocalDate.parse(request.getParameter("fecha"), format).atStartOfDay();
		movimiento.setFecha(dateTime);
		cuenta = cuentaDAO.encontrarPorNumero(request.getParameter("numeroCuenta"));
		movimiento.setCuenta(cuenta);
		movimiento.setValor(BigDecimal.valueOf(Double.parseDouble(request.getParameter("valor"))));
		
		catIngreso = categoriaDAO.encontrarCategoriaIngresoPorId(Integer.parseInt(request.getParameter("categoria")));
		ingreso.setOrigen(catIngreso);
		ingreso.setDestino(cuenta);
		ingreso.setMovimiento(movimiento);
		
		BigDecimal nuevoBalance = movimiento.getValor().add(cuenta.getSaldo());
		cuenta.setSaldo(nuevoBalance);
	
		movimientoDAO.guardarMovimiento(movimiento);
		ingresoDAO.guardarIngreso(ingreso);
		cuentaDAO.actualizar(cuenta);
		
		response.sendRedirect("VerTableroController");
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null) ? "listar" : request.getParameter("ruta");

		switch (ruta) {
		case "ingreso":
			this.prepararIngreso(request, response);
			break;
		default:
			response.sendRedirect("ingreso.jsp");
			break;
		}
	}

	private void prepararIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			List<Categoria> categoriasIngreso = categoriaDAO.obtenerCategoriasIngreso();

			String numeroCuenta = request.getParameter("numero");
            CuentaDAO cuentaDAO = new CuentaDAO();
            BigDecimal saldoCuenta = cuentaDAO.encontrarPorNumero(numeroCuenta).getSaldo();

			request.setAttribute("categoriasIngreso", categoriasIngreso);
            request.setAttribute("saldoCuenta", saldoCuenta);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mensajeError", "Error al cargar las categorías: " + e.getMessage());
		}

		getServletContext().getRequestDispatcher("/jsp/ingreso.jsp").forward(request, response);
	}
}
