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
import controller.TransactionController;
import entity.bike.Bike;
import entity.rentbike.RentBike;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.home.SearchStationScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.stationinfo.BikeHandler;
import views.screen.transaction.RentTransactionHandler;


public class RentBikeHandler extends BaseScreenHandler implements Initializable {
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	@FXML
	private AnchorPane pane;

	@FXML
	private Button searchButton;

	@FXML
	private TextField search;

	private List bikes;
	private List rentBikeItems;

	public RentBikeController getBController() {
		return (RentBikeController) super.getBController();
	}

	public RentBikeHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		setBController(new RentBikeController());
		List bikes = getBController().getAllBikeAvailable();
		this.rentBikeItems = new ArrayList<>();
        for(Object o: bikes) {
            Bike bike = (Bike) o;
            BikeHandler handler = new BikeHandler(Configs.BIKE_STATION, bike, this);
            this.rentBikeItems.add(handler);
        }
        addBikesToGrid(this.rentBikeItems);
	}
	
	private void addBikesToGrid(List items) {
        ArrayList list = (ArrayList)((ArrayList) items).clone();
        GridPane grid = new GridPane();
        grid.setMaxWidth(pane.getMaxWidth());
        int c = 0;
        int r = 0;
        for(Object item: list) {
            BikeHandler dh = (BikeHandler) item;
            if (c > 1) {
				c = 0;
				r++;
			}
            grid.add(dh.getContent(), c, r);
            c++;
        }
        this.pane.getChildren().add(grid);
    }

	public void createTransactionHandler(Bike bike) throws SQLException, IOException {
		RentBike rbike = new RentBike();
		rbike.setId(bike.getId());
		rbike.setRentDock(bike.getDockId());
		rbike.setDeposit(getBController().calculateDeposit(bike.getType()));
		rbike.setRentDate(Utils.getToday());
		RentTransactionHandler transactionHandler = new RentTransactionHandler(this.stage, Configs.RENT_TRANSACTION_PATH, rbike);
		transactionHandler.setBController(new TransactionController());
		transactionHandler.setHomeScreenHandler(this.homeScreenHandler);
		transactionHandler.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void goHome(MouseEvent e) {
		homeScreenHandler.show();
	}
}
