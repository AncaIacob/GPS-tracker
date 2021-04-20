package org.scd.service;

import org.scd.config.exception.BusinessException;
import org.scd.model.UserLocation;
import org.scd.model.dto.UpdatedLocationDTO;
import org.scd.model.dto.UserLocationDTO;
import org.scd.model.dto.UserLocationFilterDTO;

import java.util.List;

public interface UserLocationService {
    UserLocationDTO addLocation(UserLocationDTO userLocationDTO) throws BusinessException;
    UserLocation getLocationById(long id) throws BusinessException;
    void deleteLocationById(Long id) throws BusinessException;
    UserLocation updateLocationById(UpdatedLocationDTO updatedLocationDTO) throws BusinessException;
    List<UserLocation> getLocationsByUserIdAndDate(UserLocationFilterDTO userLocationFilterDTO) throws BusinessException;



}
