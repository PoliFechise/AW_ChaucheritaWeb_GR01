package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Cuenta;
import model.Ingreso;
import model.bdd.BddConnection;
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;
import model.dao.IngresoDAO;

@WebServlet("/RegistrarIngresoController")
public class RegistrarIngresoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String concepto = request.getParameter("concepto");
		String valor = request.getParameter("valor");
		String origen = request.getParameter("categoria");
		String destino = request.getParameter("numeroCuenta");
		String fecha = request.getParameter("fecha");

		if (concepto != null && valor != null && origen != null && destino != null && fecha != null) {

			IngresoDAO ingresoDAO = new IngresoDAO();

			try {
				Ingreso ingreso = new Ingreso();
				ingreso.setConcepto(concepto);
				ingreso.setValor(Float.parseFloat(valor));
				ingreso.setOrigen(origen);
				ingreso.setDestino(destino);
				ingreso.setFecha(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(fecha));
				ingresoDAO.guardarIngreso(ingreso);

			} catch (SQLException | java.text.ParseException e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "Error: " + e.getMessage());
			} finally {
				BddConnection.cerrar();
			}
		} else {
			request.setAttribute("mensaje", "Todos los campos son obligatorios.");
		}

		// Redirigir a ingreso.jsp con el mensaje
		response.sendRedirect("VerTableroController?ruta=ver");
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null) ? "listar" : request.getParameter("ruta");

		switch (ruta) {
		case "registrar-ingreso":
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
            List<Categoria> categoriasIngreso = categoriaDAO.getCategoriasIngreso();

            // Obtener saldo de la cuenta
            String numeroCuenta = request.getParameter("numero");
            CuentaDAO cuentaDAO = new CuentaDAO();

            // Buscar cuenta por ID (simulamos que recibimos el ID en el número por ahora)
            Cuenta cuenta = cuentaDAO.findById(Integer.parseInt(numeroCuenta)); // Cambia si "numero" no es un ID

            // Pasar las categorías como atributo
            request.setAttribute("categoriasIngreso", categoriasIngreso);
            if (cuenta != null) {
                request.setAttribute("saldoCuenta", cuenta.getSaldo());
            } else {
                request.setAttribute("mensajeError", "La cuenta no existe.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cargar las categorías: " + e.getMessage());
        }

        // Redirigir a ingreso.jsp
        getServletContext().getRequestDispatcher("/jsp/ingreso.jsp").forward(request, response);
    }
}
