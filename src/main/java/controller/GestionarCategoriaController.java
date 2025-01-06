package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            default:
                response.sendRedirect("ajustes.jsp");
                break;
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Categoria> categoriasIngreso;
        List<Categoria> categoriasEgreso;
        List<Categoria> categoriasTransferencia;

        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            categoriasIngreso = categoriaDAO.getCategoriasIngreso();
            categoriasEgreso = categoriaDAO.getCategoriasEgreso();
            categoriasTransferencia = categoriaDAO.getCategoriasTransferencia();

            // Enviar datos a la vista
            request.setAttribute("categoriasIngreso", categoriasIngreso);
            request.setAttribute("categoriasEgreso", categoriasEgreso);
            request.setAttribute("categoriasTransferencia", categoriasTransferencia);

            getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al listar las categorías: " + e.getMessage());
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    private void crearCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");

        if (nombre == null || nombre.trim().isEmpty() || tipo == null || tipo.trim().isEmpty()) {
            request.setAttribute("error", "El nombre y tipo de la categoría son obligatorios.");
            getServletContext().getRequestDispatcher("/jsp/categoria.jsp").forward(request, response);
            return;
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setTipo(tipo.toLowerCase());

        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            // Guardar la categoría
            categoriaDAO.guardarCategoria(categoria);

            // Redirigir al listado después de guardar
            response.sendRedirect("GestionarCategoriaController?ruta=listar");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al crear la categoría: " + e.getMessage());
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
