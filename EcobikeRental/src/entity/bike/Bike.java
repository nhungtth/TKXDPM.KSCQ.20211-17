package entity.bike;

import java.sql.*;
<<<<<<< HEAD

import entity.db.EcobikeDB;
import entity.dock.Dock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
=======
import java.util.ArrayList;
import java.util.List;

import entity.db.EcobikeDB;
import entity.station.Station;
>>>>>>> cb3ee726151a7639b51804379240592a26e7a7d5

public class Bike {
	private String id;
	private String type;
	private int price;
	private boolean status; // status of bike
	private String dockId;

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

	public String getDockId() {
		return dockId;
	}

	public void setDockId(String dockId) {
		this.dockId = dockId;
	}

	// lay thong tin ve xe trong csdl
	public Bike getBikeById(String id) {
		try {
			Bike bike = new Bike();
			Connection con = EcobikeDB.getConnection();
			String sql = "SELECT * FROM bike WHERE bike_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery(sql);
			rs.next();

			bike.setId(rs.getString("bike_id"));
			bike.setStatus(rs.getBoolean("status"));
			bike.setType(rs.getString("type"));
			bike.setPrice(rs.getInt("price"));
			bike.setDockId(rs.getString("dock_id"));

			statement.close();
			con.close();

			return bike;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
		List<Bike> listBikes = new ArrayList<>();
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "SELECT * FROM bike b INNER JOIN dock d ON b.dock_id = d.id JOIN station s ON s.id = d.id WHERE b.status = ? AND s.name = ?;";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setBoolean(1, true);
			statement.setString(2, name);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getString("bike_id"));
				bike.setStatus(true);
				bike.setType(rs.getString("type"));
				bike.setPrice(rs.getInt("price"));
				bike.setDockId(rs.getString("dock_id"));

				listBikes.add(bike);
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBikes;
	}

	//update bike status - Lan
	public void updateBikeStatus(Bike bike) {
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "UPDATE bike SET status = ? WHERE bike_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setBoolean(1, bike.isStatus());
			statement.setString(2, bike.getId());
			statement.executeUpdate(sql);
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveRentBike(Bike bike) {
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "INSERT INTO 'bike_rent' (`bike_id`, `rent_time`, `return_time`, `rent_dock`, `return_dock`) VALUES ('?', '?', null, '?', null);";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			Timestamp rentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
			statement.setTimestamp(2, rentTime);
			statement.setString(3, dockId);
			statement.executeUpdate(sql);
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
