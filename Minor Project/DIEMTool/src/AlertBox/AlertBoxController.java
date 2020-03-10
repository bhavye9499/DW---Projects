package AlertBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertBoxController implements Initializable {

	@FXML private Label messageLabel;
	private static String messageLabelText;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		messageLabel.setText(messageLabelText);
	}

	public void display(String title, String message) {
		messageLabelText = message;
		try {
        	AlertBox.setAlertBoxScene(new Scene(FXMLLoader.load(getClass().getResource(AlertBox.getAlertBoxName()))));
		} catch (Exception ignored) {}
		Stage stage = AlertBox.getAlertBoxStage();
		stage.setScene(AlertBox.getAlertBoxScene());
		stage.setTitle(title);
		stage.showAndWait();
	}
}
