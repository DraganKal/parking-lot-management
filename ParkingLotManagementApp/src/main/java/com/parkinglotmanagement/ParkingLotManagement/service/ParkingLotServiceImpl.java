package com.parkinglotmanagement.ParkingLotManagement.service;

import com.parkinglotmanagement.ParkingLotManagement.configuration.error.BadRequestException;
import com.parkinglotmanagement.ParkingLotManagement.configuration.error.ErrorMessageConstants;
import com.parkinglotmanagement.ParkingLotManagement.model.ParkingLot;
import com.parkinglotmanagement.ParkingLotManagement.model.dto.ParkingLotDTO;
import com.parkinglotmanagement.ParkingLotManagement.model.dto.ParkingScoreDTO;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The implementation of the service used for management of the ParkingLot entity.
 *
 * @author dragan
 */
@Service
@Transactional(readOnly = true)
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public ParkingLotDTO nearestParkingLot(Double latitude, Double longitude) {
        locationRequestValidation(latitude, longitude);
        GeoPoint point = new GeoPoint(latitude, longitude);
        GeoDistanceQueryBuilder queryBuilder = QueryBuilders.geoDistanceQuery("location")
                .point(point)
                .distance(20000, DistanceUnit.KILOMETERS)
                .geoDistance(GeoDistance.PLANE);

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withSorts(SortBuilders.geoDistanceSort("location", point)
                        .order(SortOrder.ASC))
                .withPageable(PageRequest.of(0, 1));

        SearchHits<ParkingLot> searchHits = elasticsearchOperations.search(searchQueryBuilder.build(), ParkingLot.class);

        if (searchHits.getTotalHits() > 0) {
            ParkingLot parkingLot = searchHits.getSearchHit(0).getContent();
            return convertParkingLotToDTO(parkingLot);
        } else {
            return null;
        }
    }

    @Override
    public ParkingScoreDTO parkingScoreIn1kmRadius(Double latitude, Double longitude) {
        locationRequestValidation(latitude, longitude);
        GeoPoint point = new GeoPoint(latitude, longitude);
        GeoDistanceQueryBuilder queryBuilder = QueryBuilders.geoDistanceQuery("location")
                .point(point)
                .distance(1, DistanceUnit.KILOMETERS)
                .geoDistance(GeoDistance.PLANE);

        SearchHits<ParkingLot> searchHits = elasticsearchOperations.search(
                new NativeSearchQueryBuilder().withQuery(queryBuilder).build(),
                ParkingLot.class,
                IndexCoordinates.of("parking-lot"));

        ParkingScoreDTO parkingScoreDTO = new ParkingScoreDTO(searchHits.getTotalHits());
        return parkingScoreDTO;
    }

    private ParkingLotDTO convertParkingLotToDTO(ParkingLot parkingLot) {
        ParkingLotDTO parkingLotDTO = new ParkingLotDTO();
        parkingLotDTO.setId(parkingLot.getId());
        parkingLotDTO.setLatitude(parkingLot.getLatitude());
        parkingLotDTO.setLongitude(parkingLot.getLongitude());
        parkingLotDTO.setName(parkingLot.getName());
        parkingLotDTO.setYear(parkingLot.getYear());
        return parkingLotDTO;
    }

    private void locationRequestValidation(Double latitude, Double longitude) {
        if(latitude == null || longitude == null) {
            throw new BadRequestException(ErrorMessageConstants.LATITUDE_AND_LONGITUDE_ARE_REQUIRED);
        }

        if(latitude < -90 || latitude > 90) {
            throw new BadRequestException(ErrorMessageConstants.LATITUDE_BAD_REQUEST);
        }
        if(longitude < -180 || longitude > 180) {
            throw new BadRequestException(ErrorMessageConstants.LONGITUDE_BAD_REQUEST);
        }
    }

}
