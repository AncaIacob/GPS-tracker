package org.scd.controller;

import org.scd.config.exception.BusinessException;
import org.scd.model.UserLocation;
import org.scd.model.dto.UpdatedLocationDTO;
import org.scd.model.dto.UserLocationDTO;
import org.scd.model.dto.UserLocationFilterDTO;
import org.scd.service.UserLocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController()
public class LocationController {
    private final UserLocationServiceImpl userLocationServiceImpl;

    @Autowired
    public LocationController(UserLocationServiceImpl userLocationServiceImpl) {
        this.userLocationServiceImpl = userLocationServiceImpl;
    }

    @PostMapping(path="/locations")
    public ResponseEntity<UserLocationDTO> addLocation(@RequestBody final UserLocationDTO userLocationDTO) throws BusinessException {
        return ResponseEntity.ok(userLocationServiceImpl.addLocation(userLocationDTO));
    }

    @GetMapping(path = "/locations/{id}")
    public ResponseEntity<UserLocation> getLocationById(@PathVariable final int id) throws BusinessException {

        return ResponseEntity.ok(userLocationServiceImpl.getLocationById(id));
    }


    @PostMapping(path = "/locations/findByUserIdAndDate")
    public ResponseEntity<List<UserLocation>> getLocationsByUserIdAndDate(@RequestBody UserLocationFilterDTO userLocationFilterDTO) throws BusinessException {
        return ResponseEntity.ok(userLocationServiceImpl.getLocationsByUserIdAndDate(userLocationFilterDTO));
    }

    @DeleteMapping(path = "/locations/delete/{id}")
    public ResponseEntity deleteLocationById(@PathVariable final Long id) throws BusinessException {
       userLocationServiceImpl.deleteLocationById(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/locations/update/{id}")
    public ResponseEntity<UserLocation> updateLocationById(@PathVariable final Long id,@RequestBody final UpdatedLocationDTO updatedLocationDTO) throws BusinessException{
        updatedLocationDTO.setId(id);
        return ResponseEntity.ok(userLocationServiceImpl.updateLocationById(updatedLocationDTO));
    }

}