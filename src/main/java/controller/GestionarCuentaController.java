package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.dao.CuentaDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GestionarCuentaController")
public class GestionarCuentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<Cuenta> cuentas;

	public GestionarCuentaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String action = request.getParameter("action");
		String ruta = (request.getParameter("ruta") == null) ? "listar" : request.getParameter("ruta");

		switch (ruta) {
		case "listar":
			listarCuentas(request, response);
			break;

		case "crear":
			presentarFormularioCrear(request, response);
			break;

		case "guardar":
			guardarCuenta(request, response);
			break;

		case "actualizar":
			presentarFormularioActualizar(request, response);
			break;

		case "modificar":
			actualizarCuenta(request, response);
			break;

		case "eliminar":
			eliminarCuenta(request, response);
			break;

		default:
			listarCuentas(request, response);
			break;
		}
	}

	private void listarCuentas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Obtiene parámetros
		// 2. Habla con el modelo
		List<Cuenta> cuentas;

		try {
			CuentaDAO cuentaDAO = new CuentaDAO();
			cuentas = cuentaDAO.getCuentas();

			// 3. Habla con la vista
			request.setAttribute("cuentas", cuentas);
			getServletContext().getRequestDispatcher("/jsp/cuenta.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void presentarFormularioCrear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("jsp/formularioCreacionCuenta.jsp").forward(request, response);
	}

	private void guardarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. Obtiene parámetros
		String nombre = request.getParameter("nombre");
		String numero = request.getParameter("numero");
		BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));

		Cuenta cuenta = new Cuenta(0, nombre, numero, saldo);
		try {
			// 2. Habla con el modelo
			CuentaDAO cuentaDAO = new CuentaDAO();
			cuentaDAO.crear(cuenta);

			// 3. Habla con la vista - Redirige al apartado ajustes
			String redirect = request.getParameter("redirect");
			if (redirect != null && !redirect.isEmpty()) {
				response.sendRedirect(redirect);
			} else {
				response.sendRedirect("VerTableroController?ruta=ajustes");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void presentarFormularioActualizar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Obtiene parámetros
		String numero = request.getParameter("numero");
		// 2. Habla con el modelo
		CuentaDAO cuentaDAO = new CuentaDAO();
		Cuenta cuenta;
		try {
			cuenta = cuentaDAO.encontrarPorNumero(numero);
			// 3. Habla con la vista
			if (cuenta != null) {
				// 3.- Hacia el formulario de actualización de cuenta
				request.setAttribute("nombre", cuenta.getNombre());
				request.setAttribute("numero", cuenta.getNumero());
				request.setAttribute("saldo", cuenta.getSaldo());
				request.getRequestDispatcher("jsp/formularioActualizacionCuenta.jsp").forward(request, response);
			} else {
				// 3.- Hacia los ajustes otra vez, porque la cuenta no existe TODO: Mostrar
				// mensaje de error
				response.sendRedirect("VerTableroController?ruta=ajustes");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void actualizarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. Obtiene parámetros
		String nombre = request.getParameter("nombre");
		String numero = request.getParameter("numero");
		BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));
		// 2. Habla con el modelo
		CuentaDAO cuentaDAO = new CuentaDAO();
		Cuenta cuenta = new Cuenta(0, nombre, numero, saldo);

		try {
			cuentaDAO.actualizar(cuenta);
			// 3. Habla con la vista - Redirige a ajustes
			String redirect = request.getParameter("redirect");
			if (redirect != null && !redirect.isEmpty()) {
				response.sendRedirect(redirect);
			} else {
				response.sendRedirect("VerTableroController?ruta=ajustes");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.- Obtiene parámetros
		String numero = request.getParameter("numero");
		// 2.- Habla con el modelo
		CuentaDAO cuentaDAO = new CuentaDAO();
		try {
			cuentaDAO.eliminar(numero);
			// 3.- Habla con la vista
			String redirect = request.getParameter("redirect");
			if (redirect != null && !redirect.isEmpty()) {
				response.sendRedirect(redirect);
			} else {
				response.sendRedirect("VerTableroController?ruta=ajustes");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
