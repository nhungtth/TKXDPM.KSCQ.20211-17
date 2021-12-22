package views.screen.rentbike;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.HomeController;
import controller.RentBikeController;
import entity.bike.Bike;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.home.SearchStationScreenHandler;

public class RentBikeHandler extends BaseScreenHandler implements Initializable{
    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
    @FXML
    private AnchorPane pane;

    @FXML
    private Button searchButton;

    @FXML
    private Label bikeid_1;

    @FXML
    private Label bikeid_2;

    @FXML
    private Label station_1;

    @FXML
    private Label station_2;

    @FXML
    private Label dock_1;

    @FXML
    private Label dock_2;

    @FXML
    private Button button_1;

    @FXML
    private Button button_2;

    @FXML
    private TextField search;

    private List Bike;
    private List rentBikeItems;

    public RentBikeController getBController() {
        return (RentBikeController) super.getBController();
    }

    public RentBikeHandler(Stage stage, String screenPath, AnchorPane pane, TextField searchInput, Label bikeid_1, Label bikeid_2, List bike) throws IOException {
        super(stage, screenPath);
        this.pane = pane;
        this.bikeid_1 = bikeid_1;
        this.bikeid_2 = bikeid_2;
        Bike = bike;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBController(new RentBikeController());
        List Bike = getBController().getAllBikeAvailable();
        this.rentBikeItems = (ArrayList)((ArrayList)  Bike).clone();

        Bike bike_1 = (Bike)rentBikeItems.get(0);
        Bike bike_2 = (Bike)rentBikeItems.get(1);
        bikeid_1.setText(String.valueOf(bike_1.getId()));
        bikeid_2.setText(String.valueOf(bike_2.getId()));
        //get station name 1 by bikeid_1 station_1.setText(String.valueOf(bike_1.));
        //get station name 2 by bikeid_2 station_2.setText(String.valueOf(bike_2.));
        dock_1.setText(String.valueOf(bike_1.getDockId()));
        dock_2.setText(String.valueOf(bike_2.getDockId()));

    }

}
