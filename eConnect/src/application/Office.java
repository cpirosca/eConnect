package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.office.ShowStudentsController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Office {

	public void display() {
		Stage officeStage = Main.getMainStage();
		officeStage.setTitle("Students management");

		try {
			Parent root = FXMLLoader.load(Office.class.getResource("/view/fxml/office/Office.fxml"));
			Scene scene = new Scene(root);
			officeStage.setScene(scene);

		} catch (IOException e) {
			e.printStackTrace();
		}
		officeStage.setResizable(false);
		officeStage.show();
		officeStage.centerOnScreen();
	}

	public File BrowseFiles() {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false); // sa nu apara all files.

		FileNameExtensionFilter txtfilter = new FileNameExtensionFilter("*.txt", "txt"); // tipurile de fisiere
																							// acceptate
		FileNameExtensionFilter xlsfilter = new FileNameExtensionFilter("*.xls", "xls");

		fileChooser.setFileFilter(txtfilter);
		fileChooser.addChoosableFileFilter(xlsfilter);
		fileChooser.setDialogTitle("Browse Files"); // sa schimb numele in bara de titlu

		fileChooser.showOpenDialog(null); // deschide browseru.

		return fileChooser.getSelectedFile(); // extrage ce-am ales si returneaza fisieru.
	}

	private volatile boolean stopdisplay = true;
	private volatile boolean stopdisplay1 = true;

	public void displayLabel(Label label, String string) {
		stopdisplay = false;
		Thread display = new Thread(new Runnable() {
			@Override
			public void run() {
				while (stopdisplay == false) {
					if (stopdisplay == true) {
						break;
					}
					Platform.runLater(() -> {
						label.setText(string);
						label.setVisible(true);
					});
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					label.setVisible(false);
					stopdisplay = true;
				}
			}
		});
		display.start();
	}

	public void redLabel(Label label) {
		stopdisplay1 = false;
		Thread test = new Thread(new Runnable() {
			@Override
			public void run() {
				while(stopdisplay1 == false) {
					if(stopdisplay1 == true) {
						break;
					}
					Platform.runLater(() -> {
						label.setTextFill(Color.RED);
						label.setFont(new Font("System", 22));
						if (label.getLayoutX() > 120) {
							label.setLayoutX(108);
							label.setLayoutY(301);
						} else {
							label.setLayoutX(45);
							label.setLayoutY(332);
						}
					});
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(() -> {
						label.setTextFill(Color.BLACK);
						label.setFont(new Font("System", 12));
						if (label.getLayoutX() == 45) {
							label.setLayoutX(111);
							label.setLayoutY(342);
						} else {
							label.setLayoutX(146);
							label.setLayoutY(312);
						}
					});
					stopdisplay1 = true;
				}
			}
		});
		test.start();
	}

	private volatile boolean stop = true;

	public void extractFromFile(File file) {

		stop = false;
		loadTable();

		Thread read = new Thread(new Runnable() {

			@Override
			public void run() {
				String original = new String("");
				while (stop == false) {
					if (stop == true) {
						break;
					}
					if ((file.getName().length() - file.getName().lastIndexOf("xls")) == 3) {
						try {
							Workbook workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int row = sheet.getRows();
							int col = sheet.getColumns();
							for (int i = 0; i < row; i++) {
								for (int j = 0; j < col; j++) {
									Cell cell = sheet.getCell(j, i);
									original = original + cell.getContents() + " ";
								}
								buildTable(original);
								original = "";
							}
						} catch (BiffException | IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							BufferedReader in = new BufferedReader(new FileReader(file));
							String strings = in.readLine();
							while (strings != null) {
								buildTable(strings);
								strings = in.readLine();
							}
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					stop = true;
				}
			}
		});
		read.start();
	}

	private void buildTable(String strings) {
		String string[] = strings.split(" ");
		if (string.length == 6) {
			string[4] = string[4] + " " + string[5];
			if (string[0].chars().allMatch(Character::isLetter) && string[1].chars().allMatch(Character::isLetter)) {
				if (string[2].length() == 13 && string[2].chars().allMatch(Character::isDigit)) {
					if (Connector.checkCNP(string[2]) == false) {
						if (Connector.checkGroup(string[3]) && Connector.checkSpecializare(string[4])) {
							Student student = new Student(string[0], string[1], string[2], string[3], string[4]);
							ShowStudentsController.addStudent(student);
							Connector.insertStudent(string[0], string[1], string[2], string[3], string[4]);
						}
					}
				}
			}

		}
	}

	private void loadTable() {
		try {
			ShowStudentsController.getStudent().clear(); // Delete the previous extracted content from file
			Stage show = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/office/showStudentsFromFile.fxml"));
			Scene scene = new Scene(root);
			show.initModality(Modality.APPLICATION_MODAL);
			show.setTitle("Students added");
			show.setResizable(false);
			show.setScene(scene);
			show.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
