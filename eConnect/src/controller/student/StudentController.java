package controller.student;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Assignments;
import application.Connector;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class StudentController {

	private ResultSet data, studentinfo;
	@FXML
	private Label nume, prenume, CNP, grupa, specializare, materie1, materie2, materie3, materie4, materie5, materie6,
			nota1, nota2, nota3, nota4, nota5, nota6;
	@FXML
	private TableView<Assignments> tableView;
	private final static ObservableList<Assignments> dataList = FXCollections.observableArrayList();
	@FXML
	private TableColumn<Assignments, String> materie, descriere;

	@FXML
	private void handleClose() {
		Stage current = (Stage) nume.getScene().getWindow();
		current.close();
	}

	public void initialize() {
		updateStudent();
		getAssignments();
		studentinfo = Connector.gradeByUser(MainController.getUser());
		materie.setCellValueFactory(new PropertyValueFactory<>("Title"));
		descriere.setCellValueFactory(new PropertyValueFactory<>("Description"));
		tableView.setItems(dataList);
		tableView.getColumns().clear();
		tableView.getColumns().addAll(materie, descriere);
	}

	private void updateStudent() {
		studentinfo = Connector.searchByUserStudentInfo(MainController.getUser());
		try {
			studentinfo.beforeFirst();
			if (studentinfo.next()) {
				nume.setText(studentinfo.getString(1));
				prenume.setText(studentinfo.getString(2));
				CNP.setText(studentinfo.getString(5));
				grupa.setText(studentinfo.getString(4));
				specializare.setText(studentinfo.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		studentinfo = Connector.getGradesAndSubjectsByStudentUsername(MainController.getUser());
		try {
			if (studentinfo != null) {
				studentinfo.beforeFirst();
				if (studentinfo.next()) {
					nota1.setText(studentinfo.getString(2));
					materie1.setText(studentinfo.getString(1));
				}
				if (studentinfo.next()) {
					nota2.setText(studentinfo.getString(2));
					materie2.setText(studentinfo.getString(1));
				}
				if (studentinfo.next()) {
					nota3.setText(studentinfo.getString(2));
					materie3.setText(studentinfo.getString(1));
				}
				if (studentinfo.next()) {
					nota4.setText(studentinfo.getString(2));
					materie4.setText(studentinfo.getString(1));
				}
				if (studentinfo.next()) {
					nota5.setText(studentinfo.getString(2));
					materie5.setText(studentinfo.getString(1));
				}
				if (studentinfo.next()) {
					nota6.setText(studentinfo.getString(2));
					materie6.setText(studentinfo.getString(1));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getAssignments() {
		data = Connector.assignmentByUser(MainController.getUser());
		try {
			data.beforeFirst();
			while (data.next()) {
				studentinfo = Connector.assignmentById(data.getString(1));
				studentinfo.beforeFirst();
				while (studentinfo.next()) {
					dataList.add(new Assignments(studentinfo.getString(1), studentinfo.getString(2)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
