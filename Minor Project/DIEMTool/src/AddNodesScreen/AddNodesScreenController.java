package AddNodesScreen;

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
			AddNodesScreen.getAddNodesScreenStage().close();
		});
		cancelButton.setOnAction(actionEvent -> {
			nodes = new String[]{};
			AddNodesScreen.getAddNodesScreenStage().close();
		});
	}

	public String[] display(String title, String addNodeToName, String addNodeToText, String nodeToAddName) {
		addNodeToTextAreaText = addNodeToText;
		addNodeToLabelName = addNodeToName;
		nodeToAddLabelName = nodeToAddName;
		try {
        	AddNodesScreen.setAddNodesScreenScene(new Scene(FXMLLoader.load(getClass().getResource(AddNodesScreen.getAddNodesScreenName()))));
		} catch (Exception ignored) {}
		Stage stage = AddNodesScreen.getAddNodesScreenStage();
		stage.setScene(AddNodesScreen.getAddNodesScreenScene());
		stage.setTitle(title);
		stage.showAndWait();
		return nodes;
	}
}
