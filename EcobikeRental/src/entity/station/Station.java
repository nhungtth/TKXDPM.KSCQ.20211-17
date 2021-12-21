package entity.station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.bike.Bike;
import entity.db.EcobikeDB;
import entity.dock.Dock;
import utils.Utils;
import entity.db.EcobikeDB;

public class Station {
	private static Logger LOGGER = Utils.getLogger(Station.class.getName());
	private String id;
	private String name;
	private double area;
	private int bikeQuantity; // quantity of bikes in station
	private int emptyDocks; // quantity of empty docks in station
	private String address;
	private List<Bike> bikes;


	public String getId() {
		return id;
	}

	public Station setId(String id) {
		this.id = id;
		return this;
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public String getName() {
		return name;
	}

	public Station setName(String name) {
		this.name = name;
		return this;
	}

	public double getArea() {
		return area;
	}

	public Station setArea(double area) {
		this.area = area;
		return this;
	}

	public int getBikeQuantity() {
		return bikeQuantity;
	}

	public Station setBikeQuantity(int bikeQuantity) {
		this.bikeQuantity = bikeQuantity;
		return this;
	}

	public int getEmptyDocks() {
		return emptyDocks;
	}

	public Station setEmptyDocks(int emptyDocks) {
		this.emptyDocks = emptyDocks;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Station setAddress(String address) {
		this.address = address;
		return this;
	}

	// search Station by name + linh
	public Station getStationByName(String name) throws SQLException{
		String sql = "SELECT * FROM station WHERE name=" + name;
		Statement stm = EcobikeDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		if(res.next()) {
			return new Station()
					.setId(res.getString("station_id"))
					.setName(res.getString("name"))
					.setAddress(res.getString("address"))
					.setArea(res.getInt("area"))
					.setBikeQuantity(res.getInt("bike_quantity"))
					.setEmptyDocks(res.getInt("empty_docks"));
		}
		return null;
	}

	// get station by id
	public Station getStationById(String id) throws SQLException{
		String sql = "SELECT * FROM station ";
		Statement stm = EcobikeDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		if(res.next()) {
			return new Station()
					.setId(res.getString("station_id"))
					.setName(res.getString("name"))
					.setAddress(res.getString("address"))
					.setArea(res.getInt("area"))
					.setBikeQuantity(res.getInt("bike_quantity"))
					.setEmptyDocks(res.getInt("empty_docks"));
		}
		return null;
	}

	// get all stations
	public List getAllStation() throws SQLException{
		Statement stm = EcobikeDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery("select * from station");
		ArrayList stations = new ArrayList<>();
		while (res.next()) {
			Station station = new Station()
					.setId(res.getString("station_id"))
					.setName(res.getString("name"))
					.setAddress(res.getString("address"))
					.setArea(res.getInt("area"))
					.setBikeQuantity(res.getInt("bike_quantity"))
					.setEmptyDocks(res.getInt("empty_docks"));
			stations.add(station);
		}
		return stations;
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
