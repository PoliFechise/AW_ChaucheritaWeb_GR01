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
		// TODO Auto-generated constructor stub
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

	private void listarCategorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.- Obtener parámetros
		// 2.- Hablar con el modelo
		List<CategoriaEgresoDTO> categorias;
		
		List<Categoria> categoriasIngreso;
		List<Categoria> categoriasTransferencia;
		
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			
			categorias = categoriaDAO.obtenerCategoriasEgreso();
			categoriasIngreso = categoriaDAO.getCategoriasIngreso();
			categoriasTransferencia = categoriaDAO.getCategoriasTransferencia();
			
			
		// 3.- Hablar con la vista
			request.setAttribute("categorias", categorias);
			request.setAttribute("categoriasIngreso", categoriasIngreso);
			request.setAttribute("categoriasTransferencia", categoriasTransferencia);
			
			getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
