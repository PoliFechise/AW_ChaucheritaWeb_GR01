package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;
import model.dto.CategoriaEgresoDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/VerTableroController")
public class VerTableroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CuentaDAO cuentaDAO;
	 
	public VerTableroController() {
		super();
		// TODO Auto-generated constructor stub
	}

    @Override
    public void init() throws ServletException {
        cuentaDAO = new CuentaDAO();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
		
		String ruta = request.getParameter("ruta");

        if ("ajustes".equals(ruta)) {
            ajustes(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ruta no encontrada");
        }
		
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
		}
	}

    private void ver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.- Obtener parámetros
        // 2.- Hablar con el modelo
        List<CategoriaEgresoDTO> categorias;
        List<Cuenta> cuentas;

        try {
            // Obtener categorías
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categorias = categoriaDAO.obtenerCategoriasEgreso();

            // Obtener cuentas utilizando el método findAll() del nuevo DAO
            CuentaDAO cuentaDAO = new CuentaDAO();
            cuentas = cuentaDAO.findAll();

            // 3.- Hablar con la vista
            request.setAttribute("categorias", categorias);
            request.setAttribute("cuentas", cuentas);

            getServletContext().getRequestDispatcher("/jsp/tablero.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
        }
    }
	
	private void ajustes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    getServletContext().getRequestDispatcher("/jsp/ajustes.jsp").forward(request, response);
	}

}
