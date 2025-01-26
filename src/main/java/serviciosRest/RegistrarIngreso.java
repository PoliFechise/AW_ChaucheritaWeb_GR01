package serviciosRest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import model.dao.CategoriaDAO;
import model.dao.CuentaDAO;
import model.dao.IngresoDAO;
import model.dao.MovimientoDAO;
import orm.entities.CatIngreso;
import orm.entities.Cuenta;
import orm.entities.Ingreso;
import orm.entities.Movimiento;

@Path("/ingreso")
public class RegistrarIngreso {

    private IngresoDAO ingresoDAO;
    private MovimientoDAO movimientoDAO;
    private CuentaDAO cuentaDAO;
    private CategoriaDAO categoriaDAO;

    public RegistrarIngreso() {
    	
        this.ingresoDAO = new IngresoDAO();
        this.movimientoDAO = new MovimientoDAO();
        this.cuentaDAO = new CuentaDAO();
        this.categoriaDAO = new CategoriaDAO();
        
    }

    @Path("/registrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registrar(IngresoRequest request) {
    	
        Movimiento movimiento = request.getMovimiento();
        Cuenta cuenta = request.getCuenta();
        Ingreso ingreso = request.getIngreso();
        CatIngreso catIngreso = request.getCatIngreso();
        
        String fechaString = request.getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime fecha = LocalDateTime.parse(fechaString, formatter);

        // Guarda el movimiento
        String numeroCuenta = cuenta.getNumero();
        cuenta = cuentaDAO.encontrarPorNumero(numeroCuenta);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(fecha);
        movimientoDAO.guardarMovimiento(movimiento);

        // Ajusta el saldo de la cuenta
        cuenta.setSaldo(cuenta.getSaldo().add(movimiento.getValor()));
        cuentaDAO.actualizar(cuenta);

        // Guarda el ingreso con el movimiento y cuenta relacionados
        Integer id = catIngreso.getId();
        catIngreso = categoriaDAO.encontrarCategoriaIngresoPorId(id);
        ingreso.setOrigen(catIngreso);
        ingreso.setMovimiento(movimiento);
        ingreso.setDestino(cuenta);
        ingresoDAO.guardarIngreso(ingreso);
    }
}
