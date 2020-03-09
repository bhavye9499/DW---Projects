package NodesScreen;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NodesScreen {

	private static String nodesScreenName;
	private static Scene nodesScreenScene;
	private static Stage nodesScreenStage;
	private static NodesScreenController nodesScreenController;

	public static void init(String name) {
		setNodesScreenName(name);
		setNodesScreenStage(new Stage());
		setNodesScreenController(new NodesScreenController());
	}

	public static String getNodesScreenName() {
		return nodesScreenName;
	}

	public static void setNodesScreenName(String name) {
		nodesScreenName = name;
	}

	public static Scene getNodesScreenScene() {
		return nodesScreenScene;
	}

	public static void setNodesScreenScene(Scene scene) {
		nodesScreenScene = scene;
	}

	public static Stage getNodesScreenStage() {
		return nodesScreenStage;
	}

	private static void setNodesScreenStage(Stage stage) {
		nodesScreenStage = stage;
		nodesScreenStage.initModality(Modality.APPLICATION_MODAL);
	}

	public static NodesScreenController getNodesScreenController() {
		return nodesScreenController;
	}

	public static void setNodesScreenController(NodesScreenController controller) {
		nodesScreenController = controller;
	}
}
