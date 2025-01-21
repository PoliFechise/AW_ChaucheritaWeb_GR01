package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CuentaDAO;
import model.dao.IngresoDAO;
import model.dao.EgresoDAO;
import model.dao.TransferenciaDAO;
import model.dao.CategoriaDAO;

import java.io.IOException;
import java.util.List;
import orm.entities.Cuenta;
import orm.entities.Ingreso;
import orm.entities.Egreso;
import orm.entities.Transferencia;
import orm.entities.Categoria;

@WebServlet("/VerMovimientosController")
public class VerMovimientosController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public VerMovimientosController() {
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
        // 1.- Obtener parámetros
        String numero = request.getParameter("numero");

        // 2.- Hablar con el modelo
        CuentaDAO cuentaDAO = new CuentaDAO();
        Cuenta cuenta = cuentaDAO.encontrarPorNumero(numero);

        IngresoDAO ingresoDAO = new IngresoDAO();
        List<Ingreso> ingresos = ingresoDAO.obtenerIngresosPorCuenta(numero);

        EgresoDAO egresoDAO = new EgresoDAO();
        List<Egreso> egresos = egresoDAO.obtenerEgresosPorCuenta(numero);

        TransferenciaDAO transferenciaDAO = new TransferenciaDAO();
        List<Transferencia> transferencias = transferenciaDAO.obtenerTransferenciasPorCuenta(numero);

        // Obtener todas las categorías
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.obtenerCategoriasIngreso();

        // 3.- Hablar con la vista
        request.setAttribute("cuenta", cuenta);
        request.setAttribute("ingresos", ingresos);
        request.setAttribute("egresos", egresos);
        request.setAttribute("transferencias", transferencias);
        request.setAttribute("categorias", categorias);

        getServletContext().getRequestDispatcher("/jsp/cuentaMovimiento.jsp").forward(request, response);
    }

    private void filtrarPorCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.- Obtener parámetros
        String numero = request.getParameter("numero");
        String tipoCategoria = request.getParameter("tipoCategoria");
        String categoria = request.getParameter("categoria");

        // 2.- Hablar con el modelo
        CuentaDAO cuentaDAO = new CuentaDAO();
        Cuenta cuenta = cuentaDAO.encontrarPorNumero(numero);

        IngresoDAO ingresoDAO = new IngresoDAO();
        List<Ingreso> ingresos = null;

        EgresoDAO egresoDAO = new EgresoDAO();
        List<Egreso> egresos = null;

        TransferenciaDAO transferenciaDAO = new TransferenciaDAO();
        List<Transferencia> transferencias = null;

        // Obtener las categorías según el tipo de movimiento seleccionado
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = null;

        if ("Ingreso".equals(tipoCategoria)) {
            if (categoria == null || categoria.isEmpty()) {
                ingresos = ingresoDAO.obtenerIngresosPorCuenta(numero);
            } else {
                ingresos = ingresoDAO.obtenerIngresosPorCuentaYCategoria(numero, categoria);
            }
            categorias = categoriaDAO.obtenerCategoriasIngreso();
        } else if ("Egreso".equals(tipoCategoria)) {
            if (categoria == null || categoria.isEmpty()) {
                egresos = egresoDAO.obtenerEgresosPorCuenta(numero);
            } else {
                egresos = egresoDAO.obtenerEgresosPorCuentaYCategoria(numero, categoria);
            }
            categorias = categoriaDAO.obtenerCategoriasEgreso();
        } else if ("Transferencia".equals(tipoCategoria)) {
            if (categoria == null || categoria.isEmpty()) {
                transferencias = transferenciaDAO.obtenerTransferenciasPorCuenta(numero);
            } else {
                transferencias = transferenciaDAO.obtenerTransferenciasPorCuentaYCategoria(numero, categoria);
            }
            categorias = categoriaDAO.obtenerCategoriasTransferencia();
        } else {
            // Si no se selecciona un tipo de categoría, obtener todas las categorías y todos los movimientos
            categorias = categoriaDAO.obtenerCategoriasIngreso();  // o mezcla de todas las categorías si es necesario
            ingresos = ingresoDAO.obtenerIngresosPorCuenta(numero);
            egresos = egresoDAO.obtenerEgresosPorCuenta(numero);
            transferencias = transferenciaDAO.obtenerTransferenciasPorCuenta(numero);
        }

        // 3.- Hablar con la vista
        request.setAttribute("cuenta", cuenta);
        request.setAttribute("ingresos", ingresos);
        request.setAttribute("egresos", egresos);
        request.setAttribute("transferencias", transferencias);
        request.setAttribute("categorias", categorias);
        request.setAttribute("tipoCategoriaSeleccionada", tipoCategoria);
        request.setAttribute("categoriaSeleccionada", categoria);

        getServletContext().getRequestDispatcher("/jsp/cuentaMovimiento.jsp").forward(request, response);
    }
}