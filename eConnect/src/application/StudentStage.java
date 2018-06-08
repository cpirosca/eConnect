package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentStage {
	
	public void display() {
		Main.getMainStage().close();
		Stage studentStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Office.class.getResource("/view/fxml/student/Student.fxml"));
			Scene scene = new Scene(root);
			studentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		studentStage.setResizable(false);
		studentStage.initStyle(StageStyle.UNDECORATED);
		studentStage.show();
		studentStage.centerOnScreen();
	}
}
