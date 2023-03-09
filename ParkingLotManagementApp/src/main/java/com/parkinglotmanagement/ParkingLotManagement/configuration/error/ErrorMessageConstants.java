package com.parkinglotmanagement.ParkingLotManagement.configuration.error;

/**
 * Error constants. Used for calling errors for BadRequestException.
 *
 * @author dragan
 */
public class ErrorMessageConstants {

    public static final String LATITUDE_BAD_REQUEST = "Latitude must be between -90.00 and 90.00";
    public static final String LONGITUDE_BAD_REQUEST = "Longitude must be between -180.00 and 180.00";
    public static final String LATITUDE_AND_LONGITUDE_ARE_REQUIRED = "Latitude and longitude must be decimal numbers";

}
