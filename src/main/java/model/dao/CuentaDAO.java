package model.dao;

import jakarta.persistence.*;
import model.Cuenta;

import java.util.List;

public class CuentaDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01");
    private EntityManager em;

    public CuentaDAO() {
        this.em = emf.createEntityManager();
    }

    public void create(Cuenta cuenta) {
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

    public List<Cuenta> findAll() {
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
    
    public void deleteByNumero(String numero) {
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
