package entity.station;

import java.util.List;

import entity.bike.Bike;
import entity.dock.Dock;

public class Station {
	private String id;
	private String name;
	private double area;
	private int bikeQuantity;	// quantity of bikes in station
	private int emptyDocks;	// quantity of empty docks in station
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
	public List<Station> getAllStations(){
		
	}
	
	// get station by id
	public Station getStationById(String id) {
		
	}
	
	// nhung bike quantity & empty dock
	public void updateStation(Station station) {
		
	}
}
