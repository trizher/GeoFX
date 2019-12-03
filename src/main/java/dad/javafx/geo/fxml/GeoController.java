package dad.javafx.geo.fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.geo.client.IPAPIService;
import dad.javafx.geo.client.IPIFYService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;

import javafx.scene.control.TabPane;

import javafx.scene.control.Label;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.CheckBox;

import javafx.scene.layout.VBox;

public class GeoController implements Initializable{
	
    @FXML
    private VBox view;

    @FXML
    private HBox ipHbox;

    @FXML
    private ImageView iconImageView;

    @FXML
    private TextField ipText;

    @FXML
    private Button checkIPButton;

    @FXML
    private TabPane datosTabPane;

    @FXML
    private Tab LocationTab;

    @FXML
    private Label latitudeResultLabel;

    @FXML
    private Label longitudeResultLabel;

    @FXML
    private Label ipLocationResultLabel;

    @FXML
    private ImageView ipLocationImageView;

    @FXML
    private Label cityStateResultLabel;

    @FXML
    private Label languageResultLabel;

    @FXML
    private Label callingCodeResultLabel;

    @FXML
    private Label zipCodeResultLabel;

    @FXML
    private Label timeZoneResultLabel;

    @FXML
    private Label currencyResultLabel;

    @FXML
    private Tab ConnectionTab;

    @FXML
    private Label ipAddressResultLabel;

    @FXML
    private Label registeredISPResultLabel;

    @FXML
    private Label typeResultLabel;

    @FXML
    private Label hostnameResultLabel;

    @FXML
    private Label asnResultLabel;

    @FXML
    private Tab SecurityTab;

    @FXML
    private Label isSafeLabel;

    @FXML
    private CheckBox proxyCheck;

    @FXML
    private CheckBox torCheck;

    @FXML
    private CheckBox crawlerCheck;

    @FXML
    private Label threatLevelResultLabel;

    @FXML
    private Label portentialThreatResultLabel;
    
    Geolocation model;
    IPIFYService ipifyService = new IPIFYService();
	IPAPIService ipapiService = new IPAPIService();
	
	public GeoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GeoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ipText.setText(ipifyService.obtenerIP());
			model = ipapiService.ipData(ipText.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		latitudeResultLabel.textProperty().bind(Bindings.createStringBinding(() -> String.format("%.6f", model.getLatitude()).replace(",", "."), model.latitudeProperty()));
		longitudeResultLabel.textProperty().bind(Bindings.createStringBinding(() -> String.format("%.6f", model.getLongitude()).replace(",", "."), model.longitudeProperty()));
		ipLocationResultLabel.textProperty().bind(Bindings.concat(model.country_nameProperty(), " (", model.country_codeProperty(), ")"));
		ipLocationImageView.imageProperty().bind(Bindings.createObjectBinding(() -> new Image("/flags/" + model.getCountry_code() + ".png"),  model.country_codeProperty()));
		cityStateResultLabel.textProperty().bind(Bindings.concat(model.cityProperty(), " (", model.region_nameProperty(), ")"));
		zipCodeResultLabel.textProperty().bind(model.zipProperty());
		languageResultLabel.textProperty().bind(Bindings.createObjectBinding(() 
				-> model.getLocation().languagesProperty().get(0).getName() 
				+ " (" + model.getLocation().languagesProperty().get(0).getCode().toUpperCase() 
				+ ")", model.locationProperty().get().languagesProperty()));
		callingCodeResultLabel.textProperty().bind(model.getLocation().calling_codeProperty());
		ipAddressResultLabel.textProperty().bind(model.ipProperty());
		typeResultLabel.textProperty().bind(model.typeProperty());
		
	}
	
	
	
	public VBox getView() {
		return view;
	}

	@FXML
	public void onCheckIPAction(ActionEvent event) {
		try {
			model.setNewGeolocation(ipapiService.ipData(ipText.getText()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
