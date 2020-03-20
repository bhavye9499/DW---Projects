package MysqlLoginScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MysqlLoginScreenController implements Initializable {

	@FXML private Button loginButton, cancelButton;
	@FXML private TextField unameTextField;
	@FXML private PasswordField passPasswordField;
	private static String uname, pass;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		unameTextField.setText("");
		passPasswordField.setText("");
		loginButton.setOnAction(actionEvent -> {
			uname = unameTextField.getText();
			pass = passPasswordField.getText();
			MysqlLoginScreen.getMysqlLoginScreenStage().close();
		});
		cancelButton.setOnAction(actionEvent -> {
			uname = pass = null;
			MysqlLoginScreen.getMysqlLoginScreenStage().close();
		});
	}

	public String[] display() {
		try {
        	MysqlLoginScreen.setMysqlLoginScreenScene(new Scene(FXMLLoader.load(getClass().getResource(MysqlLoginScreen.getMysqlLoginScreenName()))));
		} catch (Exception ignored) {}
		Stage stage = MysqlLoginScreen.getMysqlLoginScreenStage();
		stage.setScene(MysqlLoginScreen.getMysqlLoginScreenScene());
		stage.showAndWait();
		if (uname == null || pass == null) return null;
		return new String[]{uname, pass};
	}

}
