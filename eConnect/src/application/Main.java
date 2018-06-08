package application;

import com.mysql.jdbc.EscapeTokenizer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class Main extends Application {

	private static Stage mainStage;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		primaryStage.setTitle("eConnected");

		Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/login/FXMLLogin.fxml"));
		Scene scene = new Scene(root, 708, 395);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() { // TREBUIE SA inchid threadurile cand inchid programu
			
			@Override
			public void handle(WindowEvent event) {
			}
		});
		
	}
	public static Stage getMainStage() {
		return mainStage;
	}
}
