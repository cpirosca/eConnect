package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Teach {
	public void display() {
		Stage teachStage = Main.getMainStage();
		teachStage.setTitle("Students management");
		try {
			Parent root = FXMLLoader.load(Office.class.getResource("/view/fxml/teach/Teach.fxml"));
			Scene scene = new Scene(root);
			teachStage.setScene(scene);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		teachStage.setResizable(false);
		teachStage.show();
		teachStage.centerOnScreen();
	}

}
