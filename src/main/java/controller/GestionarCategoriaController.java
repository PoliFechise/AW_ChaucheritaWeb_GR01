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
import model.CatEgreso;
import model.CatIngreso;
import model.CatTransferencia;
import model.Categoria;
import model.dao.CategoriaDAO;

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
		default:
			response.sendRedirect("ajustes.jsp");
			break;
		}
	}

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// 1.  Obtener parámetros

    	// 2. Hablar con el dominio
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        List<CatIngreso> categoriasIngreso = categoriaDAO.obtenerCategoriasIngreso();
        List<CatEgreso> categoriasEgreso = categoriaDAO.obtenerCategoriasEgreso();
        List<CatTransferencia> categoriasTransferencia = categoriaDAO.obtenerCategoriasTransferencia();

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
                response.sendRedirect("jsp/formularioCrearCategoria.jsp?error=Tipo inválido");
                return;
        }

		// 3. Hablar con la vista
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.guardarCategoria(categoria);
		response.sendRedirect("GestionarCategoriaController?ruta=listar");
	}

}
