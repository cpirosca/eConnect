package controller.office;

import application.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowStudentsController {

	@FXML private TableView<Student> tableView;
	private final static ObservableList<Student> dataList = FXCollections.observableArrayList();
	@FXML private TableColumn<Student, String> nume,prenume,CNP,grupa,specializare;
	
	
	public static void addStudent(Student student) {
		dataList.add(student);
	}
	public static ObservableList<Student> getStudent() {
		return dataList;
	}
	
	public void initialize() {
		nume.setCellValueFactory(new PropertyValueFactory<>("Nume"));
		prenume.setCellValueFactory(new PropertyValueFactory<>("Prenume"));
		CNP.setCellValueFactory(new PropertyValueFactory<>("CNP"));
		grupa.setCellValueFactory(new PropertyValueFactory<>("Grupa"));
		specializare.setCellValueFactory(new PropertyValueFactory<Student, String>("Specializare"));
		tableView.setItems(dataList);
		tableView.getColumns().clear();
		tableView.getColumns().addAll(nume,prenume,CNP,grupa,specializare);
	}
	
}