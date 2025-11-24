package me.zinch.is.islab1jee8.models.dao;

import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.entities.CoordinatesId;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CoordinateDao implements ICoordinatesDao {
    @PersistenceContext
    private final EntityManager em;

    public CoordinateDao(EntityManager em) {
        this.em = em;
    }

    public void save(Coordinates coordinates) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(coordinates);
        tx.commit();
    }

    public Coordinates findById(CoordinatesId id) {
        return em.find(Coordinates.class, id);
    }

    public List<Coordinates> findAll() {
        return em.createQuery("SELECT c FROM Coordinates c", Coordinates.class)
                .getResultList();
    }

    public List<Coordinates> findAllPaged(int page, int pageSize) {
        return em.createQuery("SELECT c FROM Coordinates c", Coordinates.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Coordinates update(Coordinates coordinates) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Coordinates updated = em.merge(coordinates);
        tx.commit();
        return updated;
    }

    public void delete(CoordinatesId id) {
        Coordinates c = findById(id);
        if (c != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(c);
            tx.commit();
        }
    }
}
