# Parking Lot Management Application

Project Overview:
  This application provides the nearest parking lot based on given latitude and longitude. 
  Application also provides the parking score (number of parking lots in 1km radius) based on given latitude and longitude.
  
Prerequisites:
  - Java 11
  - Maven 3.6.0 or higher
  - Elasticsearch 7.0 or higher

Installation:
  - First, you need to run Elasticsearch 7.0 or higher.
  - Next, you need to set the cluster-name and cluster-nodes in the application.yml file.
  - You can run this project from IntelliJ (run ParkingLotManagementApp). You need to use Java 11 SDK.
  - You can also run this project from the console:
    - Navigate to the folder with the pom.xml file and src folder (ParkingLotManagementApp). 
    - Run the command: mvn spring-boot:run
    
Usage:
  - We have provided the PARKING-LOT.postman_collection.json file in the postman-collections folder so you can import file in Postman
  - You have two requests:
    - GET The nearest parking
    - GET Parking score
  - You can also run the provided curl calls through the terminal.
    
Api Endpoints:
  - GET /parking-lot/nearest-parking : Returns the closest parking lot to the specified latitude and longitude.
    - Request params: 
      - latitude: decimal number from -90 to 90 (degrees)
      - longitude: decimal number from -180 to 180 (degrees)
  - GET /parking-lot/parking-score : Returns the parking score for the specified latitude and longitude in degrees.
    - Request params: 
      - latitude: decimal number from -90 to 90 (degrees)
      - longitude: decimal number from -180 to 180 (degrees)
  
Curl calls:
  - curl http://localhost:8080/parking-lot/nearest-parking?latitude=33.7437729&longitude=-118.3812524
  - curl http://localhost:8080/parking-lot/parking-score?latitude=33.7437729&longitude=-118.3812524
  
Postman Collections (postman-collections folder):
  - PARKING-LOT.postman_collection.json
