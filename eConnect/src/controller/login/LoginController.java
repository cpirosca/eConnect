package controller.login;

import java.io.File;

import javax.crypto.Cipher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import application.Login;
import controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class LoginController {

	@FXML
	private Text incorrect;
	@FXML
	private JFXButton loginButton, clearButton;
	@FXML
	private TextField userInput, passwordInput;
	@FXML
	private JFXCheckBox rememberMe;

	private int access = -1;
	
	private File dir = new File(System.getProperty("user.home") + File.separator + "Documents/@#@!@$%!@.encrypted");
	
	@FXML
	private void handleLogin() {
		access = new Login().loginCheck(userInput.getText(), passwordInput.getText());
		if(access != -1) {
			incorrect.setVisible(false);
			if(access == 3) { // caut studentu dupa nume
				MainController.setUser(userInput.getText());
			}else if(access == 2) { // caut profesoru dupa nume
				MainController.setUser(userInput.getText());
			}
			new Login().successful(access);
			if(rememberMe.isSelected()) {
				dir.delete(); //altfel apare acces denied cand incearca sa scrie peste fisieru encrypted.
				new Login().rememberMe(Cipher.ENCRYPT_MODE, userInput, passwordInput);
			}
		}else {
			incorrect.setVisible(true);
		}
	}

	@FXML
	private void handleHelpAbout() {
		try {
			new Login().about();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleHelpAdmin() {
		try {
			new Login().admin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleClear() {
		userInput.setText("");
		passwordInput.setText("");
		if (dir.exists()) {
			dir.delete();
		}
	}
	
	@FXML
	private void userReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			passwordInput.requestFocus();
		}
	}
	@FXML 
	private void passReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			handleLogin();
		}
	}
	public void initialize() {
		new Login().loadRememberMe(userInput, passwordInput);
	}
}
