package MySQLLoginScreen;

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

public class MySQLLoginScreenController implements Initializable {

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
			MySQLLoginScreen.getMysqlLoginScreenStage().close();
		});
		cancelButton.setOnAction(actionEvent -> {
			uname = pass = null;
			MySQLLoginScreen.getMysqlLoginScreenStage().close();
		});
	}

	public String[] display() {
		try {
        	MySQLLoginScreen.setMysqlLoginScreenScene(new Scene(FXMLLoader.load(getClass().getResource(MySQLLoginScreen.getMysqlLoginScreenName()))));
		} catch (Exception ignored) {}
		Stage stage = MySQLLoginScreen.getMysqlLoginScreenStage();
		stage.setScene(MySQLLoginScreen.getMysqlLoginScreenScene());
		stage.showAndWait();
		if (uname == null || pass == null) return null;
		return new String[]{uname, pass};
	}

}
