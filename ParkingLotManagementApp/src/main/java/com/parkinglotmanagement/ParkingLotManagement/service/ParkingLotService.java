package com.parkinglotmanagement.ParkingLotManagement.service;

import com.parkinglotmanagement.ParkingLotManagement.model.dto.ParkingLotDTO;
import com.parkinglotmanagement.ParkingLotManagement.model.dto.ParkingScoreDTO;


/**
 * The service used for management of the ParkingLot entity.
 *
 * @author dragan
 */
public interface ParkingLotService {

    /**
     * finds the nearest parking based on given latitude and longitude.
     */
    ParkingLotDTO nearestParkingLot(Double latitude, Double longitude);

    /**
     * finds how many parking lots you have in 1km radius based on given latitude and longitude.
     */
    ParkingScoreDTO parkingScoreIn1kmRadius(Double latitude, Double longitude);

}
