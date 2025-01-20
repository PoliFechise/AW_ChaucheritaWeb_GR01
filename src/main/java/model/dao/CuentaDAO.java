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
    
    public Cuenta findById(Integer id) {
        return em.find(Cuenta.class, id);
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

    public void update(Cuenta cuenta) {
        try {
            em.getTransaction().begin();
            em.merge(cuenta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            em.getTransaction().begin();
            Cuenta cuenta = em.find(Cuenta.class, id);
            if (cuenta != null) {
                em.remove(cuenta);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public void borrarPorNumero(String numero) {
        try {
            em.getTransaction().begin();
            Cuenta cuenta = em.createQuery("SELECT c FROM Cuenta c WHERE c.numero = :numero", Cuenta.class)
                              .setParameter("numero", numero)
                              .getSingleResult();
            if (cuenta != null) {
                em.remove(cuenta);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }


    public void close() {
        em.close();
    }
}