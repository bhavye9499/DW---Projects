package DecisionScreen;

import AlertBox.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DecisionScreenController implements Initializable {

	@FXML private Button okButton, cancelButton;
	@FXML private TextArea decisionTextArea;
	private static String decision = "";
	private static String okButtonText = "";

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		okButton.setText(okButtonText);
		decisionTextArea.setText(decision);
		okButton.setOnAction(actionEvent -> {
			decision = decisionTextArea.getText();
			if (decision != null && !decision.strip().equals("")) {
				decision = decision.strip();
				DecisionScreen.getDecisionScreenStage().close();
			} else {
				AlertBox.getAlertBoxController().display("Error", "It seems that the decision is empty. Please give a valid decision.");
			}
		});
		cancelButton.setOnAction(actionEvent -> {
			decision = null;
			DecisionScreen.getDecisionScreenStage().close();
		});
	}

	public String display(String oldDecision) {
		decision = oldDecision;
		okButtonText = (oldDecision == null) ? "Add" : "Update";
		try {
        	DecisionScreen.setDecisionScreenScene(new Scene(FXMLLoader.load(getClass().getResource(DecisionScreen.getDecisionScreenName()))));
		} catch (Exception ignored) {}
		Stage stage = DecisionScreen.getDecisionScreenStage();
		stage.setScene(DecisionScreen.getDecisionScreenScene());
		stage.setTitle((oldDecision == null) ? "Add Decision" : "Update Decision");
		stage.showAndWait();
		return decision;
	}
}
