package views.screen.stationinfo;

import java.io.IOException;
import java.sql.SQLException;

import controller.BaseController;
import controller.HomeController;
import controller.TransactionController;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.rentbike.RentBike;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.transaction.TransactionHandler;

public class BikeInfoHandler extends BaseScreenHandler {

	private Bike bike;
	private Station b_station;

	@FXML
	private Text id;

	@FXML
	private Text type;

	@FXML
	private Text dock;

	@FXML
	private Text station;

	@FXML
	private Text address;

	@FXML
	private Button rentBtn;

	@FXML
	private Button cancelBtn;

	public BikeInfoHandler(Stage stage, String screenPath, Bike bike, Station station) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
		this.b_station = station;
		setInfo();
	}

	private void setInfo() {
		id.setText(this.bike.getId());
		type.setText(this.bike.getType());
		dock.setText(this.bike.getDockId());
		station.setText(this.b_station.getName());
		address.setText(this.b_station.getAddress());
		rentBtn.setOnMouseClicked(e -> {

			try {
				if (!bike.isStatus()) {
					PopupScreen.error("This bike is not available to rent.");
					return;
				} else if(getBController().getRentBike() != null) {
					PopupScreen.error("You must return current bike.");
					return;
				}
				RentBike rbike = new RentBike();
				rbike.setId(bike.getId());
				rbike.setRentDock(bike.getDockId());
				rbike.setDeposit(getBController().calculateDeposit(bike.getType()));
				rbike.setRentDate(Utils.getToday());

				createTransactionHandler(rbike);
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		cancelBtn.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
	}

	public void createTransactionHandler(RentBike bike) throws SQLException, IOException {
		TransactionHandler transactionHandler = new TransactionHandler(this.stage, Configs.TRANSACTION_PATH, bike);
		transactionHandler.setBController(new TransactionController());
		transactionHandler.setHomeScreenHandler(homeScreenHandler);
		transactionHandler.show();
	}

}
