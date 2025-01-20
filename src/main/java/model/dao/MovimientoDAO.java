package model.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import orm.entities.*;

public class MovimientoDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

    public MovimientoDAO() {
    }

    public List<Movimiento> obtenerMovimientos() {
        try {
            String jpql = "SELECT m FROM Movimiento m";
            Query query = em.createQuery(jpql);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los movimientos: " + e.getMessage(), e);
        }
    }

    public List<Movimiento> obtenerMovimientosPorCategoria(String tipoCategoria, String categoria) {
        try {
            String jpql = "";
            switch (tipoCategoria) {
                case "Ingreso":
                    jpql = "SELECT i.movimiento FROM Ingreso i WHERE i.origen.nombre = :categoria";
                    break;
                case "Egreso":
                    jpql = "SELECT e.movimiento FROM Egreso e WHERE e.destino.nombre = :categoria";
                    break;
                case "Transferencia":
                    jpql = "SELECT t.movimiento FROM Transferencia t WHERE t.categoria.nombre = :categoria";
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de categoría desconocido: " + tipoCategoria);
            }
            Query query = em.createQuery(jpql);
            query.setParameter("categoria", categoria);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los movimientos por categoría: " + e.getMessage(), e);
        }
    }
    
    public void guardarMovimiento(Movimiento movimiento) {
    	
    	em.getTransaction().begin();
    	
    	em.persist(movimiento);
    	
    	em.getTransaction().commit();
    	
    }
}