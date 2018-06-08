package controller.teach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Connector;
import application.Student;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TeachController {

	private ResultSet data;
	@FXML
	private TableView<Student> tableView, tableView1;
	private final static ObservableList<Student> dataList = FXCollections.observableArrayList();
	private final static ObservableList<Student> grups = FXCollections.observableArrayList();
	@FXML
	private TableColumn<Student, String> nume, prenume, grupa, cnp, nota;
	@FXML
	private TableColumn<Student, CheckBox> select;
	@FXML
	TextArea textArea;
	private List<String> groups = new ArrayList<>();

	@FXML
	public void addGrade() {
		for (int i = 0; i < dataList.size(); i++) {
			if (nota.getCellData(i) != null) {
				Connector.awardGrade(dataList.get(i).getCNP(), Integer.valueOf(nota.getCellData(i)),
						MainController.getUser());
			}
		}
	}

	@FXML
	public void addAssignment() {
		if (textArea.getText().length() > 1) {
			String listString = String.join(" ", groups);
			Connector.createBondBetweenAssignmentAndGroup(listString.split(" "), textArea.getText(),
					MainController.getUser());
		}

	}

	public void initialize() {
		textArea.setWrapText(true);
		nume.setCellValueFactory(new PropertyValueFactory<>("Nume"));
		prenume.setCellValueFactory(new PropertyValueFactory<>("Prenume"));
		cnp.setCellValueFactory(new PropertyValueFactory<>("CNP"));
		nota.setCellValueFactory(new PropertyValueFactory<>("Nota"));
		grupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));
		select.setCellValueFactory(new PropertyValueFactory<>("select"));
		tableView.setItems(dataList);
		tableView.getColumns().clear();
		tableView.getColumns().addAll(nume, prenume, cnp, nota);
		tableView1.setItems(grups);
		tableView1.getColumns().clear();
		tableView1.getColumns().addAll(select, grupa);
		tableView.setEditable(true);
		// tableView.getSelectionModel().cellSelectionEnabledProperty().set(true); -- if
		// I want to select each cell

		nota.setCellFactory(TextFieldTableCell.forTableColumn()); // SO I CAN EDIT THIS PARTICULAR CELL !!!!
		nota.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setNota(t.getNewValue()));
		getGrups();
		getStudents();
	}

	public void getGrups() {
		data = Connector.groupsByTeacherUsername(MainController.getUser());
		try {
			data.beforeFirst();
			while (data.next()) {
				groups.add(data.getString(1));
				grups.add(new Student(data.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getStudents() {
		data = Connector.studentsByTeacherUsername(MainController.getUser());
		try {
			data.beforeFirst();
			while (data.next()) {
				dataList.add(new Student(data.getString(1), data.getString(2), data.getString(3), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}