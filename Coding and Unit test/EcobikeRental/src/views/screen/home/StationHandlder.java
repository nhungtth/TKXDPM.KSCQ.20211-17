package views.screen.home;

import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import views.screen.FXMLScreenHandler;

import java.io.IOException;

public class StationHandlder  extends FXMLScreenHandler{

    @FXML
    private Label name;

    @FXML
    private Label addr;

    @FXML
    private Label bike;

    @FXML
    private Label dock;

    private  Station station;

    public StationHandlder(String screenPath, Station station) throws IOException {
        super(screenPath);
        this.station= station;
        name.setText(station.getName());
        addr.setText(station.getAddress());
        bike.setText(String.valueOf(station.getBikeQuantity()));
        dock.setText(String.valueOf(station.getEmptyDocks()));
    }
}
