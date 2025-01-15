package test;

import model.Cuenta;
import model.dao.CuentaDAO;

import java.math.BigDecimal;
import java.util.List;

public class CuentaTest {
    public static void main(String[] args) {
        CuentaDAO cuentaDAO = new CuentaDAO();

        // Crear nueva cuenta
        Cuenta cuenta = new Cuenta(null, "Cuenta Test", "1111111111", new BigDecimal("1000.00"));
        cuentaDAO.create(cuenta);
        System.out.println("Cuenta creada: " + cuenta);

        // Listar todas las cuentas
        List<Cuenta> cuentas = cuentaDAO.findAll();
        System.out.println("Cuentas existentes:");
        for (Cuenta c : cuentas) {
            System.out.println(c);
        }

        // Actualizar cuenta
        cuenta.setSaldo(new BigDecimal("2000.00"));
        cuentaDAO.update(cuenta);
        System.out.println("Cuenta actualizada: " + cuenta);

        // Eliminar cuenta
        cuentaDAO.delete(cuenta.getId());
        System.out.println("Cuenta eliminada");

        cuentaDAO.close();
    }
}
