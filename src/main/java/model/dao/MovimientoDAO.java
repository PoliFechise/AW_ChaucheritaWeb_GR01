package model.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import orm.entities.*;

public class MovimientoDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("AW_ChaucheritaWeb_GR01").createEntityManager();

    public MovimientoDAO() {
    }
}