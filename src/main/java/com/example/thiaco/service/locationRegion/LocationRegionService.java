package com.example.thiaco.service.locationRegion;

import com.example.thiaco.model.LocationRegion.LocationRegion;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.repository.LocationRegionRepository;
import com.example.thiaco.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationRegionService implements ILocationRegionService {
    @Autowired
    private LocationRegionRepository locationRegionRepository;
    @Override
    public List<LocationRegion> findAll() {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public LocationRegion save(LocationRegion locationRegion) {
        return locationRegionRepository.save(locationRegion);
    }

    @Override
    public void delete(LocationRegion locationRegionService) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public LocationRegion findLocationRegionByEmployee(Employee employee) {
        return locationRegionRepository.findLocationRegionByEmployee(employee);
    }
}
