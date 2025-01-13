package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orm.entities.*;
import model.dao.UsuarioDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}
	
	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Logica del control
		String ruta = (request.getParameter("ruta") == null) ? "ingresar" : request.getParameter("ruta");

		switch (ruta) {
		case "ingresar":
			this.ingresar(request, response);
			break;
		case "login":
			this.login(request, response);
			break;	
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1.- Obtener parámetros
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		// 2.- Hablar con el modelo
		Usuario u;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		u = usuarioDAO.autenticarPersona(usuario, contrasena);
		if (u != null) {
			// 3.- Redireccionar al controlador
			// Le permito ir al CU gestionarUsuariosControlles
			// response.sendRedirect("GestionarUsuariosController?ruta=listar");
			response.sendRedirect("VerTableroController");
		} else {
			// 3.- Redireccionar a la vista
			response.sendRedirect("jsp/login.jsp");
		}
	}
	
	private void ingresar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1.- Obtener parámetros
		// 2.- Hablar con el modelo
		// 3.- Redireccionar a la vista
		response.sendRedirect("jsp/login.jsp");
	}

}
