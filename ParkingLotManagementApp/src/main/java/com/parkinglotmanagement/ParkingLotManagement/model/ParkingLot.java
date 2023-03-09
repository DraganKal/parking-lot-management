package com.parkinglotmanagement.ParkingLotManagement.model;

import com.parkinglotmanagement.ParkingLotManagement.model.enums.ParkingLotType;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * The ParkingLot entity representing all parking lots of the system.
 *
 * @author dragan
 */
@Getter
@Setter
@Document(indexName = "parking-lot")
public class ParkingLot {

    @Id
    private String id;

    @Field(type = FieldType.Double)
    private Double latitude;

    @Field(type = FieldType.Double)
    private Double longitude;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Integer)
    private int year;

    @Field(type = FieldType.Keyword)
    private ParkingLotType type;

    @GeoPointField
    private GeoPoint location;

}
