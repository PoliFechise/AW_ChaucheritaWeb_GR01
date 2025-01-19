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
import orm.entities.*;
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;
import model.dto.CategoriaEgresoDTO;

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
			this.guardarCategoria(request, response);
			break;
		case "eliminar":
			this.eliminarCategoria(request, response); // Nueva ruta para eliminar
			break;
		case "actualizar": // Nueva ruta para actualizar
            this.presentarFormularioActualizar(request, response);
            break;
		case "cambiar": // Nueva ruta para actualizar
            this.cambiarCategoria(request, response);
            break;
		default:
			response.sendRedirect("ajustes.jsp");
			break;
		}
	}

	private void cambiarCategoria(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categoria> categoriasIngreso;
		List<Categoria> categoriasEgreso;
		List<Categoria> categoriasTransferencia;

		CategoriaDAO categoriaDAO = new CategoriaDAO();

		categoriasIngreso = categoriaDAO.obtenerCategoriasIngreso();
		categoriasEgreso = categoriaDAO.obtenerCategoriasEgreso();
		categoriasTransferencia = categoriaDAO.obtenerCategoriasTransferencia();

		// Enviar datos a la vista
		request.setAttribute("categoriasIngreso", categoriasIngreso);
		request.setAttribute("categoriasEgreso", categoriasEgreso);
		request.setAttribute("categoriasTransferencia", categoriasTransferencia);

		getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
	}

	private void presentarFormularioCrear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("jsp/formularioCreacionCategoria.jsp").forward(request, response);
	}
	
	private void presentarFormularioActualizar(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    getServletContext().getRequestDispatcher("/jsp/formularioActualizacionCategoria.jsp").forward(request, response);
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

		// Validar el tipo
		if (!tipo.equals("ingreso") && !tipo.equals("egreso") && !tipo.equals("transferencia")) {
			return;
		}

		// 2. Hablar con el dominio
		Categoria categoria = new Categoria();
		categoria.setNombre(nombre);
		//categoria.setTipo(tipo.toLowerCase());

		// 3. Hablar con la vista
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.guardar(categoria);
			response.sendRedirect("GestionarCategoriaController?ruta=listar");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener los parámetros id y tipo para la eliminación
		String idCategoria = request.getParameter("id");
		String tipoCategoria = request.getParameter("tipo");

		if (idCategoria == null || idCategoria.trim().isEmpty() || tipoCategoria == null
				|| tipoCategoria.trim().isEmpty()) {
			request.setAttribute("error", "ID de categoría o tipo no proporcionado.");
			response.sendRedirect("GestionarCategoriaController?ruta=listar");
			return;
		}

		try {
			int id = Integer.parseInt(idCategoria);
			CategoriaDAO categoriaDAO = new CategoriaDAO();

			// Llamamos al método de eliminación de la categoría
			categoriaDAO.eliminar(id);

			// Redirigir al listado después de eliminar
			response.sendRedirect("GestionarCategoriaController?ruta=listar");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al eliminar la categoría: " + e.getMessage());
			getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}
	}
}
