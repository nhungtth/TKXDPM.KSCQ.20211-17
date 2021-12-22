package views.screen.stationinfo;

import entity.bike.Bike;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.Utils;
import views.screen.FXMLScreenHandler;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BikeHandler extends FXMLScreenHandler {
    @FXML
    private Label name;

    @FXML
    private Button infoBtn;

    private static Logger LOGGER = Utils.getLogger(BikeHandler.class.getName());
    private Bike bike;
    private StationScreenHandler stationScreenHandler;

    public BikeHandler(String screenPath, Bike bike, StationScreenHandler stationScreenHandler) throws IOException {
        super(screenPath);
        this.bike = bike;
        this.stationScreenHandler = stationScreenHandler;
        name.setText(bike.getId());
        
        infoBtn.setOnMouseClicked(e->{
        	try {
				stationScreenHandler.createBikeInfoHandler(this.bike);
			} catch (SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
    }
}
