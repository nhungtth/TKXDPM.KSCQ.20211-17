package entity.bike;

public class Bike {
	private String id;
	private String type;
	private int price;
	private boolean status; // status of bike
	private String dock_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	// lay thong tin ve xe trong csdl
	public Bike getBikeById(String id) {

	}

	public List<Bike> getAllBike() {

	}

	// linh
	public List<Bike> getBikesByStationId(String id) {

	}

	// lan check status = true
	public List<Bike> getBikesAvailableByStationName(String name) {
		
	}
	
	public void updateBikeStatus(Bike bike) {
		
	}
}
