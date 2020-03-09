package NodesScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
			if (multipleNodesCheckBox.isSelected()) {
				nodes = text.split("\n");
			} else {
				nodes = new String[]{text};
			}
			NodesScreen.getNodesScreenStage().close();
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
		stage.showAndWait();
		return nodes;
	}
}
