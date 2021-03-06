package NodesScreen;

import AlertBox.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NodesScreenController implements Initializable {
	@FXML private Label addNodeToLabel, nodeToAddLabel;
	@FXML private TextArea addNodeToTextArea, nodeToAddTextArea;
	@FXML private CheckBox multipleNodesCheckBox;
	@FXML private Button okButton, cancelButton;
	private static String okButtonText, addNodeToLabelName, addNodeToTextAreaText, nodeToAddLabelName, nodeToAddTextAreaText;
	private static String[] nodes;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		okButton.setText(okButtonText);
		addNodeToLabel.setText(addNodeToLabelName);
		addNodeToTextArea.setText(addNodeToTextAreaText);
		nodeToAddLabel.setText(nodeToAddLabelName);
		nodeToAddTextArea.setText(nodeToAddTextAreaText);
		if (nodeToAddTextAreaText != null) multipleNodesCheckBox.setDisable(true);

		okButton.setOnAction(actionEvent -> {
			String text = nodeToAddTextArea.getText();
			if (text != null && !text.strip().equals("")) {
				nodes = multipleNodesCheckBox.isSelected() ? text.split("\n") : new String[]{text};
				if (!nodeToAddLabelName.equals("Attribute") || isCorrect()) {
					NodesScreen.getNodesScreenStage().close();
				}
			} else {
				AlertBox.getAlertBoxController().display("Error", "It seems that the " + nodeToAddLabelName + " is empty. Please give a valid " + nodeToAddLabelName + ".");
			}
		});
		cancelButton.setOnAction(actionEvent -> {
			nodes = new String[]{};
			NodesScreen.getNodesScreenStage().close();
		});
	}

	public String[] display(String title, String addNodeToName, String addNodeToText, String nodeToAddName, String nodeToAddText) {
		addNodeToLabelName = addNodeToName;
		addNodeToTextAreaText = addNodeToText;
		nodeToAddLabelName = nodeToAddName;
		nodeToAddTextAreaText = nodeToAddText;
		okButtonText = (nodeToAddTextAreaText == null) ? "Add" : "Update";
		try {
        	NodesScreen.setNodesScreenScene(new Scene(FXMLLoader.load(getClass().getResource(NodesScreen.getNodesScreenName()))));
		} catch (Exception ignored) {}
		Stage stage = NodesScreen.getNodesScreenStage();
		stage.setScene(NodesScreen.getNodesScreenScene());
		stage.setTitle(title);
		String message = "";
		if (nodeToAddTextAreaText == null) {	// Add operation, not an Update operation
			message = "If you want to add multiple values then add them on separate lines and don't forget to tick the checkbox for the same.\n";
		}
		if (nodeToAddName.equals("Attribute")) {
			message += "Make sure to write the datatype (int, string, float, etc.) of each attribute. For eg. firstName, string";
		}
		if (!message.equals("")) {
			AlertBox.getAlertBoxController().display("Message", message);
		}
		stage.showAndWait();
		return nodes;
	}

	private boolean isCorrect() {
		for (int i = 0; i < nodes.length; i++) {
			if (!nodes[i].contains(",")) {
				AlertBox.getAlertBoxController().display("Error", "Error at line " + (i + 1) + ": Missing datatype.");
				return false;
			}
		}
		return true;
	}

}
