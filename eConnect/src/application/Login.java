package application;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login {

	private String eConnect = "My eConnect cred";
	private File dir = new File(System.getProperty("user.home") + File.separator + "Documents/@#@!@$%!@.encrypted");

	public int loginCheck(String user, String pass) {
		if(pass.compareTo(Connector.getPassword(user)) == 0) {
			return Connector.accessLevel(user);
		}else {
			return -1;
		}
	}
	
	public void successful(int access) {
		switch (access) {
		case 1:
			new Office().display();
			break;
		case 2:
			new Teach().display();
			break;
		case 3:
			new StudentStage().display();
			break;
		}
	}
	
	public void about() throws IOException {
		Stage aboutStage = new Stage();
		aboutStage.initModality(Modality.APPLICATION_MODAL);

		Parent root = FXMLLoader.load(Login.class.getResource("/view/fxml/login/HelpAbout.fxml"));
		Scene scene = new Scene(root);

		aboutStage.initStyle(StageStyle.UNDECORATED);
		aboutStage.setResizable(false);
		aboutStage.setScene(scene);
		aboutStage.show();
	}

	public void admin() throws IOException {

		Stage adminStage = new Stage();
		adminStage.initModality(Modality.APPLICATION_MODAL);

		Parent root = FXMLLoader.load(Login.class.getResource("/view/fxml/login/HelpAdmin.fxml"));
		Scene scene = new Scene(root);

		adminStage.initStyle(StageStyle.UNDECORATED);
		adminStage.setResizable(false);
		adminStage.setScene(scene);
		adminStage.show();
	}

	public void loadRememberMe(TextField userInput, TextField passwordInput) {
		if (dir.exists()) {
			rememberMe(Cipher.DECRYPT_MODE, userInput, passwordInput);
		}
	}

	public void rememberMe(int cipherMode, TextField userInput, TextField passwordInput) {
		Thread updateLogin = new Thread(new Runnable() { // Thread stops when it exists

			@Override
			public void run() {
				try {
					SecretKeySpec secretKey = new SecretKeySpec(eConnect.getBytes(), "AES");
					Cipher cipher = Cipher.getInstance("AES");
					cipher.init(cipherMode, secretKey);
					InputStream inputStream;
					byte[] inputBytes;
					byte[] outputBytes;
					
					if(cipherMode == Cipher.DECRYPT_MODE) {
						inputStream = new FileInputStream(dir);
						inputBytes = new byte[(int) dir.length()];
					}else {
						String account = userInput.getText() + "\n" + passwordInput.getText();
						inputStream = new ByteArrayInputStream(account.getBytes(StandardCharsets.UTF_8.name()));
						inputBytes = new byte[(int) account.length()];
					}
					inputStream.read(inputBytes);
					outputBytes = cipher.doFinal(inputBytes);

					if(cipherMode == Cipher.DECRYPT_MODE) {
						String decrypt = new String(outputBytes, StandardCharsets.UTF_8);
						String string[] = decrypt.split("\\s+");
						javafx.application.Platform.runLater(() -> {
							userInput.setText(string[0]);
							passwordInput.setText(string[1]);
						});
					}
					else {
						if(!dir.exists()) {
							dir.getParentFile().mkdirs();
							dir.createNewFile();
						}
						FileOutputStream outputStream = new FileOutputStream(dir);
						outputStream.write(outputBytes);
						outputStream.close();
						Files.setAttribute(dir.toPath(), "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
					}
					
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		updateLogin.start();
	}
}
