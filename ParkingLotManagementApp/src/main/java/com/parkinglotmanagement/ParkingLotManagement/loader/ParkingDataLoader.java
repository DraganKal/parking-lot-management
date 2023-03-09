package com.parkinglotmanagement.ParkingLotManagement.loader;

import com.opencsv.CSVReader;
import com.parkinglotmanagement.ParkingLotManagement.model.ParkingLot;
import com.parkinglotmanagement.ParkingLotManagement.model.enums.ParkingLotType;
import com.parkinglotmanagement.ParkingLotManagement.repository.ParkingLotRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The class used to load parking lot data from a csv file and write the data to the ElasticSearch database.
 *
 * @author dragan
 */
@Slf4j
@Component
public class ParkingDataLoader {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @PostConstruct
    public void load() {

        try {
//        This will delete all records from database(refresh)
            parkingLotRepository.deleteAll();

            CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/parking-lots.csv"));

//        skipping first line(names)
            reader.skip(1);

            String[] nextLine;
            List<ParkingLot> parkingLots = new ArrayList<>();
            while ((nextLine = reader.readNext()) != null) {
                double latitude = Double.parseDouble(nextLine[0]);
                double longitude = Double.parseDouble(nextLine[1]);
                String name = nextLine[2];
                int year = Integer.parseInt(nextLine[3]);
                String type = nextLine[4];

                ParkingLot parkingLot = new ParkingLot();
                parkingLot.setName(name);
                parkingLot.setLatitude(latitude);
                parkingLot.setLongitude(longitude);
                parkingLot.setYear(year);
                parkingLot.setType(ParkingLotType.valueOf(type.toUpperCase()));
                parkingLot.setLocation(new GeoPoint(latitude, longitude));

                parkingLots.add(parkingLot);
            }
            parkingLotRepository.saveAll(parkingLots);
        } catch (Exception e) {
            log.error("---Loading data error: ", e.getMessage());
        }
    }

}
