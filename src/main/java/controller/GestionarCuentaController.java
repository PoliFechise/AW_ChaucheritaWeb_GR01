package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cuenta;
import model.dao.CuentaDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/GestionarCuentaController")
public class GestionarCuentaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CuentaDAO cuentaDAO;

    @Override
    public void init() throws ServletException {
        cuentaDAO = new CuentaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ruta = (request.getParameter("ruta") == null) ? "listar" : request.getParameter("ruta");

        switch (ruta) {
            case "listar":
                listarCuentas(request, response);
                break;
            case "crear":
                presentarFormularioCrear(request, response);
                break;
            case "actualizar":
                presentarFormularioActualizar(request, response);
                break;
            case "eliminar":
                eliminarCuenta(request, response);
                break;
            default:
                listarCuentas(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ruta = (request.getParameter("ruta") == null) ? "" : request.getParameter("ruta");

        switch (ruta) {
            case "guardar":
                guardarCuenta(request, response);
                break;
            case "modificar":
                actualizarCuenta(request, response);
                break;
        }
    }

    private void listarCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cuenta> cuentas = cuentaDAO.findAll();
        request.setAttribute("cuentas", cuentas);
        getServletContext().getRequestDispatcher("/jsp/cuenta.jsp").forward(request, response);
    }

    private void presentarFormularioCrear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/formularioCreacionCuenta.jsp").forward(request, response);
    }

    private void guardarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String numero = request.getParameter("numero");
        BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));
        Cuenta cuenta = new Cuenta(null, nombre, numero, saldo);
        cuentaDAO.create(cuenta);
        response.sendRedirect("VerTableroController?ruta=ajustes");
    }

    private void presentarFormularioActualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numero = request.getParameter("numero");

        // Validar que el parámetro numero no sea nulo o vacío
        if (numero == null || numero.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'numero' es requerido.");
            return;
        }

        // Buscar la cuenta por número
        List<Cuenta> cuentas = cuentaDAO.findAll();
        Cuenta cuenta = cuentas.stream()
                               .filter(c -> c.getNumero().equals(numero))
                               .findFirst()
                               .orElse(null);

        if (cuenta == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cuenta no encontrada.");
            return;
        }

        request.setAttribute("cuenta", cuenta);
        request.getRequestDispatcher("jsp/formularioActualizacionCuenta.jsp").forward(request, response);
    }

    private void actualizarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String numero = request.getParameter("numero");
        BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));

        // Buscar la cuenta por número
        List<Cuenta> cuentas = cuentaDAO.findAll();
        Cuenta cuenta = cuentas.stream()
                               .filter(c -> c.getNumero().equals(numero))
                               .findFirst()
                               .orElse(null);

        if (cuenta != null) {
            cuenta.setNombre(nombre);
            cuenta.setSaldo(saldo);
            cuentaDAO.update(cuenta);
        }

        response.sendRedirect("VerTableroController?ruta=ajustes");
    }

    private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String numero = request.getParameter("numero");

        if (numero == null || numero.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("El parámetro 'numero' es requerido.");
            return;
        }

        try {
            // Buscar y eliminar la cuenta
            cuentaDAO.deleteByNumero(numero);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al eliminar la cuenta.");
        }
    }


    @Override
    public void destroy() {
        cuentaDAO.close();
    }
}
