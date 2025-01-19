package model.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import orm.entities.Egreso;
import orm.entities.Ingreso;

public class EgresoDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

    public EgresoDAO() {
    }

    // Método para obtener todos los egresos con relaciones cargadas
    public List<Egreso> obtenerEgresos() {
        try {
            String jpql = "SELECT e FROM Egreso e JOIN FETCH e.origen JOIN FETCH e.destino JOIN FETCH e.movimiento";
            Query query = em.createQuery(jpql);
            List<Egreso> egresos = query.getResultList();
            return egresos;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los egresos en DAO: " + e.getMessage(), e);
        }
    }

 // Método para obtener egresos filtrados por categoría
    public List<Egreso> obtenerEgresosPorCategoria(String categoria) {
        try {
            String jpql = "SELECT e FROM Egreso e JOIN FETCH e.origen JOIN FETCH e.destino JOIN FETCH e.movimiento WHERE e.destino.nombre = :categoria";
            Query query = em.createQuery(jpql);
            query.setParameter("categoria", categoria);
            List<Egreso> egresos = query.getResultList();

            // Log para verificar los egresos obtenidos
            System.out.println("Egresos obtenidos por categoría en DAO: " + egresos);

            return egresos;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los egresos por categoría en DAO: " + e.getMessage(), e);
        }
    }
}