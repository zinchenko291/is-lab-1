package me.zinch.is.islab1jee8.models.dao.implementations;

import me.zinch.is.islab1jee8.Config;
import me.zinch.is.islab1jee8.exceptions.FieldValueConvertException;
import me.zinch.is.islab1jee8.models.dao.interfaces.IDao;
import me.zinch.is.islab1jee8.models.fields.EntityField;
import me.zinch.is.islab1jee8.models.fields.Filter;
import org.eclipse.persistence.exceptions.ConversionException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T, F extends EntityField> implements IDao<T, F> {
    @PersistenceContext(unitName = Config.UNIT_NAME)
    protected EntityManager em;

    private final Class<T> entityClass;

    protected AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(Integer id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return em.createQuery(String.format("SELECT c FROM %s c", entityClass.getSimpleName()), entityClass)
                .getResultList();
    }

    @Override
    public List<T> findAllPaged(Integer page, Integer pageSize, Filter<F> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

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

        TypedQuery<T> q = em.createQuery(cq);
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
        return em.createQuery(String.format("SELECT COUNT(c) FROM %s c", entityClass.getSimpleName()), Long.class)
                .getSingleResult();
    }

    @Override
    public Long countPaged(Filter<F> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);

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
    public T create(T coordinates) {
        em.persist(coordinates);
        em.flush();
        return coordinates;
    }

    @Override
    public T update(T coordinates) {
        return em.merge(coordinates);
    }

    @Override
    public T delete(T coordinates) {
        em.remove(coordinates);
        return coordinates;
    }
}
