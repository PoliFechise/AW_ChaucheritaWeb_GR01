package model.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import orm.entities.Egreso;
import orm.entities.Transferencia;

public class TransferenciaDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

    public TransferenciaDAO() {
    }

    // Método para obtener todas las transferencias con relaciones cargadas
    public List<Transferencia> obtenerTransferencias() {
        try {
            String jpql = "SELECT t FROM Transferencia t JOIN FETCH t.origen JOIN FETCH t.destino JOIN FETCH t.categoria JOIN FETCH t.movimiento";
            Query query = em.createQuery(jpql);
            List<Transferencia> transferencias = query.getResultList();
            return transferencias;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las transferencias en DAO: " + e.getMessage(), e);
        }
    }

 // Método para obtener transferencias filtradas por categoría
    public List<Transferencia> obtenerTransferenciasPorCategoria(String categoria) {
        try {
            String jpql = "SELECT t FROM Transferencia t JOIN FETCH t.origen JOIN FETCH t.destino JOIN FETCH t.categoria JOIN FETCH t.movimiento WHERE t.categoria.nombre = :categoria";
            Query query = em.createQuery(jpql);
            query.setParameter("categoria", categoria);
            List<Transferencia> transferencias = query.getResultList();

            // Log para verificar las transferencias obtenidas
            System.out.println("Transferencias obtenidas por categoría en DAO: " + transferencias);

            return transferencias;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las transferencias por categoría en DAO: " + e.getMessage(), e);
        }
    }

	public void guardarTransferencia(Transferencia transferencia) {
		
		em.getTransaction().begin();
		
		em.persist(transferencia);
		
		em.getTransaction().commit();
		
	}
}