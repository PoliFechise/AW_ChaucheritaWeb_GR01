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
import model.dao.EgresoDAO;
import model.dao.IngresoDAO;
import model.dao.MovimientoDAO;
import model.dao.TransferenciaDAO;

@WebServlet("/RegistrarTransferenciaController")
public class RegistrarTransferenciaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Transferencia transferencia = new Transferencia();
	Movimiento movimiento = new Movimiento();
	Cuenta cuentaOrigen = new Cuenta();
	Cuenta cuentaDestino = new Cuenta();
	CatTransferencia catTransferencia = new CatTransferencia();
	
	CuentaDAO cuentaDAO = new CuentaDAO();
	MovimientoDAO movimientoDAO = new MovimientoDAO();
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	TransferenciaDAO transferenciaDAO = new TransferenciaDAO();

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
		cuentaOrigen = cuentaDAO.encontrarPorNumero(request.getParameter("numeroCuenta"));
		movimiento.setCuenta(cuentaOrigen);
		movimiento.setValor(BigDecimal.valueOf(Double.parseDouble(request.getParameter("valor"))));

		catTransferencia = categoriaDAO.encontrarCategoriaTransferenciaPorId(Integer.parseInt(request.getParameter("categoria")));
		transferencia.setCategoria(catTransferencia);
		transferencia.setOrigen(cuentaOrigen);
		cuentaDestino = cuentaDAO.encontrarPorNumero(request.getParameter("destino"));
		transferencia.setDestino(cuentaDestino);
		transferencia.setMovimiento(movimiento);

		BigDecimal nuevoBalanceOrigen = cuentaOrigen.getSaldo().add(movimiento.getValor().negate());
		BigDecimal nuevoBalanceDestino = cuentaDestino.getSaldo().add(movimiento.getValor());

		if(!haySaldoSuficiente(cuentaOrigen.getSaldo(), movimiento.getValor())) {
			response.sendRedirect("VerTableroController?error=saldoInsuficiente");
			return;
		}
		
		movimientoDAO.guardarMovimiento(movimiento);
		transferenciaDAO.guardarTransferencia(transferencia);
		cuentaDestino.setSaldo(nuevoBalanceDestino);
		cuentaDAO.actualizar(cuentaDestino);
		cuentaOrigen.setSaldo(nuevoBalanceOrigen);
		cuentaDAO.actualizar(cuentaOrigen);

		response.sendRedirect("VerTableroController");
	}
	
	private boolean haySaldoSuficiente(BigDecimal saldo, BigDecimal valor) {
		return (saldo.add(valor.negate())).intValue() > 0;
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null) ? "listar" : request.getParameter("ruta");

		switch (ruta) {
		case "transferencia":
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
			// Obtener categorías de ingreso
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			List<Categoria> categoriasTransferencia = categoriaDAO.obtenerCategoriasTransferencia();

			// Obtener saldo de la cuenta
			String numeroCuenta = request.getParameter("numero");
			CuentaDAO cuentaDAO = new CuentaDAO();
			BigDecimal saldoCuenta = cuentaDAO.encontrarPorNumero(numeroCuenta).getSaldo();

			// Pasar las categorías como atributo
			request.setAttribute("categoriasTransferencia", categoriasTransferencia);
			request.setAttribute("saldoCuenta", saldoCuenta);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mensajeError", "Error al cargar las categorías: " + e.getMessage());
		}

		// Redirigir a transferencia.jsp
		getServletContext().getRequestDispatcher("/jsp/transferencia.jsp").forward(request, response);
	}
}
