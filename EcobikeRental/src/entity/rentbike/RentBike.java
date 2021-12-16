package entity.rentbike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

import entity.db.EcobikeDB;
import entity.dock.Dock;
import utils.Utils;

public class RentBike {
	private static Logger LOGGER = Utils.getLogger(RentBike.class.getName());
	private String id;
	private Date rentDate;
	private Date returnDate;
	private String rentDock;
	private String returnDock;
	private int deposit;

	public RentBike(String id, Date rentDate, String rentDock, int deposit) {
		this.id = id;
		this.rentDate = rentDate;
		this.rentDock = rentDock;
		this.deposit = deposit;
	}

	public RentBike() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getRentDock() {
		return rentDock;
	}

	public void setRentDock(String rentDock) {
		this.rentDock = rentDock;
	}

	public String getReturnDock() {
		return returnDock;
	}

	public void setReturnDock(String returnDock) {
		this.returnDock = returnDock;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	// save rent bike to db + lan
	public void saveRentBike(RentBike bike) {

	}

	// update rent bike in db
	public void updateRentBike(RentBike bike) {
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "UPDATE bike_rent SET return_dock = ? , return_time = ? WHERE bike_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, bike.getReturnDock());
			statement.setTimestamp(2, Utils.getToday());
			statement.setString(3, bike.getId());
			int rs = statement.executeUpdate(sql);
			if (rs == 1) {
				LOGGER.info("Return bike " + bike.getId() + "to " + bike.getReturnDock());
			}

			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static RentBike getCurrentBike() {
		RentBike rentBike = new RentBike();
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "SELECT * FROM bike_rent WHERE return_dock IS NULL";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery(sql);

			if (rs.next()) {
				rentBike = new RentBike(rs.getString("bike_id"), rs.getDate("rent_time"), rs.getString("rent_dock"),
						rs.getInt("deposit"));
				LOGGER.info("Current Bike rented: " + rentBike.getId());
			} else {
				LOGGER.info("There is no bike being rented.");
			}
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rentBike;
	}

}
