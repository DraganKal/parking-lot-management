package com.parkinglotmanagement.ParkingLotManagement.configuration.error;

/**
 * Used for throwing Exceptions to a client side.
 *
 * @author dragan
 */
public class BadRequestException extends IllegalArgumentException {

    public BadRequestException(String message) {
        super(message);
    }

}
