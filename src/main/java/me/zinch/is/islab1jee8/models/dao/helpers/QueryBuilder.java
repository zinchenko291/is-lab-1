package me.zinch.is.islab1jee8.models.dao.helpers;

import me.zinch.is.islab1jee8.models.fields.EntityField;
import me.zinch.is.islab1jee8.models.fields.Filter;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Objects;

@ManagedBean
@ApplicationScoped
public class QueryBuilder {
    public QueryBuilder() { /* Bean */ }

    private <F extends EntityField> String buildQueryTail(Filter<F> filter) {
        StringBuilder query = new StringBuilder();
        if (Objects.nonNull(filter)) {
            // Поиск по точному совпадению
            if (Objects.nonNull(filter.getField()) && Objects.nonNull(filter.getValue())) {
                query.append(String.format(
                        " WHERE c.%s = :value",
                        filter.getField().getValue()
                ));
            }
            // Сортировка по полю
            if (Objects.nonNull(filter.getSortDirection()) && Objects.nonNull(filter.getField())) {
                query.append(
                        String.format(" ORDER BY c.%s %s",
                                filter.getField().getValue(),
                                filter.getSortDirection().getValue()
                        ));
            } else {
                query.append(" ORDER BY c.id ASC");
            }
        }
        return query.toString();
    }

    private <T, F extends EntityField> void setParams(TypedQuery<T> query, Integer page, Integer pageSize, Filter<F> filter) {
        if (Objects.nonNull(filter) && Objects.nonNull(filter.getField()) && Objects.nonNull(filter.getValue())) {
            query.setParameter("value", filter.getValue());
        }
        if (Objects.nonNull(page) && Objects.nonNull(pageSize)) {
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
        }
    }

    public <T, F extends EntityField> TypedQuery<T> createQuery(String queryHead, Class<T> clazz, EntityManager em, Integer page, Integer pageSize, Filter<F> filter) {
        TypedQuery<T> query = em.createQuery(queryHead + buildQueryTail(filter), clazz);
        setParams(query, page, pageSize, filter);
        return query;
    }
}
