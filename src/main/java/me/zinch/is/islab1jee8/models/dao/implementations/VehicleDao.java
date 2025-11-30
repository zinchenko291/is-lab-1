package me.zinch.is.islab1jee8.models.dao.implementations;

import me.zinch.is.islab1jee8.models.dao.interfaces.IVehicleDao;
import me.zinch.is.islab1jee8.models.entities.FuelType;
import me.zinch.is.islab1jee8.models.entities.Vehicle;
import me.zinch.is.islab1jee8.models.fields.Filter;
import me.zinch.is.islab1jee8.models.fields.Range;
import me.zinch.is.islab1jee8.models.fields.VehicleField;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ManagedBean
@ApplicationScoped
public class VehicleDao extends AbstractDao<Vehicle, VehicleField> implements IVehicleDao {
    public VehicleDao() {
        super(Vehicle.class);
    }

    @Override
    public Optional<Vehicle> findMinEnginePower() {
        TypedQuery<Vehicle> vehicle = em.createQuery("SELECT v FROM Vehicle v WHERE v.enginePower = (SELECT MIN(vv.enginePower) FROM Vehicle vv)", Vehicle.class);
        try {
            return Optional.of(vehicle.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Long countGtFuelType(FuelType fuelType) {
        return 0L;
    }

    @Override
    public List<Vehicle> findByNameSubstring(Integer page, Integer pageSize, Filter<VehicleField> filter, String name) {
        return Collections.emptyList();
    }

    @Override
    public List<Vehicle> findByEnginePowerRange(Range<Integer> range) {
        return Collections.emptyList();
    }

    @Override
    public Optional<Vehicle> resetDistanceTravelledById(Integer id) {
        return Optional.empty();
    }
}
