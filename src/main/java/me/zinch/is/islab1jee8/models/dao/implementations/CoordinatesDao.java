package me.zinch.is.islab1jee8.models.dao.implementations;

import me.zinch.is.islab1jee8.exceptions.FieldValueConvertException;
import me.zinch.is.islab1jee8.models.dao.interfaces.IDao;
import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.fields.CoordinatesField;
import me.zinch.is.islab1jee8.models.fields.Filter;
import org.eclipse.persistence.exceptions.ConversionException;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ManagedBean
@ApplicationScoped
public class CoordinatesDao implements IDao<Coordinates, CoordinatesField> {

    @PersistenceContext(unitName = "postgres")
    private EntityManager em;

    public CoordinatesDao() { /* Bean */}

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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Coordinates> cq = cb.createQuery(Coordinates.class);
        Root<Coordinates> root = cq.from(Coordinates.class);

        List<Predicate> predicates = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        if (filter.getField() != null && filter.getValue() != null) {
            String fieldName = filter.getField().getValue();
            predicates.add(cb.equal(root.get(fieldName), filter.getValue()));
        }

        if (filter.getField() != null && filter.getSortDirection() != null) {
            String fieldName = filter.getField().getValue();
            if (filter.getSortDirection().getValue().equals("ASC")) {
                orders.add(cb.asc(root.get(fieldName)));
            }
            if (filter.getSortDirection().getValue().equals("DESC")) {
                orders.add(cb.desc(root.get(fieldName)));
            }
        } else {
            orders.add(cb.asc(root.get("id")));
        }

        cq.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(orders);

        TypedQuery<Coordinates> q = em.createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(page * pageSize);
        try {
            return q.getResultList();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof ConversionException) {
                throw new FieldValueConvertException(
                        String.format("Не удалось сконвертировать значение %s для поля %s", filter.getValue(), filter.getField().getValue())
                );
            }
            throw e;
        }
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT count(c) FROM Coordinates c", Long.class)
                .getSingleResult();
    }

    @Override
    public Long countPaged(Filter<CoordinatesField> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Coordinates> root = cq.from(Coordinates.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getField() != null && filter.getValue() != null) {
            predicates.add(cb.equal(root.get(filter.getField().getValue()), filter.getValue()));
        }

        cq.select(cb.count(root))
                .where(predicates.toArray(new Predicate[0]));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof ConversionException) {
                throw new FieldValueConvertException(
                        String.format("Не удалось сконвертировать значение %s для поля %s", filter.getValue(), filter.getField().getValue())
                );
            }
            throw e;
        }
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
