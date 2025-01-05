package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ingreso;
import model.dao.IngresoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/RegistrarIngresoController")
public class RegistrarIngresoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrarIngresoController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.ingresar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.guardarIngreso(request, response);
    }

    private void ingresar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirigir al JSP de ingreso
        request.getRequestDispatcher("jsp/ingreso.jsp").forward(request, response);
    }

    private void guardarIngreso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener parámetros del formulario
            float valor = Float.parseFloat(request.getParameter("valor"));
            int cuenta = Integer.parseInt(request.getParameter("cuenta"));
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");
            String concepto = request.getParameter("concepto");
            String fechaStr = request.getParameter("fecha");

            // Convertir la fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);

            // Crear objeto Ingreso
            Ingreso ingreso = new Ingreso(valor, cuenta, origen, destino, fecha, concepto);

            // Guardar ingreso en la base de datos
            IngresoDAO ingresoDAO = new IngresoDAO();
            if (ingresoDAO.guardarIngreso(ingreso)) {
                response.sendRedirect("VerTableroController");
            } else {
                request.setAttribute("error", "No se pudo registrar el ingreso.");
                request.getRequestDispatcher("jsp/ingreso.jsp").forward(request, response);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar el ingreso: " + e.getMessage());
            request.getRequestDispatcher("jsp/ingreso.jsp").forward(request, response);
        }
    }
}
