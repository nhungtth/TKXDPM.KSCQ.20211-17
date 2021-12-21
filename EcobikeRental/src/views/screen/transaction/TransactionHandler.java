package views.screen.transaction;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import controller.BaseController;
import controller.ReturnBikeController;
import controller.TransactionController;
import entity.dock.Dock;
import entity.rentbike.RentBike;
import entity.transaction.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;

public class TransactionHandler extends BaseScreenHandler implements Initializable{
	@FXML
	private TextField cardNumber;
	
	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

	@FXML
	private TextField securityCode;
	
	@FXML
	private Text username;
	
	@FXML
	private Text type;
	
	@FXML
	private Text bikeId;
	
	@FXML
	private Text dockId;
	
	@FXML
	private Text station;
	
	@FXML
	private Text deposit;
	
	@FXML
	private Text fees;
	
	@FXML
	private Text time;
	
	@FXML
	private TextField note;
	
	@FXML
	private Button btnConfirm;
	
	private Dock dock;
	private RentBike bike;

	// for return transaction
	public TransactionHandler(Stage stage, String screenPath, Dock dock) throws SQLException, IOException {
		super(stage, screenPath);
		this.dock = dock;
		this.bike = BaseController.getRentBike();
	}
	
	// for rent transaction
	public TransactionHandler(Stage stage, String screenPath, RentBike bike) throws SQLException, IOException {
		super(stage, screenPath);
		this.bike = bike;
	}
	
	public TransactionController getBController() {
		return (TransactionController) super.getBController();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		username.setText(BaseController.getUser().getName());
		bikeId.setText(bike.getId());
		if(dock != null) {
			dockId.setText(dock.getId());
			station.setText(dock.getStationName());
			deposit.setText(String.valueOf(bike.getDeposit()));
			
			bike.setReturnDock(dock.getId());
			bike.setReturnDate(Utils.getToday());
			
			long t = (bike.getReturnDate().getTime() - bike.getRentDate().getTime())/6000;
			time.setText(String.valueOf(t));
			
			int f = new ReturnBikeController().calculateFees(t);
			fees.setText(String.valueOf(f));
			type.setText(Configs.RETURN);
  		} else {
			dockId.setText(bike.getRentDock());
			deposit.setText("0");
			int f = new ReturnBikeController().calculateDeposit(bike.getId());
			fees.setText(String.valueOf(f));
			type.setText(Configs.RENT);
		}
		
		btnConfirm.setOnMouseClicked(e -> {
			try {
				confirmToPay();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
	}
	
	private void confirmToPay() throws IOException {
		String contents = note.getText();
		TransactionController ctrl = getBController();
		Map<String, String> response;
		int amount;
		if(type.getText() == Configs.RETURN) {
			// refund deposit
			amount  = bike.getDeposit();
			response = ctrl.processTransaction(amount, contents, cardNumber.getText(), holderName.getText(),
					expirationDate.getText(), securityCode.getText(), Configs.REFUND);	
			createTransaction(Configs.REFUND, amount, contents, Configs.RETURN);
		}
		
		// pay fees
		amount = Integer.parseInt(fees.getText());
		response = ctrl.processTransaction(amount, contents, cardNumber.getText(), holderName.getText(),
				expirationDate.getText(), securityCode.getText(), Configs.PAY);	
		createTransaction(Configs.PAY, amount, contents, type.getText());

		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
		resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreenHandler(homeScreenHandler);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}

	private void createTransaction(String type, int amount, String contents, String purpose) {
		Transaction transaction = new Transaction(type, bike, amount, username.getText(), contents, purpose);
		Transaction.saveTransaction(transaction);
	}

	@FXML
	public void goHome(MouseEvent event) {
		homeScreenHandler.show();
	}
}
