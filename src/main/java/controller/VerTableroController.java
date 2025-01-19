package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MovimientoDAO;
import model.dto.TableroDTO;
import services.TableroService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/VerTableroController")
public class VerTableroController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public VerTableroController() {
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
        String ruta = (request.getParameter("ruta") == null) ? "ver" : request.getParameter("ruta");

        switch (ruta) {
            case "ver":
                this.verTablero(request, response);
                break;
            case "filtrarPorFechas":
                this.filtrarPorFechas(request, response);
                break;
            case "borrarFiltro":
                this.borrarFiltro(request, response);
                break;
            case "ajustes":
                this.ajustes(request, response);
                break;
            case "cuentaMovimiento":
                this.cuentaMovimiento(request, response);
                break;
        }
    }

    private void verTablero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            TableroService tableroService = new TableroService();
            TableroDTO tableroDTO = tableroService.obtenerDatosTablero();

            request.setAttribute("tableroDTO", tableroDTO);
            getServletContext().getRequestDispatcher("/jsp/tablero.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filtrarPorFechas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        try {
            TableroService tableroService = new TableroService();
            TableroDTO tableroDTO = tableroService.obtenerDatosTableroPorFechas(fechaInicio, fechaFin);

            request.setAttribute("tableroDTO", tableroDTO);
            request.setAttribute("fechaInicio", fechaInicio);
            request.setAttribute("fechaFin", fechaFin);
            getServletContext().getRequestDispatcher("/jsp/tablero.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void borrarFiltro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("VerTableroController?ruta=ver");
    }

    private void ajustes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/ajustes.jsp").forward(request, response);
    }

    private void cuentaMovimiento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/cuentaMovimiento.jsp").forward(request, response);
    }
}