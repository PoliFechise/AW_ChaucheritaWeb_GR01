package model.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import orm.entities.Cuenta;
import orm.entities.Egreso;
import orm.entities.Ingreso;

public class EgresoDAO {

	private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

	public EgresoDAO() {
	}

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

	public List<Egreso> obtenerEgresosPorCuenta(Cuenta cuenta) {
		String numeroCuenta = cuenta.getNumero();
		try {
			String jpql = "SELECT e FROM Egreso e JOIN FETCH e.origen JOIN FETCH e.destino JOIN FETCH e.movimiento WHERE e.origen.numero = :numeroCuenta";
			Query query = em.createQuery(jpql);
			query.setParameter("numeroCuenta", numeroCuenta);
			List<Egreso> egresos = query.getResultList();
			return egresos;
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los egresos por cuenta en DAO: " + e.getMessage(), e);
		}
	}

	public List<Egreso> obtenerEgresosPorCuentaYCategoria(Cuenta cuenta, String categoria) {
		String numeroCuenta = cuenta.getNumero();
		try {
			String jpql = "SELECT e FROM Egreso e JOIN FETCH e.origen JOIN FETCH e.destino JOIN FETCH e.movimiento WHERE e.origen.numero = :numeroCuenta AND e.destino.nombre = :categoria";
			Query query = em.createQuery(jpql);
			query.setParameter("numeroCuenta", numeroCuenta);
			query.setParameter("categoria", categoria);
			List<Egreso> egresos = query.getResultList();
			return egresos;
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los egresos por cuenta y categoría en DAO: " + e.getMessage(),
					e);
		}
	}

	public void guardarEgreso(Egreso egreso) {
		em.getTransaction().begin();
		em.persist(egreso);
		em.getTransaction().commit();
	}
}