package controller;

import entity.bike.Bike;
import entity.station.Station;

import java.sql.SQLException;
import java.util.List;


/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */
public class HomeController extends BaseController{


    /**
     * this method gets all Station in DB and return back to home to display
     * @return List[Station]
     * @throws SQLException
     */
    public List getAllStation() throws SQLException{
        return new Station().getAllStation();
    }

    public List getBikesByStationId(String stationId) throws SQLException{
        return Bike.getBikesByStationId(stationId);
    }

    public Station getSByStationName(String name) throws SQLException{
        return Station.getStationByName(name);
    }

}
