package controller;

import entity.db.EcobikeDB;
import entity.rentbike.RentBike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entity.bike.Bike;
import entity.station.Station;

/**
 * this class controls the flow of events in rent bike usecase
 * 
 * @author LanVTN
 *
 */
public class RentBikeController extends BaseController {

	/**
	 * this method get all bikes available in station with name
	 * 
	 * @param name: station name
	 * @return List[Bike]
	 */
	public List<Bike> getBikesAvailableByStationName(String name) {
		return Bike.getBikesAvailableByStationName(name);
	}

	/**
	 * this method check if bike is available now
	 * @param id: id of bike
	 * @return true if bike is available, else false
	 */
	public boolean checkBikeAvalaible(String id) {
		Bike bike = new Bike().getBikeById(id);
		if (bike != null) {
			return bike.isStatus();
		} else {
			return false;
		}
	}

	/**
	 * this method gets all bike available
	 * 
	 * @return List[Bike]
	 */
	public List<Bike> getAllBikeAvailable() {
		return Bike.getAllBikeAvailable();
	}

	/**
	 * this method updates status of bike in DB
	 * 
	 * @param bike
	 */
	public void updateBikeStatus(Bike bike) {
		bike.updateBikeStatus(bike);
	}

	/**
	 * this method save a new bike which be rented
	 * 
	 * @param bike
	 */
	public void saveRentBike(RentBike bike) {
		new RentBike().saveRentBike(bike);
	}

}
