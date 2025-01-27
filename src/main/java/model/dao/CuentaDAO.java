package model.dao;

import jakarta.persistence.*;
import orm.entities.Cuenta;

import java.util.List;

public class CuentaDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01");
    private EntityManager em;

    public CuentaDAO() {
        this.em = emf.createEntityManager();
    }

    public void guardar(Cuenta cuenta) {
        try {
            em.getTransaction().begin();
            em.persist(cuenta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    
 // Nuevo método para encontrar por número de cuenta
    public Cuenta encontrarPorNumero(String numero) {
        try {
            // Crear la consulta JPQL
            TypedQuery<Cuenta> query = em.createQuery("SELECT c FROM Cuenta c WHERE c.numero = :numero", Cuenta.class);
            // Establecer el parámetro de la consulta
            query.setParameter("numero", numero);
            // Ejecutar la consulta y devolver el resultado
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cuenta> obtenerCuentas() {
        return em.createQuery("SELECT c FROM Cuenta c", Cuenta.class).getResultList();
    }

    public void actualizar(Cuenta cuenta) {
        try {
            em.getTransaction().begin();
            em.merge(cuenta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public void eliminarPorNumero(String numero) {
        try {
            em.getTransaction().begin();

            // Buscar la cuenta por su número
            Cuenta cuenta = em.createQuery("SELECT c FROM Cuenta c WHERE c.numero = :numero", Cuenta.class)
                              .setParameter("numero", numero)
                              .getSingleResult();

            if (cuenta != null) {
                // Eliminar transferencias relacionadas con movimientos de la cuenta
                em.createQuery("DELETE FROM Transferencia t WHERE t.movimiento.id IN (SELECT m.id FROM Movimiento m WHERE m.cuenta.id = :cuentaId)")
                  .setParameter("cuentaId", cuenta.getId())
                  .executeUpdate();

                // Eliminar ingresos relacionados con movimientos de la cuenta
                em.createQuery("DELETE FROM Ingreso i WHERE i.movimiento.id IN (SELECT m.id FROM Movimiento m WHERE m.cuenta.id = :cuentaId)")
                  .setParameter("cuentaId", cuenta.getId())
                  .executeUpdate();

                // Eliminar egresos relacionados con movimientos de la cuenta
                em.createQuery("DELETE FROM Egreso e WHERE e.movimiento.id IN (SELECT m.id FROM Movimiento m WHERE m.cuenta.id = :cuentaId)")
                  .setParameter("cuentaId", cuenta.getId())
                  .executeUpdate();

                // Eliminar movimientos relacionados con la cuenta
                em.createQuery("DELETE FROM Movimiento m WHERE m.cuenta.id = :cuentaId")
                  .setParameter("cuentaId", cuenta.getId())
                  .executeUpdate();

                // Finalmente, eliminar la cuenta
                em.remove(cuenta);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }


}