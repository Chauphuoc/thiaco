package com.example.thiaco.service.locationRegion;

import com.example.thiaco.model.LocationRegion.LocationRegion;
import com.example.thiaco.model.employee.Employee;
import com.example.thiaco.service.IGeneralService;

public interface ILocationRegionService extends IGeneralService<LocationRegion,Long> {
    LocationRegion findLocationRegionByEmployee(Employee employee);
}
