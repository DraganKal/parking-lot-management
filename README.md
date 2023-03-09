# Parking Lot Management Application

Project Overview:
  This application provides the nearest parking lot based on given latitude and longitude. 
  Application also provides the parking score (number of parking lots in 1km radius) based on given latitude and longitude.
  
Prerequisites:
  - Java 11
  - Maven 3.6.0 or higher
  - Elasticsearch 7.0 or higher

Installation:
  - First you need to run Elasticsearch 7.0 or higher
  - Next you need to set cluster-name and cluster-nodes in application.yml
  - You can run this project from IntelliJ (Run ParkingLotManagementApp). You need to use Java 11 sdk
  - You can also run this project from console:
    - navigate to folder with pom.xml and src folder
    - run command: mvn spring-boot:run
    
Usage:
  - We provided PARKING-LOT.postman_collection.json file so you can import that file in Postman
  - You have two requests:
    - GET The nearest parking
    - GET Parking score
  - You can also run provided curl calls through terminal
    
Api Endpoints:
  - GET /parking-lot/nearest-parking : Returns the closest parking lot to the specified latitude and longitude.
  - GET /parking-lot/parking-score : Returns the parking score for the specified latitude and longitude.
  
Postman Collections (postman-collections folder):
  - PARKING-LOT.postman_collection.json
