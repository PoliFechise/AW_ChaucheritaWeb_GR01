package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GestionarCategoriaController")
public class GestionarCategoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public GestionarCategoriaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}
	
	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	private void listarCategorias (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.- Obtener parámetros
		// 2.- Hablar con el modelo
		// 3.- Hablar con la vista
		getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
	}
	

}
