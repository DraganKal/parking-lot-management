package com.parkinglotmanagement.ParkingLotManagement.controller;

import com.parkinglotmanagement.ParkingLotManagement.model.dto.ParkingLotDTO;
import com.parkinglotmanagement.ParkingLotManagement.model.dto.ParkingScoreDTO;
import com.parkinglotmanagement.ParkingLotManagement.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller exposing API for ParkingLot entity.
 * URL : /parking-lot
 *
 * @author dragan
 */
@RestController
@RequestMapping("/parking-lot")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    /**
     * GET /nearest-parking: finds the nearest parking based on given latitude and longitude
     *
     * request params - latitude: Double, longitude: Double
     * return: JSON object with params - id: String, latitude: Double, longitude: Double, name: String, year: int, type: String
     */
    @GetMapping(value = "/nearest-parking")
    public ResponseEntity<ParkingLotDTO> theNearestParkingLot(@RequestParam Double latitude, @RequestParam Double longitude) {
        return new ResponseEntity<>(
                parkingLotService.nearestParkingLot(latitude, longitude),
                HttpStatus.OK
        );
    }

    /**
     * GET /parking-score: finds how many parking lots you have in 1km radius based on given latitude and longitude
     *
     * request params - latitude: Double, longitude: Double
     * return: JSON object with params - score: long
     */
    @GetMapping(value = "/parking-score")
    public ResponseEntity<ParkingScoreDTO> parkingScore(@RequestParam Double latitude, @RequestParam Double longitude) {
        return new ResponseEntity<>(
                parkingLotService.parkingScoreIn1kmRadius(latitude, longitude),
                HttpStatus.OK
        );
    }

}
