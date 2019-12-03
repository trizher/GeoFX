package dad.javafx.geo.client;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import dad.javafx.geo.mensaje.Mensaje;

public class IPIFYService {

	public IPIFYService() {
		Unirest.setObjectMapper(new UnirestObjectMapper());
	}

	public String obtenerIP() throws Exception {
		String ip = "";
		try {
			ip = Unirest.get("https://api.ipify.org").asObject(String.class).getBody();
		} catch (UnirestException e) {
			Mensaje.mostrarAlert("error", "Error", "Error al obtener IP");
		}
		return ip;
	}
}
