package entity.dock;

public class Dock {
	private String id;
	private boolean status; // status of dock
	private String stationId; // station of dock point
	
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
	
	// get docks by station id
	public List<Dock> getDocksAvailableByStation(String id){
		
	}
	
	public void updateDockStatus(Dock dock) {
		
	}
}
