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
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	private void listarCategorias (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.- Obtener parámetros
		// 2.- Hablar con el modelo
		// 3.- Hablar con la vista
	}
	

}
