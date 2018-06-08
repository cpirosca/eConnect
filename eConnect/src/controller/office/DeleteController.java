package controller.office;

import java.sql.ResultSet;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Connector;
import application.Office;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteController {

	@FXML
	private JFXButton doneButton, deleteButton;
	@FXML
	private JFXTextField searchLabel;
	@FXML
	private Label errorLabel, nume, prenume, CNP, grupa, specializare, succesLabel;
	private volatile boolean stop = true;
	private String CNPsaved;

	@FXML
	private void handleDoneButton() {
		Stage current = (Stage) doneButton.getScene().getWindow();
		current.close();
	}

	@FXML
	private void handleSearch() {
		CNPsaved = searchLabel.getText();
		if (CNPsaved.length() != 13 || !CNPsaved.chars().allMatch(Character::isDigit)) {
			new Office().displayLabel(errorLabel, "CNP Incorrect!");
		} else {
			String temp = Connector.searchByCnp(CNPsaved);
			if (temp != null) {
				String[] student = temp.split(" ");
				displaySearchedStudent(student[0], student[1], student[2], student[3], student[5] + " " + student[6],
						true);
			} else {
				new Office().displayLabel(errorLabel, "CNP inexistent!");
			}
		}
	}

	@FXML
	private void handleDeleteButton() {
		displaySearchedStudent(null, null, null, null, null, false);
		if (!Connector.deleteStudent(CNPsaved)) {
			new Office().displayLabel(errorLabel, "Nu a putut fi sters!");
		}
	}

	private void displaySearchedStudent(String nume1, String prenume1, String CNP1, String grupa1, String specializare1,
			boolean show) {
		stop = false;
		Thread display = new Thread(new Runnable() {
			@Override
			public void run() {
				while (stop == false) {
					if (stop == true) {
						break;
					}
					if (show == true) {
						Platform.runLater(() -> {
							nume.setText(nume1);
							prenume.setText(prenume1);
							CNP.setText(CNP1);
							grupa.setText(grupa1);
							specializare.setText(specializare1);
							nume.setVisible(true);
							prenume.setVisible(true);
							CNP.setVisible(true);
							grupa.setVisible(true);
							specializare.setVisible(true);
							deleteButton.setVisible(true);
						});
					} else {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						nume.setVisible(false);
						prenume.setVisible(false);
						CNP.setVisible(false);
						grupa.setVisible(false);
						specializare.setVisible(false);
						deleteButton.setVisible(false);
						succesLabel.setVisible(true);
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						succesLabel.setVisible(false);
					}
					stop = true;
				}
			}
		});
		display.start();
	}
}
