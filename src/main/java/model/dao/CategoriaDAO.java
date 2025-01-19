package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.bdd.BddConnection;
import model.dto.CategoriaEgresoDTO;
import orm.entities.*;

public class CategoriaDAO {

	private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

	public CategoriaDAO() {
	}

	public List<Categoria> obtenerCategoriasEgreso() {
        String jpql = "SELECT c FROM CatEgreso c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public List<Categoria> obtenerCategoriasIngreso() {
        String jpql = "SELECT c FROM CatIngreso c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public List<Categoria> obtenerCategoriasTransferencia() {
        String jpql = "SELECT c FROM CatTransferencia c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
}
