package com.example.thiaco.repository;

import com.example.thiaco.model.LocationRegion.LocationRegion;
import com.example.thiaco.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRegionRepository extends JpaRepository<LocationRegion,Long> {
    LocationRegion findLocationRegionByAddress(String address);

    LocationRegion findLocationRegionByEmployee(Employee employee);
}
