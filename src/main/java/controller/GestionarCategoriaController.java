package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;
import model.dao.CategoriaDAO;
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
		default:
			response.sendRedirect("ajustes.jsp");
			break;
		}
	}

	private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.- Obtener parámetros
		// 2.- Hablar con el modelo
<<<<<<< Updated upstream
=======
		List<CategoriaEgresoDTO> categorias;

>>>>>>> Stashed changes
		List<Categoria> categoriasIngreso;
		List<Categoria> categoriasEgreso;
		List<Categoria> categoriasTransferencia;

		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
<<<<<<< Updated upstream
			
=======

			categorias = categoriaDAO.obtenerCategoriasEgreso();
>>>>>>> Stashed changes
			categoriasIngreso = categoriaDAO.getCategoriasIngreso();
			categoriasEgreso = categoriaDAO.getCategoriasEgreso();
			categoriasTransferencia = categoriaDAO.getCategoriasTransferencia();
<<<<<<< Updated upstream
			
			
		// 3.- Hablar con la vista
			request.setAttribute("categoriasEgreso", categoriasEgreso);
=======

			// 3.- Hablar con la vista
			request.setAttribute("categorias", categorias);
>>>>>>> Stashed changes
			request.setAttribute("categoriasIngreso", categoriasIngreso);
			request.setAttribute("categoriasTransferencia", categoriasTransferencia);

			getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Método para crear una categoría dependiendo del tipo
	private void crearCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. OBTENER PARÁMETROS
		String nombre = request.getParameter("nombre");
		String tipo = request.getParameter("tipo"); // ingreso, egreso o transferencia segun en donde se necesite crear la categoria

		if (nombre == null || nombre.trim().isEmpty() || tipo == null || tipo.trim().isEmpty()) {
			// Validación básica de parámetros
			request.setAttribute("error", "El nombre y tipo de la categoría son obligatorios.");
			getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
			return;
		}

		// 2.HABLO CON EL DOMINIO
		Categoria categoria = new Categoria();
		categoria.setNombre(nombre);

		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();

			// Guardar según el tipo de categoría
			switch (tipo.toLowerCase()) { // Convertir a minúsculas por seguridad
			case "egreso":
				categoria.setTipo("egreso");
				categoriaDAO.guardarCategoria(categoria);
				break;
			case "ingreso":
				categoria.setTipo("ingreso");
				categoriaDAO.guardarCategoria(categoria);
				break;
			case "transferencia":
				categoria.setTipo("transferencia");
				categoriaDAO.guardarCategoria(categoria);
				break;
			default:
				throw new IllegalArgumentException("Tipo de categoría no válido: " + tipo);
			}

			// 3. HABLO CON LA VISTA
			response.sendRedirect("GestionarCategoriaController?ruta=listar");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
