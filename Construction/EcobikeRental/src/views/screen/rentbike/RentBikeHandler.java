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
import views.screen.popup.PopupScreen;
import views.screen.transaction.TransactionHandler;

public class RentBikeHandler extends BaseScreenHandler implements Initializable {
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

	public RentBikeHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	public void createTransactionHandler(Bike bike) throws SQLException, IOException {
		RentBike rbike = new RentBike();
		rbike.setId(bike.getId());
		rbike.setRentDock(bike.getDockId());
		rbike.setDeposit(getBController().calculateDeposit(bike.getType()));
		rbike.setRentDate(Utils.getToday());
		TransactionHandler transactionHandler = new TransactionHandler(this.stage, Configs.TRANSACTION_PATH, rbike);
		transactionHandler.setBController(new TransactionController());
		transactionHandler.setHomeScreenHandler(this.homeScreenHandler);
		transactionHandler.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new RentBikeController());
		List Bike = getBController().getAllBikeAvailable();
		this.rentBikeItems = (ArrayList) ((ArrayList) Bike).clone();

		Bike bike_1 = (Bike) rentBikeItems.get(0);
		Bike bike_2 = (Bike) rentBikeItems.get(1);
		bikeid_1.setText(String.valueOf(bike_1.getId()));
		bikeid_2.setText(String.valueOf(bike_2.getId()));

		dock_1.setText(String.valueOf(bike_1.getDockId()));
		dock_2.setText(String.valueOf(bike_2.getDockId()));
		button_1.setOnMouseClicked(e -> {
			try {
				if (getBController().checkBikeAvalaible(bike_1.getId()))
					createTransactionHandler(bike_1);
				else
					PopupScreen.error("This bike is not available now.");
			} catch (SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		button_2.setOnMouseClicked(e -> {
			try {
				if (getBController().checkBikeAvalaible(bike_2.getId()))
					createTransactionHandler(bike_2);
				else 
					PopupScreen.error("This bike is not available now.");
			} catch (SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
}
