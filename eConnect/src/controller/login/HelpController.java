package controller.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {

	@FXML private Button closeHAbout, cancelHAdmin;
	//to be added establish connection
	
	@FXML
	private void closeHelpAbout() {
		Stage stage = (Stage) closeHAbout.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void closeHelpAdmin() {
		Stage stage = (Stage) cancelHAdmin.getScene().getWindow();
		stage.close();
	}
}
