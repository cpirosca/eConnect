package controller.office;

import java.io.IOException;

import controller.MainController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class OfficeController {

	@FXML Text adaugaText;
	
	@FXML
	private void addStudent() {
		try {
			Stage addStudent = new Stage();
			Parent root = FXMLLoader.load(OfficeController.class.getResource("/view/fxml/office/OfficeAdd.fxml"));
			display(addStudent, root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void deleteStudent() {
		try {
			Stage deleteStudent = new Stage();
			Parent root = FXMLLoader.load(OfficeController.class.getResource("/view/fxml/office/OfficeDelete.fxml"));
			display(deleteStudent, root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void searchStudent() {
		try {
			Stage searchStudent = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/office/OfficeSearch.fxml"));
			Parent root = loader.load();
			SearchController c = loader.getController();
			//Parent root = FXMLLoader.load(OfficeController.class.getResource("/view/fxml/office/OfficeSearch.fxml"));
			searchStudent.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					c.setStop(true);
				}
			});
			display(searchStudent, root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void display(Stage current,Parent root) {
		//current.initModality(Modality.APPLICATION_MODAL);
		current.initStyle(StageStyle.UNDECORATED);
		current.setScene(new Scene(root));
		current.setResizable(false);
		current.show();
		current.centerOnScreen();
		current.setY(current.getY()+20);
		/*current.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				
			}
		});*/
	}
}
