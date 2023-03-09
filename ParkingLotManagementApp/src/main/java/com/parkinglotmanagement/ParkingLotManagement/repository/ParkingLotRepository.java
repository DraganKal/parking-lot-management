package com.parkinglotmanagement.ParkingLotManagement.repository;

import com.parkinglotmanagement.ParkingLotManagement.model.ParkingLot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * ElasticSearch repository for management of the ParkingLot entity.
 *
 * @author dragan
 */
public interface ParkingLotRepository extends ElasticsearchRepository<ParkingLot, String> {

}
