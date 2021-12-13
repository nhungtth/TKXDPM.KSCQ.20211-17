package entity.rentbike;

import java.util.Date;

public class RentBike {
	private String id;
	private Date rentDate;
	private Date returnDate;
	private String rentDock;
	private String returnDock;
	
	public RentBike(String id, Date rentDate, String rentDock) {
		this.id = id;
		this.rentDate = rentDate;
		this.rentDock = rentDock;
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
	
	// save rent bike to db + lan
	public void saveRentBike(RentBike bike) {
		
	}
	
	// update rent bike + nhung
	public void updateRentBike(RentBike bike) {
		
	}
	
}
