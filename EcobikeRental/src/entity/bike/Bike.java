package entity.bike;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.db.EcobikeDB;
import entity.station.Station;

public class Bike {
	private String id;
	private String type;
	private int price;
	private boolean status; // status of bike
	private String dock_id;

	public String getId() {
		return id;
	}

	public Bike setId(String id) {
		this.id = id;
		return this;
	}

	public String getType() {
		return type;
	}

	public Bike setType(String type) {
		this.type = type;
		return this;
	}

	public int getPrice() {
		return price;
	}

	public Bike setPrice(int price) {
		this.price = price;
		return this;
	}

	public boolean isStatus() {
		return status;
	}

	public Bike setStatus(boolean status) {
		this.status = status;
		return this;
	}

	public String getDock_id() {
		return dock_id;
	}

	public Bike setDock_id(String dock_id) {
		this.dock_id = dock_id;
		return this;
	}

	// lay thong tin ve xe trong csdl
	public Bike getBikeById(String id) {
	
	}

	public List<Bike> getAllBike() {

	}

	// linh
	public List getBikesByStationId(String stationId) throws SQLException{
		String sql = "SELECT * FROM bike b, dock d WHERE b.dock_id = d.dock_id AND d.station_id=" + stationId + ";";
		Statement stm = EcobikeDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		ArrayList bikes = new ArrayList<>();
		while (res.next()) {
			Bike bike = new Bike()
					.setId(res.getString("bike_id"))
					.setType(res.getString("type"))
					.setDock_id(res.getString("dock_id"))
					.setPrice(res.getInt("price"))
					.setStatus(res.getBoolean("status"));
			bikes.add(bike);
		}
		return bikes;
	}

	// lan check status = true
	public List<Bike> getBikesAvailableByStationName(String name) {
		
	}
	
	public void updateBikeStatus(Bike bike) {
		
	}
}
