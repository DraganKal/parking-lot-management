package com.parkinglotmanagement.ParkingLotManagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity used for exposing ParkingScore data through API.
 *
 * @author dragan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingScoreDTO {

    private long score;

}
