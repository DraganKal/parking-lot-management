package com.parkinglotmanagement.ParkingLotManagement.model.dto;

import com.parkinglotmanagement.ParkingLotManagement.model.enums.ParkingLotType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity used for exposing ParkingLot data through API.
 *
 * @author dragan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotDTO {

    private String id;

    private Double latitude;

    private Double longitude;

    private String name;

    private int year;

    private ParkingLotType type;

}
