package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orm.entities.*;
import services.TableroService;
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;
import model.dto.CategoriaEgresoDTO;
import model.dto.TableroDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/VerTableroController")
public class VerTableroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerTableroController() {
		super();
		// TODO Auto-generated constructor stub
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
		String ruta = (request.getParameter("ruta") == null) ? "ver" : request.getParameter("ruta");

		switch (ruta) {
		case "ver":
			this.ver(request, response);
			break;
			
		case "ajustes":
            this.ajustes(request, response);
            break;
            
		case "cuentaMovimiento":
            this.cuentaMovimiento(request, response);
            break;
		}
	}

	private void ver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 1.- Obtener parámetros
	    // 2.- Hablar con el modelo
	    List<CategoriaEgresoDTO> categorias;
	    List<Cuenta> cuentas;

	    try {
	    	TableroService tableroService = new TableroService();
	    	TableroDTO tableroDTO = tableroService.obtenerDatosTablero();
	    	
	    	// 3.- Hablar con la vista
	    	request.setAttribute("tableroDTO", tableroDTO);
			 getServletContext().getRequestDispatcher("/jsp/tablero.jsp").forward(request, response);

			/*
			 * // Obtener categorías CategoriaDAO categoriaDAO = new CategoriaDAO();
			 * categorias = categoriaDAO.getCategoriasEgreso();
			 * 
			 * // Obtener cuentas utilizando el método findAll() del nuevo DAO CuentaDAO
			 * cuentaDAO = new CuentaDAO(); cuentas = cuentaDAO.findAll();
			 * 
			 * // 3.- Hablar con la vista request.setAttribute("categorias", categorias);
			 * request.setAttribute("cuentas", cuentas);
			 * 
			 * getServletContext().getRequestDispatcher("/jsp/tablero.jsp").forward(request,
			 * response);
			 */
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	private void ajustes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    getServletContext().getRequestDispatcher("/jsp/ajustes.jsp").forward(request, response);
	}
	
	// Redirigir a la vista cuentaMovimiento.jsp
	private void cuentaMovimiento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    getServletContext().getRequestDispatcher("/jsp/cuentaMovimiento.jsp").forward(request, response);
	}


}
