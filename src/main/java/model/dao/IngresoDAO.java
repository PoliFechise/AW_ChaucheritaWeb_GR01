package model.dao;

import model.Ingreso;
import model.bdd.BddConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class IngresoDAO {
	
    private EntityManager entityManager;

    public IngresoDAO() {
        
    	this.entityManager = Persistence.createEntityManagerFactory("chaucherita_web").createEntityManager();
    }

    public void guardarIngreso(Ingreso ingreso) {
    	
    	entityManager.getTransaction().begin();
    	
    	entityManager.persist(ingreso);
    	
    	entityManager.getTransaction().commit();
    
    }
}
