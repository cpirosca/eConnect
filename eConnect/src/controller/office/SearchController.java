package controller.office;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXTextField;

import application.Connector;
import application.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SearchController {

	ObservableList<String> specializareList = FXCollections.observableArrayList("Automatica Romana",
			"Automatica Engleza", "Calculatoare Romana", "Calculatoare Engleza");

	@FXML
	private JFXTextField nume, prenume, CNP, grupa, specializare;
	@FXML
	private Label cautareLabel, cautareLabel1, cautareLabel2, cautareLabel3, cautareLabel4, cautareLabel51,
			cautareLabel11, cautareLabel21, cautareLabel31, cautareLabel41, cautareLabel5;

	@FXML
	private TableView<Student> tableView;
	private final static ObservableList<Student> dataList = FXCollections.observableArrayList();
	@FXML
	private TableColumn<Student, String> numeCol, prenumeCol, CNPCol, grupaCol, specializareCol; // din nume

	volatile ResultSet students;
	private volatile boolean stop = false;

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@FXML
	private void handleClear() {
		nume.setText("");
		prenume.setText("");
		CNP.setText("");
		grupa.setText("");
		specializare.setText("");
		synchronized (dataList) {
			if (dataList != null) {
				dataList.clear();
			}

		}
		synchronized (students) {
			students = Connector.studentInfo(nume.getText(), prenume.getText(), CNP.getText(), grupa.getText(),
					specializare.getText());
		}
	}

	@FXML
	private void handleDone() {
		stop = true;
		Stage current = (Stage) nume.getScene().getWindow();
		current.close();
	}

	@FXML
	public void numeReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			prenume.requestFocus();
		}
	}

	@FXML
	public void prenumeReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			CNP.requestFocus();
		}
	}

	@FXML
	public void CNPReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			grupa.requestFocus();
		}
	}

	@FXML
	public void grupaReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			specializare.requestFocus();
		}
	}

	@FXML
	public void specializareReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			nume.requestFocus();
		}
	}

	public static void addStudent(Student student) {
		dataList.add(student);
	}
	
	public void initialize() {
		students = Connector.studentInfo(nume.getText(), prenume.getText(), CNP.getText(), grupa.getText(),
				specializare.getText());

		numeCol.setCellValueFactory(new PropertyValueFactory<>("Nume"));
		prenumeCol.setCellValueFactory(new PropertyValueFactory<>("Prenume"));
		CNPCol.setCellValueFactory(new PropertyValueFactory<>("CNP"));
		grupaCol.setCellValueFactory(new PropertyValueFactory<>("Grupa"));
		specializareCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Specializare"));
		tableView.setItems(dataList);
		tableView.getColumns().clear();
		tableView.getColumns().addAll(prenumeCol, numeCol, CNPCol, grupaCol, specializareCol);

		cautareLabel2.textProperty().bind(nume.textProperty());
		cautareLabel1.textProperty().bind(prenume.textProperty());
		cautareLabel3.textProperty().bind(CNP.textProperty());
		cautareLabel4.textProperty().bind(grupa.textProperty());
		cautareLabel5.textProperty().bind(specializare.textProperty());
		
		nume.textProperty().addListener((observable, oldValue, newValue) -> {
			synchronized (students) {
				students = Connector.studentInfo(nume.getText(), prenume.getText(), CNP.getText(), grupa.getText(),
						specializare.getText());
				try {
					if (students != null) {
						dataList.clear();
						students.beforeFirst();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		prenume.textProperty().addListener((observable, oldValue, newValue) -> {
			synchronized (students) {
				students = Connector.studentInfo(nume.getText(), prenume.getText(), CNP.getText(), grupa.getText(),
						specializare.getText());
				try {
					if (students != null) {
						dataList.clear();
						students.beforeFirst();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		CNP.textProperty().addListener((observable, oldValue, newValue) -> {
			synchronized (students) {
				students = Connector.studentInfo(nume.getText(), prenume.getText(), CNP.getText(), grupa.getText(),
						specializare.getText());
				try {
					if (students != null) {
						dataList.clear();
						students.beforeFirst();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		grupa.textProperty().addListener((observable, oldValue, newValue) -> {
			synchronized (students) {
				students = Connector.studentInfo(nume.getText(), prenume.getText(), CNP.getText(), grupa.getText(),
						specializare.getText());
				try {
					if (students != null) {
						dataList.clear();
						students.beforeFirst();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		specializare.textProperty().addListener((observable, oldValue, newValue) -> {
			synchronized (students) {
				students = Connector.studentInfo(nume.getText(), prenume.getText(), CNP.getText(), grupa.getText(),
						specializare.getText());
				try {
					if (students != null) {
						dataList.clear();
						students.beforeFirst();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		startThreads();
	}
	public void startThreads() {
		Thread loadTable = new Thread(new Runnable() {
			@Override
			synchronized public void run() {
				while (stop == false) {
					if (stop == true) {
						break;
					}
					synchronized (students) {
						try {
							
							if (students != null) {
								while (students.next()) {
									addStudent(new Student(students.getString(3), students.getString(2),
											students.getString(1), students.getString(4), students.getString(6)));
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
				}
			}
		});
		loadTable.start();

		Thread display = new Thread(new Runnable() {
			@Override
			public void run() {
				while (stop == false) {
					if (stop == true) {
						break;
					}
					if (nume.getText().length() != 0) {
						cautareLabel21.setVisible(true);
					} else {
						cautareLabel21.setVisible(false);
					}
					if (prenume.getText().length() != 0) {
						cautareLabel11.setVisible(true);
					} else {
						cautareLabel11.setVisible(false);
					}
					if (CNP.getText().length() != 0) {
						cautareLabel31.setVisible(true);
					} else {
						cautareLabel31.setVisible(false);
					}
					if (grupa.getText().length() != 0) {
						cautareLabel41.setVisible(true);
					} else {
						cautareLabel41.setVisible(false);
					}
					if (specializare.getText().length() != 0) {
						cautareLabel51.setVisible(true);
					} else {
						cautareLabel51.setVisible(false);
					}
				}
			}
		});
		display.start();
	}
}
