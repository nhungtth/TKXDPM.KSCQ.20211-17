package entity.dock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.db.EcobikeDB;
import utils.Utils;

public class Dock {
	private static Logger LOGGER = Utils.getLogger(Dock.class.getName());
	private String id;
	private boolean status; // status of dock
	private String stationId; // station of dock point
	private String stationName;
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// get available docks
	public static List<Dock> getDocksAvailable() {
		List<Dock> listDock = new ArrayList<>();
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "SELECT * FROM dock d INNER JOIN station s ON d.station_id = s.station_id WHERE d.status = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			ResultSet rs = preparedStatement.executeQuery(sql);

			while (rs.next()) {
				Dock dock = new Dock();
				dock.setId(rs.getString("dock_id"));
				dock.setStatus(true);
				dock.setStationId(rs.getString("station_id"));
				dock.setAddress(rs.getString("address"));
				dock.setStationName(rs.getString("name"));

				listDock.add(dock);
			}
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listDock;
	}

	public void updateDockStatus(Dock dock) {
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "UPDATE dock SET status = ? WHERE dock_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setBoolean(1, dock.isStatus());
			statement.setString(2, dock.getId());
			int rs = statement.executeUpdate(sql);
			if(rs == 1) {
				LOGGER.info("Update dock " + dock.getId() + "to " + dock.isStatus());
			}
			
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkAvailable(String id) {
		boolean status = false;
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "SELECT status FROM dock WHERE dock_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				status = rs.getBoolean("status");
			}
			
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
