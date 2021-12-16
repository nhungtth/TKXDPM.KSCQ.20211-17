package entity.station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import entity.bike.Bike;
import entity.db.EcobikeDB;
import entity.dock.Dock;
import utils.Utils;

public class Station {
	private static Logger LOGGER = Utils.getLogger(Station.class.getName());
	private String id;
	private String name;
	private double area;
	private int bikeQuantity; // quantity of bikes in station
	private int emptyDocks; // quantity of empty docks in station
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getBikeQuantity() {
		return bikeQuantity;
	}

	public void setBikeQuantity(int bikeQuantity) {
		this.bikeQuantity = bikeQuantity;
	}

	public int getEmptyDocks() {
		return emptyDocks;
	}

	public void setEmptyDocks(int emptyDocks) {
		this.emptyDocks = emptyDocks;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// search Station by name + linh
	public List<Station> getStationByName(String name) {

	}

	// get all stations
	public List<Station> getAllStations() {

	}

	// get station by id
	public Station getStationById(String id) {

	}

	// update info for station
	public void updateStation(Station station) {
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "UPDATE station SET bike_quantity = ? , empty_docks = ? WHERE station_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, station.getBikeQuantity());
			statement.setInt(2, station.getEmptyDocks());
			statement.setString(3, station.getId());
			int rs = statement.executeUpdate(sql);

			LOGGER.info("Update station " + station.getId() + ": " + station.getBikeQuantity() + "bikes, "
					+ station.getEmptyDocks() + "docks.\n");
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
