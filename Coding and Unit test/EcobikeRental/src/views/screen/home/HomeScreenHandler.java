package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import common.exception.InvalidDeliveryInfoException;
import controller.HomeController;
import controller.RentBikeController;
import controller.ReturnBikeController;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.rentbike.RentBikeHandler;
import views.screen.returnbike.ReturnBikeHandler;
import views.screen.stationinfo.StationScreenHandler;


public class HomeScreenHandler extends BaseScreenHandler implements Initializable{

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private Label title_1;
    @FXML
    private Label title_2;

    @FXML
    private Label addr_1;

    @FXML
    private Label addr_2;

    @FXML
    private Label bike_1;

    @FXML
    private Label bike_2;

    @FXML
    private Label dock_1;

    @FXML
    private Label dock_2;

    @FXML
    private Button info_1;

    @FXML
    private Button info_2;

    @FXML
    private TextField search;

    @FXML
    private Button rentBtn;

    @FXML
    private Button returnBtn;


    private List homeItems;

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
    }

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new HomeController());
        try{
            List stations = getBController().getAllStation();
            this.homeItems = (ArrayList)((ArrayList)  stations).clone();
        }catch (SQLException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }

        Station station_1 = (Station)homeItems.get(0);
        Station station_2 = (Station)homeItems.get(1);

        title_1.setText(String.valueOf(station_1.getName()));
        title_2.setText(String.valueOf(station_2.getName()));
        addr_1.setText(String.valueOf(station_1.getAddress()));
        addr_2.setText(String.valueOf(station_2.getAddress()));
        bike_1.setText(String.valueOf(station_1.getBikeQuantity()));
        bike_2.setText(String.valueOf(station_2.getBikeQuantity()));
        dock_1.setText(String.valueOf(station_1.getEmptyDocks()));
        dock_2.setText(String.valueOf(station_2.getEmptyDocks()));

        info_1.setOnMouseClicked(e -> {
            try {
                getInfo(station_1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        info_2.setOnMouseClicked(e -> {
            try {
                getInfo(station_2);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        returnBtn.setOnMouseClicked(e ->{
        	try {
				ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(this.stage, Configs.RETURN_BIKE_PATH);
				returnBikeHandler.setHomeScreenHandler(this);
				returnBikeHandler.setBController(new ReturnBikeController());
				returnBikeHandler.setPreviousScreen(this);
				returnBikeHandler.setScreenTitle("Return Bike");
				returnBikeHandler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        });
        
        rentBtn.setOnMouseClicked(e->{
        	try {
				RentBikeHandler rentBikeHandler = new RentBikeHandler(this.stage, Configs.RENT_BIKE_PATH);
				rentBikeHandler.setHomeScreenHandler(this);
				rentBikeHandler.setBController(new RentBikeController());
				rentBikeHandler.setScreenTitle("Rent Bike");
				rentBikeHandler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
    }
    
    public void createRentBikeHandler() {
    	
    }
    public void getInfo(Station station) throws IOException{
        StationScreenHandler stationScreen;
        stationScreen = new StationScreenHandler(this.stage, Configs.STATION_INFO_PATH, station);
        stationScreen.setHomeScreenHandler(this);
        stationScreen.setBController(new HomeController());
        stationScreen.setPreviousScreen(this);
        stationScreen.setScreenTitle("Station Screen");
        stationScreen.show();
    }

    @FXML
    void requestToSearch(MouseEvent event) throws IOException, InterruptedException, SQLException {
        String key = search.getText();
        Station station_rs = getBController().getSByStationName(key);
        if(station_rs == null)
        	return;
        SearchStationScreenHandler searchStationScreen;
        searchStationScreen = new SearchStationScreenHandler(this.stage, Configs.SEARCH_STATION, station_rs);
        searchStationScreen.setHomeScreenHandler(this);
        searchStationScreen.setBController(new HomeController());
        searchStationScreen.setPreviousScreen(this);
        searchStationScreen.setScreenTitle("Search Station Screen");
        searchStationScreen.show();

    }
}
