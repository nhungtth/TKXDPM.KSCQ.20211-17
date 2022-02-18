package entity.bike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.db.EcobikeDB;

public class StandardEBike extends Bike{
	private int time;
	private int battery;
	
	@Override
	public HashMap getAdvancedInfo(){
		HashMap info = new HashMap();
		try {
			Connection con = EcobikeDB.getConnection();
			String sql = "SELECT * FROM standard_ebike WHERE sebike_id = '" + this.id + "'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet res = statement.executeQuery(sql);
			ArrayList bikes = new ArrayList<>();
			if (res.next()) {
				this.time = res.getInt("time_limit");
				this.battery = res.getInt("battery");
				info.put("time", String.valueOf(this.time));
				info.put("battery", String.valueOf(this.battery));
			}
			statement.close();
			con.close();
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
