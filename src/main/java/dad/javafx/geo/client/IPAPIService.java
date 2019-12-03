package dad.javafx.geo.client;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import dad.javafx.geo.fxml.Geolocation;
import dad.javafx.geo.mensaje.Mensaje;

public class IPAPIService {
	
	private String apiKey = "80659911d6a33780232da07f11bb696f";

	public IPAPIService() {
		Unirest.setObjectMapper(new UnirestObjectMapper());
	}

	public Geolocation ipData(String ip) throws Exception {
		Geolocation geo = new Geolocation();
		try {
			geo = Unirest.get("http://api.ipapi.com/" + ip + "?access_key=" + apiKey)
					.asObject(Geolocation.class).getBody();
		} catch (UnirestException e) {
			Mensaje.mostrarAlert("error", "Error", "No se ha podido obtener datos de la IP: " + ip);
		}
		return geo;
	}
	
	
}
