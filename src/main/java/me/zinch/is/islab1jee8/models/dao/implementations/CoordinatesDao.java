package me.zinch.is.islab1jee8.models.dao.implementations;

import me.zinch.is.islab1jee8.models.fields.CoordinatesField;
import me.zinch.is.islab1jee8.models.fields.Filter;
import me.zinch.is.islab1jee8.models.dao.helpers.QueryBuilder;
import me.zinch.is.islab1jee8.models.dao.interfaces.IDao;
import me.zinch.is.islab1jee8.models.entities.Coordinates;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@ManagedBean
@ApplicationScoped
public class CoordinatesDao implements IDao<Coordinates, CoordinatesField> {

    @PersistenceContext(unitName = "postgres")
    private EntityManager em;

    private QueryBuilder queryBuilder;

    public CoordinatesDao() {}

    @Inject
    public CoordinatesDao(
            QueryBuilder queryBuilder
    ) {
        this.queryBuilder = queryBuilder;
    }

    @Override
    public Optional<Coordinates> findById(Integer id) {
        try {
            Coordinates coords = em.createQuery("SELECT c from Coordinates c WHERE c.id = :id", Coordinates.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(coords);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Coordinates> findAll() {
        return em.createQuery("SELECT c FROM Coordinates c", Coordinates.class)
                .getResultList();
    }

    @Override
    public List<Coordinates> findAllPaged(Integer page, Integer pageSize,
                                          Filter<CoordinatesField> filter) {
        return queryBuilder
                .createQuery("SELECT c FROM Coordinates c", Coordinates.class, em, page, pageSize, filter)
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT count(c) FROM Coordinates c", Long.class)
                .getSingleResult();
    }

    @Override
    public Long countPaged(Filter<CoordinatesField> filter) {
        return queryBuilder
                .createQuery("SELECT count(c) FROM Coordinates c", Long.class, em, null, null, filter)
                .getSingleResult();
    }

    @Override
    public Coordinates create(Coordinates coordinates) {
        em.persist(coordinates);
        em.flush();
        return coordinates;
    }

    @Override
    public Coordinates update(Coordinates coordinates) {
        return em.merge(coordinates);
    }

    @Override
    public Coordinates delete(Coordinates coordinates) {
        em.remove(coordinates);
        return coordinates;
    }
}
