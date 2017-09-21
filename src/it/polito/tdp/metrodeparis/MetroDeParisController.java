/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Model;
import it.polito.tdp.metrodeparis.model.Path;
import it.polito.tdp.metrodeparis.model.Station;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="inputDeparture"
    private ComboBox<Station> inputDeparture; // Value injected by FXMLLoader

    @FXML // fx:id="inputArrival"
    private ComboBox<Station> inputArrival; // Value injected by FXMLLoader

    @FXML // fx:id="showPath"
    private Button showPath; // Value injected by FXMLLoader

    @FXML // fx:id="output"
    private TextArea output; // Value injected by FXMLLoader

    @FXML // fx:id="eta"
    private Label eta; // Value injected by FXMLLoader

    @FXML
    void doShowPath(ActionEvent event) {
    	
    	output.clear();
    	
    	Station departure = inputDeparture.getValue();
    	Station arrival = inputArrival.getValue();
    	
    	if(departure == null && arrival == null){
    		output.setText("ERROR: Insert some value for departure and arrival stations.");
    		return;
    	}
    	
    	if(departure == null){
    		output.setText("ERROR: Insert some value for departure station.");
    		return;
    	}
    	
    	if(arrival == null){
    		output.setText("ERROR: Insert some value for arrival station.");
    		return;
    	}
    	
    	if(departure.equals(arrival)){
    		output.setText("ERROR: Insert different values for departure and arrival station.");
    		return;
    	}
    	
    	Path shortestPath = model.getShortestPath(departure, arrival);
    	
    	for(Station s : shortestPath.getElements()){
    		output.appendText(s.toString()+"\n");
    	}
    	
    	eta.setText("Estimated time arrival: "+shortestPath.getTextEta());

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert inputDeparture != null : "fx:id=\"inputDeparture\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert inputArrival != null : "fx:id=\"inputArrival\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert showPath != null : "fx:id=\"showPath\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert output != null : "fx:id=\"output\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert eta != null : "fx:id=\"eta\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
    }

	public void setModel(Model model) {
		
		this.model = model;
		
		inputDeparture.setDisable(true);
		inputArrival.setDisable(true);
		showPath.setDisable(true);
		output.setDisable(true);
		
		inputDeparture.getItems().addAll(model.getStationList());
		inputArrival.getItems().addAll(model.getStationList());
		
		model.createGraph();
		
		inputDeparture.setDisable(false);
		inputArrival.setDisable(false);
		showPath.setDisable(false);
		output.setDisable(false);
		
	}
}
