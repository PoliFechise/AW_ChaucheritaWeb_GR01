package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoriaDAO;
import model.dao.MovimientoDAO;

import java.io.IOException;
import java.util.List;
import orm.entities.*;

/**
 * Servlet implementation class VerMovimientos
 */
@WebServlet("/VerMovimientos")
public class VerMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerMovimientos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}
	
	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Logica del control
		String ruta = (request.getParameter("ruta") == null) ? "inspeccionar" : request.getParameter("ruta");

		switch (ruta) {
		case "inspeccionar":
			this.inspeccionar(request, response);
			break;
		case "filtrarPorCategoria":
			this.filtrarPorCategoria(request, response);
			break;
			}
	}

	private void inspeccionar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 1. Obtener parámetros
        String numero = request.getParameter("numero");
        String nombre = request.getParameter("nombre");
        String saldo = request.getParameter("saldo");

        // 2. Hablar con el modelo
        MovimientoDAO movimientoDAO = new MovimientoDAO();
        List<Movimiento> movimientos = movimientoDAO.obtenerMovimientos();

        // 3. Pasar los datos a la vista
        request.setAttribute("numero", numero);
        request.setAttribute("nombre", nombre);
        request.setAttribute("saldo", saldo);
        request.setAttribute("movimientos", movimientos);

        // 4. Hablar con la vista
        getServletContext().getRequestDispatcher("/jsp/cuentaMovimiento.jsp").forward(request, response);
    }
	
	private void filtrarPorCategoria(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 1. Obtener parámetros
        String tipoCategoria = request.getParameter("tipoCategoria");
        String categoria = request.getParameter("categoria");

        // Obtener categorías para el filtro
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = null;
        switch (tipoCategoria) {
            case "Ingreso":
                categorias = categoriaDAO.getCategoriasIngreso();
                break;
            case "Egreso":
                categorias = categoriaDAO.getCategoriasEgreso();
                break;
            case "Transferencia":
                categorias = categoriaDAO.getCategoriasTransferencia();
                break;
            default:
                categorias = categoriaDAO.getCategorias();
                break;
        }

        // 2. Hablar con el modelo
        MovimientoDAO movimientoDAO = new MovimientoDAO();
        List<Movimiento> movimientos = movimientoDAO.obtenerMovimientosPorCategoria(tipoCategoria, categoria);
        
        // 3. Pasar los datos a la vista
        request.setAttribute("movimientos", movimientos);
        request.setAttribute("tipoCategoriaSeleccionada", tipoCategoria);
        request.setAttribute("categoriaSeleccionada", categoria);
        request.setAttribute("categorias", categorias);

        // 4. Hablar con la vista
        getServletContext().getRequestDispatcher("/jsp/cuentaMovimiento.jsp").forward(request, response);
    }
}
