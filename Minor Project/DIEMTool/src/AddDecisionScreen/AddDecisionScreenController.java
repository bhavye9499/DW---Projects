package AddDecisionScreen;

import DIEMToolApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
			Main.addDecisionScreenStage.close();
		});
		cancelButton.setOnAction(actionEvent -> {
			decision = null;
			Main.addDecisionScreenStage.close();
		});
	}

	public String display() {
		try {
			Main.addDecisionScreenParent = FXMLLoader.load(getClass().getResource(Main.addDecisionScreenName));
		} catch (Exception ignored) {}
        Main.addDecisionScreenScene = new Scene(Main.addDecisionScreenParent);
		Main.addDecisionScreenStage.setScene(Main.addDecisionScreenScene);
		Main.addDecisionScreenStage.showAndWait();
		return decision;
	}
}
