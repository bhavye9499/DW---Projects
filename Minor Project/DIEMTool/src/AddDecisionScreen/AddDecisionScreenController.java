package AddDecisionScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDecisionScreenController implements Initializable {

	@FXML private Button addButton, cancelButton;
	@FXML private TextArea decisionTextArea;
	private static String decision;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		decisionTextArea.setText("");
		addButton.setOnAction(actionEvent -> {
			decision = decisionTextArea.getText();
			AddDecisionScreen.getAddDecisionScreenStage().close();
		});
		cancelButton.setOnAction(actionEvent -> {
			decision = null;
			AddDecisionScreen.getAddDecisionScreenStage().close();
		});
	}

	public String display() {
		try {
        	AddDecisionScreen.setAddDecisionScreenScene(new Scene(FXMLLoader.load(getClass().getResource(AddDecisionScreen.getAddDecisionScreenName()))));
		} catch (Exception ignored) {}
		Stage stage = AddDecisionScreen.getAddDecisionScreenStage();
		stage.setScene(AddDecisionScreen.getAddDecisionScreenScene());
		stage.showAndWait();
		return decision;
	}
}
