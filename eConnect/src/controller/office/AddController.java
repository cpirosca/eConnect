package controller.office;

import java.io.File;
import java.util.List;

import com.jfoenix.controls.JFXTextField;

import application.Connector;
import application.Office;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class AddController {

	ObservableList<String> specializareList = FXCollections.observableArrayList("Automatica Romana",
			"Automatica Engleza", "Calculatoare Romana", "Calculatoare Engleza");

	@FXML
	private Label errorLabel, succesLabel, formatLabel, oneFile;

	@FXML
	private JFXTextField numeInput, prenumeInput, CNPInput, grupaInput;

	@FXML
	private ChoiceBox<String> spChoice;

	@FXML
	private void initialize() {
		spChoice.setItems(specializareList);
		spChoice.setValue("");
	}

	@FXML
	private void addButton() {
		if (numeInput.getText().length() == 0 || prenumeInput.getText().length() == 0
				|| CNPInput.getText().length() == 0 || grupaInput.getText().length() == 0
				|| spChoice.getValue().length() == 0) {
			new Office().displayLabel(errorLabel, "Nu pot exista campuri goale !");
		} else {
			if (!numeInput.getText().chars().allMatch(Character::isLetter)
					|| !prenumeInput.getText().chars().allMatch(Character::isLetter)) {
				new Office().displayLabel(errorLabel, "Nu sunt acceptate caracterele speciale !");
			} else {
				if (CNPInput.getText().length() != 13 || !CNPInput.getText().chars().allMatch(Character::isDigit)) {
					new Office().displayLabel(errorLabel, "CNP incorrect !");
				} else {
					if (Connector.checkCNP(CNPInput.getText())) {
						new Office().displayLabel(errorLabel, "CNP exista deja !");
					} else {
						if (Connector.insertStudent(numeInput.getText(), prenumeInput.getText(),
								CNPInput.getText(), grupaInput.getText(), spChoice.getValue()) == true) {
							new Office().displayLabel(succesLabel, "Adaugat cu succes !");
						}else {
							new Office().displayLabel(errorLabel, "Eroare neprevazuta ! Exista grupa ?");
						}
						numeInput.setText("");
						prenumeInput.setText("");
						CNPInput.setText("");
						grupaInput.setText("");
					}
				}
			}
		}
	}

	@FXML
	private void doneButton() {
		Stage current = (Stage) errorLabel.getScene().getWindow();
		current.close();
	}

	@FXML
	private void browseButton() {
		File file = new Office().BrowseFiles();
		if (file != null) {
			new Office().extractFromFile(file);
		}
	}

	@FXML
	private void handleDragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}

	@FXML
	private void handleDrop(DragEvent event) {
		List<File> files = event.getDragboard().getFiles();
		if (files.size() == 1) {
			if ((files.get(0).getName().length() - files.get(0).getName().lastIndexOf("txt")) == 3
					|| (files.get(0).getName().length() - files.get(0).getName().lastIndexOf("xls")) == 3) {
				new Office().extractFromFile(files.get(0));
			} else {
				new Office().redLabel(formatLabel);
			}
		} else {
			new Office().redLabel(oneFile);
		}
	}

	@FXML
	public void numeReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			prenumeInput.requestFocus();
		}
	}

	@FXML
	public void prenumeReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			CNPInput.requestFocus();
		}
	}

	@FXML
	public void CNPReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			grupaInput.requestFocus();
		}
	}

	@FXML
	public void grupaReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			spChoice.requestFocus();
		}
	}

	@FXML
	public void specializareReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			numeInput.requestFocus();
		}
	}
}
