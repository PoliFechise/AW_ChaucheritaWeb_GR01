package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cuenta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GestionarCuentaController")
public class GestionarCuentaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<Cuenta> cuentas;

    public GestionarCuentaController() {
        super();
        cuentas = new ArrayList<>();
        cuentas.add(new Cuenta("Cuenta 1", 12345, 1000.0f));
        cuentas.add(new Cuenta("Cuenta 2", 67890, 2000.0f));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarSolicitud(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarSolicitud(request, response);
    }

    private void procesarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("listar")) {
            listarCuentas(request, response);
        } else if (action.equals("crear")) {
            presentarFormularioCrear(request, response);
        } else if (action.equals("guardar")) {
            guardarCuenta(request, response);
        } else if (action.equals("actualizar")) {
            presentarFormularioActualizar(request, response);
        } else if (action.equals("modificar")) {
            actualizarCuenta(request, response);
        } else if (action.equals("eliminar")) {
            eliminarCuenta(request, response);
        }
    }

    private void listarCuentas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cuentas", cuentas);
        request.getRequestDispatcher("jsp/VistaCuenta.jsp").forward(request, response);
    }

    private void presentarFormularioCrear(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("jsp/FormularioCrearCuenta.jsp").forward(request, response);
    }

    private void guardarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        int numero = Integer.parseInt(request.getParameter("numero"));
        float saldo = Float.parseFloat(request.getParameter("saldo"));

        cuentas.add(new Cuenta(nombre, numero, saldo));

        // Redirige al apartado ajustes
        String redirect = request.getParameter("redirect");
        if (redirect != null && !redirect.isEmpty()) {
            response.sendRedirect(redirect);
        } else {
            response.sendRedirect("VerTableroController?ruta=ajustes");
        }
    }

    private void presentarFormularioActualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numero = Integer.parseInt(request.getParameter("numero"));
        Cuenta cuenta = buscarCuenta(numero);

        if (cuenta != null) {
            request.setAttribute("nombre", cuenta.getNombre());
            request.setAttribute("numero", cuenta.getNumero());
            request.setAttribute("saldo", cuenta.getSaldo());
            request.getRequestDispatcher("jsp/FormularioActualizarCuenta.jsp").forward(request, response);
        } else {
            response.sendRedirect("VerTableroController?ruta=ajustes");
        }
    }

    private void actualizarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int numero = Integer.parseInt(request.getParameter("numero"));
        String nombre = request.getParameter("nombre");
        float saldo = Float.parseFloat(request.getParameter("saldo"));

        Cuenta cuenta = buscarCuenta(numero);
        if (cuenta != null) {
            cuenta.setNombre(nombre);
            cuenta.setSaldo(saldo);
        }

        // Redirige a ajustes
        String redirect = request.getParameter("redirect");
        if (redirect != null && !redirect.isEmpty()) {
            response.sendRedirect(redirect);
        } else {
            response.sendRedirect("VerTableroController?ruta=ajustes");
        }
    }

    private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int numero = Integer.parseInt(request.getParameter("numero"));
        cuentas.removeIf(cuenta -> cuenta.getNumero() == numero);

        // Redirige a ajustes o a la ruta proporcionada en redirect
        String redirect = request.getParameter("redirect");
        if (redirect != null && !redirect.isEmpty()) {
            response.sendRedirect(redirect);
        } else {
            response.sendRedirect("VerTableroController?ruta=ajustes");
        }
    }

    private Cuenta buscarCuenta(int numero) {
        return cuentas.stream().filter(cuenta -> cuenta.getNumero() == numero).findFirst().orElse(null);
    }
}
