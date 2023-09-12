package com.example.thiaco.model.LocationRegion;

import com.example.thiaco.dto.LocationRegionDTO;
import com.example.thiaco.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location_region")
@Accessors(chain = true)
public class LocationRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provinceId;

    private String provinceName;

    private String districtId;

    private String districtName;

    private String wardId;

    private String wardName;

    private String address;

    @OneToOne
    @JoinColumn(name = "nhanVien_id",referencedColumnName = "id",nullable = false)
    private Employee employee;

    public LocationRegionDTO toLocationRegionDTO ()
    {
       return new LocationRegionDTO()
               .setId(id)
               .setProvinceId(provinceId)
               .setProvinceName(provinceName)
               .setDistrictId(districtId)
               .setDistrictName(districtName)
               .setWardId(wardId)
               .setWardName(wardName)
               .setAddress(address)
               .setEmployeeId(employee.getId())
               ;
    }
}

