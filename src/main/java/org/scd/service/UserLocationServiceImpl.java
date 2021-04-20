package org.scd.service;

import org.scd.config.exception.BusinessException;
import org.scd.model.User;
import org.scd.model.UserLocation;
import org.scd.model.dto.UpdatedLocationDTO;
import org.scd.model.dto.UserLocationDTO;
import org.scd.model.dto.UserLocationFilterDTO;
import org.scd.model.security.CustomUserDetails;
import org.scd.repository.UserLocationRepository;
import org.scd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserLocationServiceImpl implements UserLocationService{

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserLocationDTO addLocation(UserLocationDTO userLocationDTO) throws BusinessException{


        if(userLocationDTO.getLatitude() == null)
        {
            throw new BusinessException(400,"Latitude cannot be null !");
        }

        if(userLocationDTO.getLongitude() == null)
        {
            throw new BusinessException(400,"Longitude cannot be null !");
        }

        final User currentUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        userLocationRepository.save(new UserLocation(userLocationDTO.getLatitude(),userLocationDTO.getLongitude(),userLocationDTO.getDate(),currentUser));

          return null;

    }

    public UserLocation getLocationById(long id) throws BusinessException {
       UserLocation userLocation = userLocationRepository.getById( id);
       if(Objects.isNull(userLocation))
       {
           throw new BusinessException(404, "Location not found !");
       }
        return userLocationRepository.getById(Long.valueOf(id));

    }





    public void deleteLocationById(Long id) throws BusinessException {
        UserLocation userLocation = userLocationRepository.getById( id);
        if(Objects.isNull(userLocation))
        {
            throw new BusinessException(404, "Location not found !");
        }
        userLocationRepository.deleteById(id);

    }

    public UserLocation updateLocationById(UpdatedLocationDTO updatedLocationDTO) throws BusinessException {
        UserLocation updatedUserLocation=userLocationRepository.getById(updatedLocationDTO.getId());

        if(Objects.isNull(updatedUserLocation))
        {
            throw new BusinessException(404, "Location not found !");
        }

        if (updatedLocationDTO.getLatitude()!=null)
            updatedUserLocation.setLatitude(updatedLocationDTO.getLatitude());

        if (updatedLocationDTO.getLongitude()!=null)
            updatedUserLocation.setLongitude(updatedLocationDTO.getLongitude());
        updatedUserLocation.setDate(new Date());

       return userLocationRepository.save(updatedUserLocation);

    }

    public List<UserLocation> getLocationsByUserIdAndDate(UserLocationFilterDTO userLocationFilterDTO) throws BusinessException {


        if(userLocationFilterDTO.getStartDate() == null)
        {
            throw new BusinessException(400,"Start Date cannot be null !");
        }

        if(userLocationFilterDTO.getEndDate() == null)
        {
            throw new BusinessException(400,"End Date cannot be null !");
        }

        if(userLocationFilterDTO.getUserId() == null)
        {
            throw new BusinessException(400,"User Id cannot be null !");
        }
        User user=userRepository.findById(userLocationFilterDTO.getUserId()).orElse(null);

        if (Objects.isNull(user)){
            throw new BusinessException(404, "User with given id not found!");
        }


        if (userLocationFilterDTO.getStartDate().compareTo(userLocationFilterDTO.getEndDate()) > 0) {
            throw new BusinessException(403, "Start date can't be greater than end date");
        }
        return userLocationRepository.customQuery(userLocationFilterDTO.getUserId(),userLocationFilterDTO.getStartDate(),userLocationFilterDTO.getEndDate());
    }
}
