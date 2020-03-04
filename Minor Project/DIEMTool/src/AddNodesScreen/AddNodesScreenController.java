package AddNodesScreen;

import DIEMToolApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNodesScreenController implements Initializable {
	@FXML private Label addNodeToLabel, nodeToAddLabel;
	@FXML private TextArea addNodeToTextArea, nodeToAddTextArea;
	@FXML private CheckBox multipleNodesCheckBox;
	@FXML private Button addButton, cancelButton;
	private static String addNodeToLabelName, addNodeToTextAreaText, nodeToAddLabelName;
	private static String[] nodes;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		addNodeToLabel.setText(addNodeToLabelName);
		addNodeToTextArea.setText(addNodeToTextAreaText);
		nodeToAddLabel.setText(nodeToAddLabelName);
		nodeToAddTextArea.setText("");
		addButton.setOnAction(actionEvent -> {
			String text = nodeToAddTextArea.getText();
			if (multipleNodesCheckBox.isSelected()) {
				nodes = text.split("\n");
			} else {
				nodes = new String[]{text};
			}
			Main.addNodesScreenStage.close();
		});
		cancelButton.setOnAction(actionEvent -> {
			nodes = null;
			Main.addNodesScreenStage.close();
		});
	}

	public String[] display(String title, String addNodeToName, String addNodeToText, String nodeToAddName) {
		addNodeToTextAreaText = addNodeToText;
		addNodeToLabelName = addNodeToName;
		nodeToAddLabelName = nodeToAddName;
		try {
			Main.addNodesScreenParent = FXMLLoader.load(getClass().getResource(Main.addNodesScreenName));
		} catch (Exception ignored) {}
        Main.addNodesScreenScene = new Scene(Main.addNodesScreenParent);
		Main.addNodesScreenStage.setScene(Main.addNodesScreenScene);
		Main.addNodesScreenStage.setTitle(title);
		Main.addNodesScreenStage.showAndWait();
		return nodes;
	}
}
