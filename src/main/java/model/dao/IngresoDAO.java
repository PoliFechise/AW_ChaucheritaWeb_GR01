package model.dao;

import orm.entities.*;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class IngresoDAO {

	private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

	public IngresoDAO() {
	}

	public List<Ingreso> obtenerIngresos() {
		try {
			String jpql = "SELECT i FROM Ingreso i JOIN FETCH i.origen JOIN FETCH i.movimiento";
			Query query = em.createQuery(jpql);
			List<Ingreso> ingresos = query.getResultList();
			return ingresos;
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los ingresos en DAO: " + e.getMessage(), e);
		}
	}

	public List<Ingreso> obtenerIngresosPorCategoria(String categoria) {
		try {
			String jpql = "SELECT i FROM Ingreso i JOIN FETCH i.origen JOIN FETCH i.movimiento WHERE i.origen.nombre = :categoria";
			Query query = em.createQuery(jpql);
			query.setParameter("categoria", categoria);
			List<Ingreso> ingresos = query.getResultList();
			return ingresos;
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los ingresos por categoría en DAO: " + e.getMessage(), e);
		}
	}

	public List<Ingreso> obtenerIngresosPorCuenta(String numeroCuenta) {
		try {
			String jpql = "SELECT i FROM Ingreso i JOIN FETCH i.origen JOIN FETCH i.movimiento WHERE i.destino.numero = :numeroCuenta";
			Query query = em.createQuery(jpql);
			query.setParameter("numeroCuenta", numeroCuenta);
			List<Ingreso> ingresos = query.getResultList();
			return ingresos;
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los ingresos por cuenta en DAO: " + e.getMessage(), e);
		}
	}

	public List<Ingreso> obtenerIngresosPorCuentaYCategoria(String numeroCuenta, String categoria) {
		try {
			String jpql = "SELECT i FROM Ingreso i JOIN FETCH i.origen JOIN FETCH i.movimiento WHERE i.destino.numero = :numeroCuenta AND i.origen.nombre = :categoria";
			Query query = em.createQuery(jpql);
			query.setParameter("numeroCuenta", numeroCuenta);
			query.setParameter("categoria", categoria);
			List<Ingreso> ingresos = query.getResultList();
			return ingresos;
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los ingresos por cuenta y categoría en DAO: " + e.getMessage(),
					e);
		}
	}

	public void guardarIngreso(Ingreso ingreso) {
		em.getTransaction().begin();
		em.persist(ingreso);
		em.getTransaction().commit();
	}
}
