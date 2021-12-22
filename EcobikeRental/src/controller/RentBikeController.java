package controller;

import entity.db.EcobikeDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entity.bike.Bike;
import entity.station.Station;

public class RentBikeController extends BaseController {
    public List<Bike> getBikesAvailableByStationName(String name){
        return Bike.getBikesAvailableByStationName(name);
    }
    public List<Bike> getAllBikeAvailable(){ return Bike.getAllBikeAvailable();}
    public void updateBikeStatus(Bike bike){
        bike.updateBikeStatus(bike);
    }
    public void saveRentBike(Bike bike){
        bike.saveRentBike(bike);
    }
    }
}
