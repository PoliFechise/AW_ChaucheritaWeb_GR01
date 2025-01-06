package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Cuenta;
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;

@WebServlet("/GestionarCategoriaController")
public class GestionarCategoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GestionarCategoriaController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null) ? "listar" : request.getParameter("ruta");

		switch (ruta) {
		case "listar":
			this.listarCategorias(request, response);
			break;
		case "crear":
			this.presentarFormularioCrear(request, response);
			break;
		case "guardar":
			this.crearCategoria(request, response);
			break;
		default:
			response.sendRedirect("ajustes.jsp");
			break;
		}
	}

	private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categoria> categoriasIngreso;
		List<Categoria> categoriasEgreso;
		List<Categoria> categoriasTransferencia;

		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();

			categoriasIngreso = categoriaDAO.getCategoriasIngreso();
			categoriasEgreso = categoriaDAO.getCategoriasEgreso();
			categoriasTransferencia = categoriaDAO.getCategoriasTransferencia();

			// Enviar datos a la vista
			request.setAttribute("categoriasIngreso", categoriasIngreso);
			request.setAttribute("categoriasEgreso", categoriasEgreso);
			request.setAttribute("categoriasTransferencia", categoriasTransferencia);

			getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void presentarFormularioCrear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("jsp/formularioCrearCategoria.jsp").forward(request, response);
	}

	private void crearCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Obtener parámetros
		String nombre = request.getParameter("nombre");
		String tipo = request.getParameter("tipo");

		// Validar si los parámetros son nulos o vacíos
		if (nombre == null || nombre.trim().isEmpty() || tipo == null || tipo.trim().isEmpty()) {
			request.setAttribute("error", "El nombre y tipo de la categoría son obligatorios.");
			// Usar forward para reenviar la solicitud y mostrar el error en la lista
			request.getRequestDispatcher("/GestionarCategoriaController?ruta=listar").forward(request, response);
			return;
		}

		// Validar el tipo
		if (!tipo.equals("ingreso") && !tipo.equals("egreso") && !tipo.equals("transferencia")) {
			request.setAttribute("error", "El tipo de categoría no es válido.");
			// Usar forward para reenviar la solicitud y mostrar el error en la lista
			request.getRequestDispatcher("/GestionarCategoriaController?ruta=listar").forward(request, response);
			return;
		}

		// 2. Hablar con el dominio
		Categoria categoria = new Categoria();
		categoria.setNombre(nombre);
		categoria.setTipo(tipo.toLowerCase());

		// 3. Hablar con la vista
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.guardarCategoria(categoria);
			response.sendRedirect("GestionarCategoriaController?ruta=listar");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}