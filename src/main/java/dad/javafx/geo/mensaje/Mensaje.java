package dad.javafx.geo.mensaje;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Mensaje {

    public static boolean mostrarAlert(String tipo, String titulo, String contenido) {
		Alert alert = null;
		
		boolean ret = false;
		if (tipo == "error" || tipo == "informacion"){
			if(tipo == "error") {
				alert = new Alert(AlertType.ERROR);
			} else if(tipo == "informacion") {
				alert = new Alert(AlertType.INFORMATION);
				titulo = null;
			}
			alert.setHeaderText(titulo);
			alert.setContentText(contenido);
			alert.showAndWait();
		} else if(tipo == "confirmacion") {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(titulo);
			alert.setContentText(contenido);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    ret = true;
			}
		}
		
		
		return ret;
	}
	
}
