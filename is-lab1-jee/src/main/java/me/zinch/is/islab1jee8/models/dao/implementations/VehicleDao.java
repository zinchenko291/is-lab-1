package me.zinch.is.islab1jee8.models.dao.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import me.zinch.is.islab1jee8.exceptions.ConstraintException;
import me.zinch.is.islab1jee8.models.dao.helpers.VehicleFieldConverter;
import me.zinch.is.islab1jee8.models.dao.interfaces.IVehicleDao;
import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.entities.FuelType;
import me.zinch.is.islab1jee8.models.entities.Vehicle;
import me.zinch.is.islab1jee8.models.fields.Range;
import me.zinch.is.islab1jee8.models.fields.VehicleField;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VehicleDao extends AbstractDao<Vehicle, VehicleField> implements IVehicleDao {

    @Inject
    public VehicleDao(VehicleFieldConverter converter) {
        super(Vehicle.class, converter);
    }

    @Override
    public Vehicle create(Vehicle entity) {
        try {
            Coordinates coordinates = em.getReference(Coordinates.class, entity.getCoordinates().getId());
            entity.setCoordinates(coordinates);
            em.persist(entity);
            em.flush();
            return entity;
        } catch (ConstraintViolationException e) {
            throw new ConstraintException(String.format("Ошибка валидации значения(ий). %s", getConstraintMessage(e)));
        }
        catch (NullPointerException e) {
            throw new ConstraintException("Проставлены значения null в недопустимые поля.");
        }
    }

    @Override
    public Vehicle update(Vehicle entity) {
        Vehicle vehicle = em.find(Vehicle.class, entity.getId());
        entity.setCreationDate(vehicle.getCreationDate());
        em.merge(entity);
        return entity;
    }

    @Override
    public Optional<Vehicle> findMinEnginePower() {
        try {
            Vehicle result = (Vehicle) em.createNativeQuery(
                    "SELECT * FROM get_vehicle_with_min_engine_power()",
                    Vehicle.class
            ).getSingleResult( );
            return Optional.ofNullable(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Long countGtFuelType(FuelType fuelType) {
        Object result = em.createNativeQuery(
                        "SELECT count_vehicles_with_fuel_type_greater_than(?1)"
                )
                .setParameter(1, fuelType.name())
                .getSingleResult();

        return ((Number) result).longValue();
    }

    @Override
    public Optional<Vehicle> resetDistanceTravelledById(Integer id) {
        try {
            em.createNativeQuery(
                            "SELECT reset_vehicle_distance(?1)"
                    )
                    .setParameter(1, id)
                    .getResultList();

            Vehicle updated = em.find(Vehicle.class, id);
            em.refresh(updated);
            return Optional.ofNullable(updated);
        } catch (PersistenceException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Vehicle> findByNameSubstring(Integer page, Integer pageSize, String name) {
        @SuppressWarnings("unchecked")
        List<Vehicle> result = em.createNativeQuery(
                        "SELECT * FROM get_vehicles_with_name_containing(?1)",
                        Vehicle.class
                )
                .setParameter(1, name)
                .setFirstResult(page * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return result;
    }

    @Override
    public List<Vehicle> findByEnginePowerRange(Integer page, Integer pageSize, Range<Integer> range) {
        @SuppressWarnings("unchecked")
        List<Vehicle> result = em.createNativeQuery(
                        "SELECT * FROM get_vehicles_in_engine_power_range(?1, ?2)",
                        Vehicle.class
                )
                .setParameter(1, range.getMin())
                .setParameter(2, range.getMax())
                .setFirstResult(page * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return result;
    }

    @Override
    public Long countByNameSubstring(String name) {
        Object result = em.createNativeQuery(
                        "SELECT COUNT(*) FROM get_vehicles_with_name_containing(?1)"
                )
                .setParameter(1, name)
                .getSingleResult();

        return ((Number) result).longValue();
    }

    @Override
    public Long countByEnginePowerRange(Range<Integer> range) {
        Object result = em.createNativeQuery(
                        "SELECT COUNT(*) FROM get_vehicles_in_engine_power_range(?1, ?2)"
                )
                .setParameter(1, range.getMin())
                .setParameter(2, range.getMax())
                .getSingleResult();

        return ((Number) result).longValue();
    }
}
