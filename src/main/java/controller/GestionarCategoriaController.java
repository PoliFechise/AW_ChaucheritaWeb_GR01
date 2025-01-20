package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dao.CategoriaDAO;
import orm.entities.CatEgreso;
import orm.entities.CatIngreso;
import orm.entities.CatTransferencia;
import orm.entities.Categoria;

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
			this.crearCategoria(request, response);
			break;
		case "guardar":
			this.guardarCategoria(request, response);
			break;
		case "eliminar":
			this.eliminarCategoria(request, response);
			break;
		case "actualizar":
			this.actualizarCategoria(request, response);
			break;
		case "cambiar":
			this.cambiarCategoria(request, response);
			break;
		default:
			response.sendRedirect("VerTableroController?ruta=ajustes");
			break;
		}
	}

	private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Obtener parámetros

		// 2. Hablar con el dominio
		CategoriaDAO categoriaDAO = new CategoriaDAO();

		List<Categoria> categoriasIngreso = categoriaDAO.obtenerCategoriasIngreso();
		List<Categoria> categoriasEgreso = categoriaDAO.obtenerCategoriasEgreso();
		List<Categoria> categoriasTransferencia = categoriaDAO.obtenerCategoriasTransferencia();

		// 3. Hablar con la vista
		request.setAttribute("categoriasIngreso", categoriasIngreso);
		request.setAttribute("categoriasEgreso", categoriasEgreso);
		request.setAttribute("categoriasTransferencia", categoriasTransferencia);

		getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
	}

	private void crearCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("jsp/formularioCrearCategoria.jsp").forward(request, response);
	}

	private void guardarCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Obtener parámetros
		String nombre = request.getParameter("nombre");
		String tipo = request.getParameter("tipo");

		// Validar si los parámetros son nulos o vacíos
		if (nombre == null || nombre.trim().isEmpty() || tipo == null || tipo.trim().isEmpty()) {
			return;
		}

		// 2. Hablar con el dominio
		Categoria categoria;

		switch (tipo.toLowerCase()) {
		case "ingreso":
			categoria = new CatIngreso(nombre);
			break;
		case "egreso":
			categoria = new CatEgreso(nombre);
			break;
		case "transferencia":
			categoria = new CatTransferencia(nombre);
			break;
		default:
			return;
		}

		// 3. Hablar con la vista
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.guardar(categoria);
		response.sendRedirect("VerTableroController?ruta=ajustes&section=categoria");
	}

	private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		
		if (idParam == null || idParam.trim().isEmpty()) {
			response.sendRedirect("VerTableroController?ruta=ajustes&section=categoria");
			return;
		}

		int id = Integer.parseInt(idParam);
		request.setAttribute("categoriaId", id);
		request.setAttribute("nombre", nombre);
		request.getRequestDispatcher("jsp/formularioActualizarCategoria.jsp").forward(request, response);
	}

	private void cambiarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idParam = request.getParameter("id");
		String nombre = request.getParameter("nombre");

		if (idParam == null || idParam.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
			response.sendRedirect("VerTableroController?ruta=ajustes&section=categoria");
			return;
		}

		int id = Integer.parseInt(idParam);
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.actualizar(id, nombre);
		response.sendRedirect("VerTableroController?ruta=ajustes&section=categoria");
	}

	private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. Obtener parámetros
		int id = Integer.parseInt(request.getParameter("id"));

		// 2. Hablar con el dominio
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.eliminar(id);

		// 3. Hablar con la vista
		response.sendRedirect("VerTableroController?ruta=ajustes&section=categoria");
	}

}