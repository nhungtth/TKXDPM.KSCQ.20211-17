package views.screen.stationinfo;

import entity.bike.Bike;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.Utils;
import views.screen.FXMLScreenHandler;


import java.io.IOException;
import java.util.logging.Logger;

public class BikeHandler extends FXMLScreenHandler {
    @FXML
    private Label name;

    @FXML
    private Button infoButton;

    private static Logger LOGGER = Utils.getLogger(BikeHandler.class.getName());
    private Bike bike;

    public BikeHandler(String screenPath, Bike bike) throws IOException {
        super(screenPath);
        this.bike = bike;
        name.setText(bike.getId());
    }
}
